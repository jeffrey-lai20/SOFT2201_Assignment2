package stickman.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class JsonReader {

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
     * Reads in a JSON file and initialising variables to the given input.
     * stickmanSize comes in 4 sizes, tiny, normal, large, or giant.
     * @param jsonFile
     */
    public JsonReader(String jsonFile) {
        JSONParser parser = new JSONParser();
        try {
            Object arrayObj = parser.parse(new InputStreamReader(new FileInputStream(jsonFile)));
            JSONObject stickman = (JSONObject) arrayObj;
            this.stickmanSize = (String) stickman.get("stickmanSize");
            if (!(stickmanSize.equals("tiny") || stickmanSize.equals("normal") ||
                    stickmanSize.equals("large") || stickmanSize.equals("giant"))) {
                System.err.println("Unexpected stickman size from JSON file.");
                System.exit(1);
            }
            JSONObject stickmanPos = (JSONObject) stickman.get("stickmanPos");
            this.xPos = (double) stickmanPos.get("x");
            this.cloudVelocity = (double) stickman.get("cloudVelocity");
            this.levelNumber = (long) stickman.get("levelNumber");
            this.platformNumber = (long) stickman.get("platformNumber");
            this.platform = new double[((int) platformNumber) * 2];
            for (int i = 0; i < platformNumber*2; i += 2) {
                char num = (char) ((i/2)+'0');
                JSONObject pf = (JSONObject) stickman.get("platform"+num);
                this.platform[i] = (double) pf.get("x");
                this.platform[i+1] = (double) pf.get("y");

            }
            this.enemyNumber = (long) stickman.get("enemyNumber");
            this.enemy = new double[(int) enemyNumber];
            for (int j = 0; j < enemyNumber; j++) {
                char num = (char) ((j)+'0');
                JSONObject en = (JSONObject) stickman.get("enemy"+num);
                this.enemy[j] = (double) en.get("x");
            }
            this.finishLine = (double) stickman.get("finishLine");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the stickman size read from the JSON file.
     * @return
     */
    public String getStickmanSize() {
        return stickmanSize;
    }

    /**
     * Returns the cloud velocity read from the JSON file.
     * @return
     */
    public double getCloudVelocity() {
        return cloudVelocity;
    }

    /**
     * Returns the hero's initial x-position read from the JSON file.
     * @return
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Returns the finish line's x-position read from the JSON file.
     * @return
     */
    public double getFinishLine() {
        return finishLine;
    }

    /**
     * Returns the level number read from the JSON file.
     * @return
     */
    public long getLevelNumber() {
        return levelNumber;
    }

    /**
     * Returns the number of platforms that will be given,
     * read from the JSON file.
     * @return
     */
    public long getPlatformNumber() {
        return platformNumber;
    }

    /**
     * Returns an array of doubles, where even indices are x-positions,
     * and odd indices are y-positions, read from the JSON file.
     * @return
     */
    public double[] getPlatform() {
        return platform;
    }

    /**
     * Returns the number of enemies that will be given,
     * read from the JSON file.
     * @return
     */
    public long getEnemyNumber() {
        return enemyNumber;
    }

    /**
     * Returns an array of doubles that represent the
     * x-positions of enemies, read from the JSON file.
     * @return
     */
    public double[] getEnemy() {
        return enemy;
    }
}
