package stickman.model;

public class NormalEnemy implements EnemyStrategy {
    @Override
    public double move(double xPos, int eMove) {
        if (eMove < 50) {
            xPos += 1;
            return xPos;
        } else if (eMove <= 100) {
            xPos -= 1;
            return xPos;
        }
        return xPos;
    }
}
