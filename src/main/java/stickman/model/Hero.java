package stickman.model;

public class Hero extends AbstractEntity implements HeroControl {
//    private String entityName;
//    private double xPos;
//    private double yPos;
//    private double height;
//    private double width;

    public Hero(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos, size);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (entityName.equals("Hero")) {
            this.height = size;
            this.width = size;
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
    public void jump() {
        this.yPos--;
    }

    @Override
    public void desc() {
        this.yPos++;
    }
}
