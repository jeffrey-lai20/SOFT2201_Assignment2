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
        if (this.entityName.equals("Platform1")) {
            this.width = 16;
            this.height = 16;
        } else if (this.entityName.equals("Platform2")) {
            this.width = 16;
            this.height = 16;
        }

    }

    @Override
    public String getImagePath() {
        if (entityName.equals("Platform1")) {
            return "foot_tile.png";
        } else if (entityName.equals("Platform2")) {
            return "foot_tile.png";
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
