package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Cloud;
import static org.junit.Assert.*;

/**
 * Set of tests to check the functionality of cloud entities.
 */
public class CloudTest {
    /**
     * Test for basic functionality.
     */
    @Test
    public void basicTest() {
        Cloud cloud = new Cloud(100, 200, 1);
        assertEquals(200, cloud.getYPos(), 0.01);
        assertEquals(100, cloud.getXPos(), 0.01);
    }

    /**
     * Tests for valid image path.
     */
    @Test
    public void imagePathTest() {
        Cloud cloud = new Cloud(100, 200, 0);
        assertSame("cloud_1.png", cloud.getImagePath());
        Cloud cloud2 = new Cloud(100, 200, 1);
        assertSame("cloud_2.png", cloud2.getImagePath());
    }

    /**
     * Tests for change in x position due to movement.
     */
    @Test
    public void moveTest() {
        Cloud cloud = new Cloud(100, 200, 0);
        cloud.move(3.6);
        assertNotEquals(100, cloud.getXPos());
    }
}
