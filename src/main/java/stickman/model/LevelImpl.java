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
    private int jumpY = 0;
    private boolean top;

    private Hero hero;
    private Cloud cloud1;
    private Cloud cloud2;
    private AbstractEntity platform1;
    private AbstractEntity platform2;
    private AbstractEntity finishLineFlag;
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
        cloud1 = new Cloud("Cloud1", cloud1x, height - 200, size);
        cloud2 = new Cloud("Cloud2", cloud2x, height - 230, size);
        platform1 = new AbstractEntity("Platform1",platform[0], platform[1], size);
        platform2 = new AbstractEntity("Platform2",platform[2], platform[3], size);
        finishLineFlag = new AbstractEntity("FinishLineFlag",finishLine, floorHeight-70, size);

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
        //Create a for loop to add numerous clouds and platforms and enemies later
        entities.add(0,hero);
        entities.add(1, cloud1);
        entities.add(2, cloud2);
        entities.add(3, platform1);
        entities.add(4, platform2);
        entities.add(5, finishLineFlag);

        cloudMove();
        if (getHeroX() == finishLine) finish = true;
//        for (int i = 1; i < entities.size()-1; i++) {
//            if (checkCollision(hero, entities.get(i))) {
//                handleCollision(entities.get(i));
//                jumpY = 0;
//                col = true;
//            } else {
//                col = false;
//            }
//        }


        for (int i = 1; i < entities.size()-1; i++) {
            if (checkCollision(hero, entities.get(i))) {
                handleCollision(entities.get(i));
                if (!checkOnTop(hero, entities.get(i)) && hero.getHeight() != floorHeight) {
                    hero.desc(floorHeight);
                }

            }
        }

        if ((left || right) && !jump && top) {
            top = false;
        }
        if (!top && !jump) {
            if (!hero.desc(floorHeight)) {
                jump = false;
                jumpY = 0;
                top = true;
            }

        }


        if (left && hero.getXPos() > 0 && !finish) {
            hero.moveLeft();
        }
        if (right && !finish) {
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
        if (jumpY != 0 || finish) {
            return false;
        }
        jump = true;
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (finish) {
            left = false;
            return false;
        }

        left = true;
        return true;
    }

    @Override
    public boolean moveRight() {
        if (finish) {
            left = false;
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

    public boolean finish() {
        return finish;
    }

    private void cloudMove() {
        cloud1.move(cloudVelocity);
        cloud2.move(cloudVelocity);
    }

    private boolean checkCollision (Entity a, Entity b) {
        return (a.getXPos() < (b.getXPos() + b.getWidth()) &&
                ((a.getXPos()+a.getWidth()) > b.getXPos())  &&
                (a.getYPos() < (b.getYPos() + b.getHeight())) &&
                ((a.getYPos() + a.getHeight()) > b.getYPos()));
    }

    private boolean checkOnTop (Entity a, Entity b) {
//        System.out.println("xpos "+a.getXPos());
//        System.out.println("xpos and width "+a.getXPos()+a.getWidth());
//        System.out.println("e xpos "+b.getXPos());
//        System.out.println("e xpos & width"+b.getXPos() + b.getWidth());
//        System.out.println("y & height " + (a.getYPos() + a.getHeight()));
//        System.out.println("bypos "+b.getYPos());
//        top = true;
//
//        System.out.println("idk man " + (a.getXPos() + a.getWidth() < (b.getXPos())));
//        if        ((a.getXPos() + a.getWidth() < (b.getXPos())) &&
//                ((a.getXPos()) > b.getXPos() + b.getWidth()) && (((a.getYPos() + a.getHeight()) >= b.getYPos()-3) &&
//                    ((a.getYPos() + a.getHeight()) <= b.getYPos()+3))) {
//            System.out.println("Here");
//            top = false;
//
//        }
        return
                ((a.getXPos() + a.getWidth() >= (b.getXPos())) &&
                ((a.getXPos()) <= b.getXPos() + b.getWidth()) &&
                ((a.getYPos() + a.getHeight()) >= b.getYPos()-3) &&
                        ((a.getYPos() + a.getHeight()) <= b.getYPos()+3));
    }

    private void handleCollision(Entity entity) {
        System.out.println("jump" + jump);
        System.out.println(checkOnTop(hero, entity));
//        if (jump) {
//            this.hero.jump(jumpY);
//
//        }
        if (checkOnTop(hero, entity)) {
//            jump = this.hero.desc(entity.getYPos()-3);
            this.hero.setY(entity.getYPos()-this.hero.getHeight());
            jump = false;
            jumpY = 0;
            top = true;

        } else {
//            jump = this.hero.desc(floorHeight);
            if (left) {
                hero.moveRight();
                left = false;
            }
            if (right) {
                hero.moveLeft();
                right = false;
            }

            System.out.println(checkOnTop(hero, entity));
        }






    }

}
