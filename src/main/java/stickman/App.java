package stickman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;
import stickman.view.GameWindow;

import java.io.IOException;
import java.util.Map;

/**
 * App initialises and starts the game.
 */
public class App extends Application {

    /**
     * Main method for App.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        Map<String, String> params = getParameters().getNamed();

        String s = "Java 11 sanity check";
        if (s.isBlank()) {
            throw new IllegalStateException("You must be running Java 11+. You won't ever see this exception though" +
                    " as your code will fail to compile on Java 10 and below.");
        }

        GameEngineImpl model = new GameEngineImpl("src/main/resources/load.json");
        GameWindow window = new GameWindow(model, 640, 400);
        window.run();

        primaryStage.setTitle("Stickman");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
