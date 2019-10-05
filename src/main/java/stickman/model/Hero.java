package stickman.model;

public class Hero extends AbstractEntity implements HeroControl {

    boolean dead = false;

    public Hero(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (entityName.equals("Hero")) {
            this.height = 34*size;
            this.width = 20*size;
        }
    }

    @Override
    public String getImagePath() {
        if (entityName.equals("Hero") ) {
            return "ch_stand1.png";
        }
        return null;
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
    public void jump(int jumpY) {
        if (jumpY < 30) {
            this.yPos -= 3;
        }
    }

    @Override
    public boolean desc(double floorHeight) {
        if (this.yPos > floorHeight-this.height) {
            return false;
        }
        this.yPos += 3;
        return true;
    }

    public void setY(double newY) {
        this.yPos = newY;
    }

    public void died() {
        this.dead = true;
    }

    public boolean isDead() {
        return this.dead;
    }
}
