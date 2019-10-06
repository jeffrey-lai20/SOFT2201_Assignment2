package stickman.model;

import stickman.model.Entity.*;

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
    private double cloudVelocity;
    private List<Entity> entities;
    private int numClouds;
    private boolean left;
    private boolean right;
    private boolean jump;
    private double size;
    private long platformNumber;
    private long enemyNumber;
    private double finishLine;
    private boolean finish;
    private int jumpY = 0;
    private boolean top;
    private boolean gameOver;
    private Hero hero;
    private Cloud[] clouds;
    private PlatformEntity[] platformEntities;
    private Enemy[] enemyEntities;
    private FlagEntity finishLineFlag;

    /**
     * Constructor to set initial dimensions of the level, set the size of the hero,
     * and set the positions of the hero, platforms, enemies, clouds, and finish line.
     * @param heroX
     * @param cloudVelocity
     * @param stickmanSize
     */
    public LevelImpl (double heroX, double cloudVelocity, String stickmanSize, long levelNumber, long platformNumber,
                      double[] platform, long enemyNumber, double[] enemy, double finishLine) {
        if (stickmanSize.equalsIgnoreCase("tiny")) {
            size = 0.75;
        } else if (stickmanSize.equalsIgnoreCase("normal")) {
            size = 1;
        } else if (stickmanSize.equalsIgnoreCase("large")) {
            size = 1.5;
        } else if (stickmanSize.equalsIgnoreCase("giant")) {
            size = 2;
        } else {
            System.err.println("Incorrect stickman size input from JSON File.");
            System.exit(1);
        }
        this.height = 300;
        this.width = 640;
        this.floorHeight = 300;
        this.cloudVelocity = cloudVelocity;
        this.entities = new ArrayList<>();
        numClouds = 10;
        double cloud1x = 100;
        double cloud2x = 50;
        this.platformNumber = platformNumber;
        this.enemyNumber = enemyNumber;
        this.finishLine = finishLine;
        this.finish = false;
        hero = new Hero(heroX, floorHeight-34*size, size);
        clouds = new Cloud[numClouds];
        for (int i = 0; i < numClouds; i++) {
            if (i % 2 == 0) clouds[i] = new Cloud((cloud1x +100*i), height - 200, i);
            if (i % 2 == 1) clouds[i] = new Cloud(cloud2x +150*i, height - 200, i);
        }
        platformEntities = new PlatformEntity[(int) platformNumber];
        for (int i = 0; i < platformNumber*2; i += 2) {
            platformEntities[i/2] = new PlatformEntity(platform[i], platform[i+1]);
        }
        enemyEntities = new Enemy[(int) enemyNumber];
        for (int i = 0; i < enemyNumber; i++) {
            enemyEntities[i] = new Enemy(enemy[i], floorHeight-20, i);
        }
        finishLineFlag = new FlagEntity(finishLine, floorHeight-70);

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
        entities = new ArrayList<> ();
        entities.add(hero);
        for (int i = 0; i < numClouds; i ++) {
            entities.add(clouds[i]);
        }
        for (int i = 0; i < platformNumber; i++) {
            entities.add(platformEntities[i]);
        }
        for (int i = 0; i < enemyNumber; i++) {
            if (!enemyEntities[i].getRemove()) {
                entities.add(enemyEntities[i]);
                enemyEntities[i].move();
            }
        }
        entities.add(finishLineFlag);
        cloudMove();
        if (getHeroX() == finishLine) finish = true;
        for (int i = 1; i < entities.size()-1; i++) {
            top = false;
            if (checkCollision(hero, entities.get(i))) {
                handleCollision(entities.get(i));
                checkOnTop(hero, entities.get(i));
                i = entities.size()-1;
            }
        }
        if (!top && !jump) {
            if (!hero.desc(floorHeight)) {
                jump = false;
                jumpY = 0;
                top = true;
            }
        }
        if (left && hero.getXPos() > 0 && !finish &&!gameOver) {
            hero.moveLeft();
        }
        if (right && !finish && !gameOver) {
            hero.moveRight();
        }
        if (jump) {
            hero.jump(jumpY);
            jumpY++;
            top = false;
            if (jumpY >= 30) {
                if (!hero.desc(floorHeight)) {
                    jump = false;
                    jumpY = 0;
                    top = true;
                }
            }
        }
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
        if (jumpY != 0 || finish || gameOver) {
            return false;
        }
        jump = true;
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (finish || gameOver && !jump) {
            left = false;
            return false;
        }

        left = true;
        return true;
    }

    @Override
    public boolean moveRight() {
        if (finish || gameOver && !jump) {
            right = false;
            return false;
        }
            right = true;
        return true;
    }

    @Override
    public boolean stopMoving() {
        left = false;
        right = false;
        return true;
    }

    /**
     * Returns a boolean representing whether or not the game is finished.
     * @return
     */
    public boolean finish() {
        return finish;
    }

    /**
     * Handles movements for the clouds, according to the given cloud velocity.
     */
    private void cloudMove() {
        for (int i = 0; i < numClouds; i++) {
            clouds[i].move(cloudVelocity);
        }
    }

    /**
     * Checks for any intersection/collision between the hero entity, 'a',
     * and other entities, 'b'. If there is any intersection, true is returned.
     * @param a
     * @param b
     * @return
     */
    private boolean checkCollision (Entity a, Entity b) {
        return (a.getXPos() < (b.getXPos() + b.getWidth()) &&
                ((a.getXPos()+a.getWidth()) > b.getXPos())  &&
                (a.getYPos() < (b.getYPos() + b.getHeight())) &&
                ((a.getYPos() + a.getHeight()) > b.getYPos()));
    }

    /**
     * Checks for an intersection/collision between the bottom of 'a' and
     * the top of 'b'. A discrepancy of 3 pixels above or below is allowed.
     * @param a
     * @param b
     * @return
     */
    private boolean checkOnTop (Entity a, Entity b) {
        if ((a.getXPos() + a.getWidth() >= (b.getXPos()) ||
                ((a.getXPos()) <= b.getXPos() + b.getWidth())) &&
                (((a.getYPos() + a.getHeight()) >= b.getYPos()-3) &&
                ((a.getYPos() + a.getHeight()) <= b.getYPos()+3))) {
            this.top = true;
        }
        return top;
    }

    /**
     * Handles the collision between the hero and the given entity. If the hero is
     * above the entity, it will stay on top if it is a platform. Otherwise if the
     * entity is an enemy, it will kill it. Also prevents the movement into
     * intersections of the hero and entities.
     * @param entity
     */
    private void handleCollision(Entity entity) {
        if (checkOnTop(hero, entity)) {
            this.hero.setY(entity.getYPos()-this.hero.getHeight());
            jump = false;
            jumpY = 0;
            if (entity.isEnemy()) entity.remove();
        } else {
            if (entity.isEnemy()) hero.died();
            if (left) {
                hero.moveRight();
                left = false;
            }
            if (right) {
                hero.moveLeft();
                right = false;
            }
            if (jump) hero.desc(floorHeight);
        }
    }

    /**
     * Returns a boolean flag to determine whether the hero is dead or not.
     * @return
     */
    public boolean heroDead() {
        return hero.isDead();
    }

    /**
     * Sets a boolean flag to determine that the game is over.
     */
    public void gameOver() {
        this.gameOver = true;
    }
}
