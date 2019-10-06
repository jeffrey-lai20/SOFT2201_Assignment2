package stickman.model.Entity;

public class FlagEntity extends AbstractEntity {
    public FlagEntity( double xPos, double yPos) {
        super(xPos, yPos);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getImagePath() {
        return "flag.png";
    }

    @Override
    public Layer getLayer() {
        layer = Layer.BACKGROUND;
        return layer;
    }
}
