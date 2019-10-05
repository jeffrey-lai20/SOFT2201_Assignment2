package stickman.model;

public class AbstractEntity implements Entity {

    String entityName;
    double xPos;
    double yPos;
    double height;
    double width;
    Layer layer;
    boolean remove = false;

    public AbstractEntity (String entityName, double xPos, double yPos) {
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (this.entityName.contains("Platform")) {
            this.width = 91;
            this.height = 16;
        }
    }

    public boolean isEnemy() {
        return false;
    }

    public void remove() {
        this.remove = true;
    }

    public boolean getRemove() {
        return this.remove;
    }

    @Override
    public String getImagePath() {
        if (entityName.contains("Platform")) {
            return "foot_tile_medium.png";
        } else if (entityName.equals("FinishLineFlag")) {
            return "flag.png";
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
        if (entityName.equals("FinishLineFlag")) {
            this.layer = Layer.BACKGROUND;
        }
        return this.layer;
    }
}
