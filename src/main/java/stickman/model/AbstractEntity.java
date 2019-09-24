package stickman.model;

public class AbstractEntity implements Entity {

    String entityName;
    double xPos;
    double yPos;
    double height;
    double width;
    Layer layer;

    public AbstractEntity (String entityName, double xPos, double yPos, double size) {
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getImagePath() {
        if (entityName.equals("Platform1")) {
            return "tree.png";
        } else if (entityName.equals("Platform2")) {
            return "foot_tile.png";
        }
        return null;
    }

    @Override
    public double getXPos() {
        return this.xPos;
    }

    @Override
    public double getYPos() {
        return this.yPos;
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
    public Layer getLayer() {
        this.layer = Layer.FOREGROUND;
        return this.layer;
    }

    public void setxPos(double newX) {
        this.xPos = newX;
    }

    public void setyPos(double newY) {
        this.yPos = newY;
    }
}
