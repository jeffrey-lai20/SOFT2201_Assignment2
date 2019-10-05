package stickman.model;

public class Enemy extends AbstractEntity {

    private int eMove = 0;
    private EnemyContext enemCon;

    public Enemy(String entityName, double xPos, double yPos, double size) {
        super(entityName, xPos, yPos, size);
        this.entityName = entityName;
        this.xPos = xPos;
        this.yPos = yPos;
        if (this.entityName.equals("Enemy1")) {
            enemCon = new EnemyContext(new AccelerateEnemy());
            this.width = 30;
            this.height = 30;
        } else if (this.entityName.equals("Enemy2")) {
            enemCon = new EnemyContext(new FastEnemy());
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
    public boolean isEnemy() {
        return true;
    }
}
