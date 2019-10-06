package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Enemy;
import static org.junit.Assert.*;

/**
 * Set of tests to check for functionality of enemy entities.
 */
public class EnemyTest {

    /**
     * Test for basic functionality.
     */
    @Test
    public void basicTest() {
        Enemy enemy = new Enemy(300, 300, 1);
        assertEquals(300, enemy.getYPos(), 0.01);
        assertEquals(300, enemy.getXPos(), 0.01);
        assertEquals(30, enemy.getWidth(), 0.01);
        assertEquals(30, enemy.getHeight(), 0.01);
    }

    /**
     * Tests for change in x position due to movement.
     */
    @Test
    public void moveTest() {
        Enemy enemy = new Enemy(300, 300, 0);
        enemy.move();
        assertNotEquals(300, enemy.getXPos());

        Enemy enemy2 = new Enemy(300, 300, 1);
        enemy2.move();
        assertNotEquals(300, enemy2.getXPos());

        Enemy enemy3 = new Enemy(300, 300, 2);
        enemy3.move();
        assertNotEquals(300, enemy3.getXPos());
    }

    /**
     * Tests for valid image path.
     */
    @Test
    public void imagePathTest() {
        Enemy enemy = new Enemy(300, 300, 1);
        assertSame("slimeBa.png", enemy.getImagePath());
    }

    /**
     * Tests to check if enemy flag is correct.
     */
    @Test
    public void isEnemyTest() {
        Enemy enemy = new Enemy(300, 300, 3);
        assertTrue(enemy.isEnemy());
    }

    /**
     * Tests to check if removal flag has been correctly signalled.
     */
    @Test
    public void removeTest() {
        Enemy enemy = new Enemy(300, 300, 3);
        enemy.remove();
        assertTrue(enemy.getRemove());
    }
}
