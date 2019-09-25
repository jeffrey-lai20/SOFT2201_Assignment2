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

    public JsonReader(String jsonFile) throws FileNotFoundException {
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
//            for (int i = 0; i < platformNumber*2; i += 2) {
//                JSONObject pf = (JSONObject) stickman.get("platform"+Integer.toString(i+1));
//                this.platform[i] = (double) pf.get("x");
//                this.platform[i+1] = (double) pf.get("y");
//            }
            this.enemyNumber = (long) stickman.get("enemyNumber");
            this.enemy = new double[(int) enemyNumber];
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

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStickmanSize() {
        return stickmanSize;
    }

    public double getCloudVelocity() {
        return cloudVelocity;
    }

    public double getxPos() {
        return xPos;
    }

    public double getFinishLine() {
        return finishLine;
    }

    public long getLevelNumber() {
        return levelNumber;
    }

    public long getPlatformNumber() {
        return platformNumber;
    }


    public double[] getPlatform() {
        return platform;
    }

    public long getEnemyNumber() {
        return enemyNumber;
    }

    public double[] getEnemy() {
        return enemy;
    }
}
