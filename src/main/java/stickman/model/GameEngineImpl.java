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
    private double startTime;
    private int lives;

    /**
     * Constructor to initialise the game, taking in the JSON filename
     * as a String parameter. Initialises variables with a JSON file reader
     * and starts the level with appropriate values.
     * @param jsonFile
     */
    public GameEngineImpl(String jsonFile) throws FileNotFoundException {
        JsonReader reader = new JsonReader(jsonFile);
        this.stickmanSize = reader.getStickmanSize();
        this.xPos = reader.getxPos();
        this.cloudVelocity = reader.getCloudVelocity();
        this.levelNumber = reader.getLevelNumber();
        this.platformNumber = reader.getPlatformNumber();
        this.platform = reader.getPlatform();
        this.enemyNumber = reader.getEnemyNumber();
        this.enemy = reader.getEnemy();
        this.finishLine = reader.getFinishLine();
        this.lives = 3;

        if (xPos < 0 || cloudVelocity < 0) {
            System.err.println("Unexpected negative value from JSON file found.");
            System.exit(1);
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
        startTime = System.currentTimeMillis();
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

    /**
     * Returns whether or not the level has been completed.
     * @return
     */
    public boolean finish() {
        return this.currentLevel.finish();
    }

    /**
     * Returns the time that the game has started as a double.
     * @return
     */
    public double getStartTime() { return this.startTime; }

    /**
     * Refreshes the current level to its initial values.
     */
    public void restartLevel() {
        this.currentLevel = new LevelImpl(xPos, cloudVelocity, stickmanSize,
                levelNumber, platformNumber, platform, enemyNumber, enemy, finishLine);
    }

    /**
     * Returns the number of lives the hero has.
     * @return
     */
    public int getLives() {
        return lives;
    }

    /**
     * Checks whether of not the hero is still alive. Sets a flag
     * to notify that the game is over if the hero's lives left is 0.
     * @return
     */
    public boolean gameOver() {
        if (lives == 0) {
            currentLevel.gameOver();
            return true;
        }
        return false;
    }

    /**
     * Decreases the hero's lives by 1 if a flag from level is raised.
     * @return
     */
    public boolean heroDead() {
        if (this.currentLevel.heroDead()) {
            lives --;
        }
        return this.currentLevel.heroDead();
    }
}

