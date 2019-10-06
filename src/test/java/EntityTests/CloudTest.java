package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Cloud;
import static org.junit.Assert.*;

public class CloudTest {
    @Test
    public void basicTest() {
        Cloud cloud = new Cloud(100, 200, 1);
        assertEquals(200, cloud.getYPos(), 0.01);
        assertEquals(100, cloud.getXPos(), 0.01);
    }

    @Test
    public void imagePathTest() {
        Cloud cloud = new Cloud(100, 200, 0);
        assertSame("cloud_1.png", cloud.getImagePath());
        Cloud cloud2 = new Cloud(100, 200, 1);
        assertSame("cloud_2.png", cloud2.getImagePath());
    }

    @Test
    public void moveTest() {
        Cloud cloud = new Cloud(100, 200, 0);
        cloud.move(3.6);
        assertNotEquals(100, cloud.getXPos());
    }
}
