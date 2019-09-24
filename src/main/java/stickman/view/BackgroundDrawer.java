package stickman.view;

import javafx.scene.layout.Pane;
import stickman.model.GameEngine;

/** Interface for BackgroundDrawer */
public interface BackgroundDrawer {
    /** Draws the game's background. */
    void draw(GameEngine model, Pane pane);
    /** Does nothing as it is a static background. */
    void update(double xViewportOffset);
}
