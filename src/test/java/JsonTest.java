import org.junit.Test;
import stickman.model.JsonReader;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Set of tests for JsonReader
 */
public class JsonTest {

    /**
     * Test for basic functionality.
     */
    @Test
    public void basicTest() throws FileNotFoundException {
        JsonReader reader = new JsonReader("src/test/resources/jsonTest.json");
        assertEquals("normal", reader.getStickmanSize());
        assertEquals(20, reader.getxPos(), 0.01);
        assertEquals(3.2, reader.getCloudVelocity(), 0.01);
    }

    /**
     * Tests for taking input of platform entities.
     * @throws FileNotFoundException
     */
    @Test
    public void platformTest() throws FileNotFoundException {
        JsonReader reader = new JsonReader("src/test/resources/jsonTest.json");
        assertEquals(2, reader.getPlatformNumber(), 0.01);
        assertNotNull(reader.getPlatform());
    }

    /**
     * Tests for taking input of enemy entities.
     * @throws FileNotFoundException
     */
    @Test
    public void enemyTest() throws FileNotFoundException {
        JsonReader reader = new JsonReader("src/test/resources/jsonTest.json");
        assertEquals(2, reader.getEnemyNumber(), 0.01);
        assertNotNull(reader.getEnemy());
    }

    /**
     * Tests for taking input of level number and finish line x-position.
     * @throws FileNotFoundException
     */
    @Test
    public void miscTest() throws FileNotFoundException {
        JsonReader reader = new JsonReader("src/test/resources/jsonTest.json");
        assertEquals(1, reader.getLevelNumber(), 0.01);
        assertEquals(1280, reader.getFinishLine(), 0.01);
    }
}
