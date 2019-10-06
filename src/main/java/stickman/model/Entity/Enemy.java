package stickman.model.Entity;

public class Enemy extends AbstractEntity {

    private int eMove = 0;
    private EnemyContext enemCon;

    public Enemy(double xPos, double yPos, double num) {
        super(xPos, yPos);
        this.xPos = xPos;
        this.yPos = yPos;
        if (num % 3 == 0) {
            enemCon = new EnemyContext(new NormalEnemy());
        }
        if (num % 3 == 1) {
            enemCon = new EnemyContext(new FastEnemy());
        }
        if (num % 3 == 2) {
            enemCon = new EnemyContext(new AccelerateEnemy());
        }
        this.width = 30;
        this.height = 30;

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
            return "slimeBa.png";
    }

    @Override
    public boolean isEnemy() {
        return true;
    }
}
