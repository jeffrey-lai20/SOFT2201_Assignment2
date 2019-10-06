package stickman.model.Entity;

public class Cloud extends AbstractEntity {
    private double num;
    public Cloud(double xPos, double yPos, double num) {
        super(xPos, yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        this.num = num;
        if (num % 2 == 0) {
            this.height = 10;
            this.width = 40;
        } else if (num % 2 == 1) {
            this.height = 22;
            this.width = 80;
        }
    }

    @Override
    public String getImagePath() {
        if (num % 2 == 0) {
            return "cloud_1.png";
        } else if (num % 2 == 1) {
            return "cloud_2.png";
        }
        return null;
    }

    public void move(double cloudVelocity) {
        xPos += cloudVelocity/60;
    }

}
