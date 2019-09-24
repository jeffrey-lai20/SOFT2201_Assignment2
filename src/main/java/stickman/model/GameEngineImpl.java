package stickman.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

/**
 * GameEngineImpl implements the interface GameEngine.
 * It provides the core functionality for the game and initialises it.
 * Functionality includes movement of the hero and updating the frame
 * of the game. The game is initalised with input of a JSON file.
 */
public class GameEngineImpl implements GameEngine {
    private LevelImpl currentLevel;
    private double xPos;
    private double cloudVelocity;
    private String stickmanSize;
    private long levelNumber;
    private long platformNumber;
    private double[] platform;
    private long enemyNumber;
    private double[] enemy;
    private double finishLine;

    /**
     * Constructor to initialise the game, taking in the JSON filename
     * as a String parameter. Reads the variable information from the
     * JSON file and starts the level with appropriate values.
     * @param jsonFile
     */
    public GameEngineImpl(String jsonFile) {
        JSONParser parser = new JSONParser();
        try {
            Object arrayObj = parser.parse(new InputStreamReader(new FileInputStream(jsonFile)));
            JSONObject stickman = (JSONObject) arrayObj;
            this.stickmanSize = (String) stickman.get("stickmanSize");
            JSONObject stickmanPos = (JSONObject) stickman.get("stickmanPos");
            this.xPos = (double) stickmanPos.get("x");
            this.cloudVelocity = (double) stickman.get("cloudVelocity");
            this.levelNumber = (long) stickman.get("levelNumber");
            this.platformNumber = (long) stickman.get("platformNumber");
            this.platform = new double [((int) platformNumber)*2];
//            for (int i = 0; i < platformNumber*2; i += 2) {
//                JSONObject pf = (JSONObject) stickman.get("platform"+Integer.toString(i+1));
//                this.platform[i] = (double) pf.get("x");
//                this.platform[i+1] = (double) pf.get("y");
//            }
            this.enemyNumber = (long) stickman.get("enemyNumber");
            this.enemy = new double [(int) enemyNumber];
//            for (int j = 0; j < enemyNumber; j++) {
//                JSONObject en = (JSONObject) stickman.get("enemy"+Integer.toString(j+1));
//                this.enemy[j] = (double) en.get("x");
//            }
            JSONObject pf = (JSONObject) stickman.get("platform1");
            this.platform[0] = (double) pf.get("x");
            this.platform[1] = (double) pf.get("y");
            JSONObject pf2 = (JSONObject) stickman.get("platform2");
            this.platform[2] = (double) pf2.get("x");
            this.platform[3] = (double) pf2.get("y");


            JSONObject en = (JSONObject) stickman.get("enemy1");
            this.enemy[0] = (double) en.get("x");
            JSONObject en2 = (JSONObject) stickman.get("enemy2");
            this.enemy[1] = (double) en2.get("x");
            this.finishLine = (double) stickman.get("finishLine");

            if (!(stickmanSize.equals("tiny") || stickmanSize.equals("normal") ||
                stickmanSize.equals("large") || stickmanSize.equals("giant"))) {
                System.err.println("Unexpected stickman size from JSON file.");
                System.exit(1);
            }

            if (xPos < 0 || cloudVelocity < 0) {
                System.err.println("Unexpected negative value from JSON file found.");
                System.exit(1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startLevel();
    }

    @Override
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void startLevel() {
        this.currentLevel = new LevelImpl(xPos, cloudVelocity, stickmanSize,
                levelNumber, platformNumber, platform, enemyNumber, enemy, finishLine);
    }

    @Override
    public boolean jump() {
        return this.currentLevel.jump();
    }

    @Override
    public boolean moveLeft() {
        return this.currentLevel.moveLeft();
    }

    @Override
    public boolean moveRight() {
        return this.currentLevel.moveRight();
    }

    @Override
    public boolean stopMoving() {
        return this.currentLevel.stopMoving();
    }

    @Override
    public void tick() {
    this.currentLevel.tick();
    }

    public boolean finish() {
        return this.currentLevel.finish();
    }
}

