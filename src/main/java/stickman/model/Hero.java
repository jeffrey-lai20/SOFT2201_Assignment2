package stickman.model;

public class Hero extends AbstractEntity implements HeroControl {

    public Hero(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos, size);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (entityName.equals("Hero")) {
            this.height = 34;
            this.width = 20;
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
    public void jump(int jumpTick) {
        if (jumpTick < 30) {
            this.yPos -= 2;
        } else if (jumpTick < 60) {
            this.yPos += 2;
        }
    }
}
