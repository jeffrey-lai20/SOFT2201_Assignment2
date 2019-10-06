package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Entity;
import stickman.model.Entity.FlagEntity;
import static org.junit.Assert.*;

/**
 * Set of tests to check flag entity.
 */
public class FlagTest {

    /**
     * Test for basic functionality.
     */
    @Test
    public void basicTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        assertEquals(1000, flag.getXPos(), 0.01);
        assertEquals(230, flag.getYPos(), 0.01);
    }

    /**
     * Tests for valid image path.
     */
    @Test
    public void imagePathTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        assertSame("flag.png", flag.getImagePath());
    }

    /**
     * Tests to check correct layering of flag to be in background.
     */
    @Test
    public void layerTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        Entity.Layer layer = Entity.Layer.BACKGROUND;
        assertSame(layer, flag.getLayer());

    }

}
