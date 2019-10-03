package stickman.model;

public class Enemy extends AbstractEntity implements EnemyMove {

    private int eMove = 0;

    public Enemy(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos, size);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (this.entityName.equals("Enemy1")) {
            this.width = 30;
            this.height = 30;
        } else if (this.entityName.equals("Enemy2")) {
            this.width = 30;
            this.height = 30;
        }
    }

    public void move() {
        System.out.println(eMove);
        if (eMove <= 50) {
            moveRight();
        } else if (eMove < 100) {
            moveLeft();
        } else {
            eMove = 0;
        }
        eMove++;
    }


    @Override
    public void moveLeft() {
        this.xPos--;
    }

    @Override
    public void moveRight() {
        this.xPos++;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }
}
