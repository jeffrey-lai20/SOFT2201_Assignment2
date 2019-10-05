package stickman.model;

public class Enemy extends AbstractEntity {

    private int eMove = 0;
    private EnemyContext enemCon;

    public Enemy(String entityName, double xPos, double yPos, double num) {
        super(entityName, xPos, yPos);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (this.entityName.contains("Enemy")) {
            if (num % 3 == 0) {
                enemCon = new EnemyContext(new AccelerateEnemy());
            }
            if (num % 3 == 1) {
                enemCon = new EnemyContext(new FastEnemy());
            }
            if (num % 3 == 2) {
                enemCon = new EnemyContext(new NormalEnemy());
            }
            this.width = 30;
            this.height = 30;
        }
    }

    public void move() {
        if (eMove <= 100) {
            if (eMove == 100) {
                eMove = 0;
            }
            this.xPos = enemCon.enemyMove(xPos, eMove);
            eMove++;
        }
    }

    @Override
    public String getImagePath() {
        if (this.entityName.contains("Enemy")) {
            return "slimeBa.png";
        }
        return null;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }
}
