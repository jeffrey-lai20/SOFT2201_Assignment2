package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Entity;
import stickman.model.Entity.FlagEntity;
import static org.junit.Assert.*;

public class FlagTest {

    @Test
    public void basicTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        assertEquals(1000, flag.getXPos(), 0.01);
        assertEquals(230, flag.getYPos(), 0.01);
    }

    @Test
    public void imagePathTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        assertSame("flag.png", flag.getImagePath());
    }

    @Test
    public void layerTest() {
        FlagEntity flag = new FlagEntity(1000, 230);
        Entity.Layer layer = Entity.Layer.BACKGROUND;
        assertSame(layer, flag.getLayer());

    }

}
