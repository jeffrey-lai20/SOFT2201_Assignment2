package stickman.model;

public class FastEnemy implements EnemyStrategy {

    @Override
    public double move(double xPos, int eMove) {
        if (eMove < 50) {
            xPos += 2;
            return xPos;
        } else if (eMove <= 100) {
            xPos -=2;
            return xPos;
        }
        return xPos;
    }
}
