package stickman.model;

import java.util.List;
import java.util.ArrayList;

/**
 * LevelImpl implements the interface Level. It provides information
 * about the game's current level, such as where entities are positioned
 * and where they should be positioned next.
 */
public class LevelImpl implements Level {
    private double height;
    private double width;
    private double floorHeight;
    private double heroX;
    private double heroXV;
    private double cloudVelocity;
    private List<Entity> entities;
    private double cloud1x;
    private double cloud2x;
    private boolean left;
    private boolean right;
    private boolean jump;
    private double size;
    private long levelNumber;
    private long platformNumber;
    private double[] platform;
    private long enemyNumber;
    private double[] enemy;
    private double finishLine;
    private boolean finish;
    private int jumpTick = 0;

    private Hero hero;
    private Cloud cloud;
    private Cloud cloud2;
    private AbstractEntity platform1;
    private AbstractEntity platform2;
    /**
     * Constructor to set initial dimensions and positions
     * of the level and its entities.
     * @param heroX
     * @param cloudVelocity
     * @param stickmanSize
     */
    public LevelImpl (double heroX, double cloudVelocity, String stickmanSize, long levelNumber, long platformNumber,
                      double[] platform, long enemyNumber, double[] enemy, double finishLine) {
        if (stickmanSize.equalsIgnoreCase("tiny")) {
            size = 20;
        } else if (stickmanSize.equalsIgnoreCase("normal")) {
            size = 35;
        } else if (stickmanSize.equalsIgnoreCase("large")) {
            size = 50;
        } else if (stickmanSize.equalsIgnoreCase("giant")) {
            size = 70;
        } else {
            System.err.println("Incorrect stickman size input from JSON File.");
            System.exit(1);
        }
        this.height = 300;
        this.width = 640;
        this.floorHeight = 300;
        this.heroX = heroX;
        this.heroXV = heroX;
        this.cloudVelocity = cloudVelocity;
        this.entities = new ArrayList<Entity> ();
        this.cloud1x = 150;
        this.cloud2x = 500;
        this.levelNumber = levelNumber;
        this.platformNumber = platformNumber;
        this.platform = platform;
        this.enemyNumber = enemyNumber;
        this.enemy = enemy;
        this.finishLine = finishLine;
        this.finish = false;
        hero = new Hero("Hero", heroX, floorHeight-size, size);
        cloud = new Cloud("Cloud1", cloud1x, height - 200, size);
        cloud2 = new Cloud("Cloud2", cloud2x, height - 230, size);
        platform1 = new AbstractEntity("Platform1",platform[0], platform[1], size);
        platform2 = new AbstractEntity("Platform2",platform[2], platform[3], size);
    }

    @Override
    public List<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public void tick() {
        entities = new ArrayList<Entity> ();
        entities.add(0,hero);

        if (left && hero.getXPos() > 0) {
            hero.moveLeft();
        } else if (right) {
            hero.moveRight();
        }

        if (jump) {
            if (jumpTick < 30) {
                hero.jump();
                jumpTick++;
            }
        }

        if (jumpTick >= 30 && jumpTick < 60) {
            hero.desc();
            jumpTick++;
        } else if (jumpTick == 60) {
            jump = false;
            jumpTick = 0;
        }

        entities.add(0, hero);
        entities.add(1, cloud);
        entities.add(2, cloud2);
        entities.add(3, platform1);
        entities.add(4, platform2);
    }

    @Override
    public double getFloorHeight() {
        return this.floorHeight;
    }

    @Override
    public double getHeroX() {
        return this.hero.getXPos();
    }

    @Override
    public boolean jump() {
        if (jumpTick != 0) {
            return false;
        }
        jump = true;
        return true;
    }

    @Override
    public boolean moveLeft() {
            left = true;
        return true;
    }

    @Override
    public boolean moveRight() {
            right = true;
        return true;
    }

    @Override
    public boolean stopMoving() {
        left = false;
        right = false;
        return true;
    }

    public boolean finish() {
        if (finish)
        System.out.println("Finished!");
        return finish;
    }

    private void cloud() {
        if (cloud1x > width) cloud1x = -80;
        if (cloud2x > width) cloud2x = -80;
        if (cloud1x < -80) cloud1x = width;
        if (cloud2x < -80) cloud2x = width;
        cloud1x += cloudVelocity/60;
        cloud2x += cloudVelocity/60;
        if (left && heroXV >= 280 && heroX == 279) {
            cloud1x += 1;
            cloud2x += 1;
        } else if (right && heroXV >= 360 && heroX == 360) {
            cloud1x -= 1;
            cloud2x -= 1;
        }
    }

}
