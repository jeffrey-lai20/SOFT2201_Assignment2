package stickman.model.Entity;

public class EnemyContext {
    private EnemyStrategy strategy;

    public EnemyContext(EnemyStrategy strategy) {
       this.strategy = strategy;
    }

    public double enemyMove(double xPos, int eMove) {
        return strategy.move(xPos,  eMove);
    }
}
