package stickman.model;

public class FastEnemy implements EnemyStrategy {

    @Override
    public double move(double xPos, int eMove) {
        if (eMove < 25) {
            xPos += 3;
            return xPos;
        } else if (eMove < 50) {
            xPos -= 3;
            return xPos;
        } else if (eMove < 75) {
            xPos += 3;
            return xPos;
        } else if (eMove <= 100) {
            xPos -=3;
            return xPos;
        }
        return xPos;
    }
}
