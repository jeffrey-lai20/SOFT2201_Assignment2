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

    /**
     * Constructor to initialise the game, taking in the JSON filename
     * as a String parameter. Reads the variable information from the
     * JSON file and starts the level with appropriate values.
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

    public boolean finish() {
        return this.currentLevel.finish();
    }

    public double getStartTime() { return this.startTime; }
}

