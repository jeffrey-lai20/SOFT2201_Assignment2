package stickman.model.Entity;

public class AbstractEntity implements Entity {

    double xPos;
    double yPos;
    double height;
    double width;
    Layer layer;
    boolean remove = false;

    public AbstractEntity (double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    @Override
    public boolean isEnemy() {
        return false;
    }

    @Override
    public void remove() {
        this.remove = true;
    }

    @Override
    public boolean getRemove() {
        return this.remove;
    }

    @Override
    public String getImagePath() {
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
}
