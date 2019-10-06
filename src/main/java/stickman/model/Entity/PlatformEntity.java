package stickman.model.Entity;

public class PlatformEntity extends AbstractEntity {
    public PlatformEntity(double xPos, double yPos) {
        super(xPos, yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 91;
        this.height = 16;
    }

    @Override
    public String getImagePath() {
        return "foot_tile_medium.png";
    }

}
