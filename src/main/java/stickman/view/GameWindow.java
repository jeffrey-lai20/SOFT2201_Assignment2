package stickman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import stickman.model.Entity;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Draws the game's window as well as its entities. Continously
 * updates the frame and draws newly position entities.
 */
public class GameWindow {
    private final int width;
    private Scene scene;
    private Pane pane;
    private GameEngineImpl model;
    private List<EntityView> entityViews;
    private BackgroundDrawer backgroundDrawer;

    private double xViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 280.0;

    /**
     * Constructor for GameWindow. Sets the initial values of its
     * attributes and initialises drawing and intake of keyboard input.
     * @param model
     * @param width
     * @param height
     */
    public GameWindow(GameEngineImpl model, int width, int height) {
        this.model = model;
        this.pane = new Pane();
        this.width = width;
        this.scene = new Scene(pane, width, height);

        this.entityViews = new ArrayList<>();

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

//        this.backgroundDrawer = new BlockedBackground();
        this.backgroundDrawer = new ParallaxBackground();
        backgroundDrawer.draw(model, pane);
    }

    /** Returns the scene. */
    public Scene getScene() {
        return this.scene;
    }

    /** Runs the game window at a refresh rate of every 17ms*/
    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /** Draws and updates the game window and all its entities. */
    private void draw() {
        model.tick();

        if (model.finish()) {
            Text finished = new Text("FINISHED!");
            finished.setX(model.getCurrentLevel().getWidth()/2);
            finished.setY(model.getCurrentLevel().getHeight()/2);
        }
        List<Entity> entities = model.getCurrentLevel().getEntities();

        for (EntityView entityView: entityViews) {
            entityView.markForDelete();
        }

        double heroXPos = model.getCurrentLevel().getHeroX();
        heroXPos -= xViewportOffset;

        //viewport margin is 280
        //if hero is less than 280
        if (heroXPos < VIEWPORT_MARGIN) {
            if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                xViewportOffset -= VIEWPORT_MARGIN - heroXPos;
                if (xViewportOffset < 0) {
                    xViewportOffset = 0;
                }
            }
            //hero is greater than 360
        } else if (heroXPos > width - VIEWPORT_MARGIN) {
            xViewportOffset += heroXPos - (width - VIEWPORT_MARGIN);
        }

        backgroundDrawer.update(xViewportOffset);

        for (Entity entity: entities) {
            boolean notFound = true;
            for (EntityView view: entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView: entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }
}
