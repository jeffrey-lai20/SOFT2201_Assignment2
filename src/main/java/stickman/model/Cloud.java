package stickman.model;

public class Cloud extends AbstractEntity {

    public Cloud(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos, size);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (entityName.equals("Cloud1")) {
            this.height = 10;
            this.width = 40;
        } else if (entityName.equals("Cloud2")) {
            this.height = 22;
            this.width = 80;
        }
    }

    @Override
    public String getImagePath() {
        if (entityName.equals("Cloud1")) {
            return "cloud_1.png";
        } else if (entityName.equals("Cloud2")) {
            return "cloud_2.png";
        }
        return null;
    }

    public void move(double cloudVelocity) {
        xPos += cloudVelocity/60;
    }

}
