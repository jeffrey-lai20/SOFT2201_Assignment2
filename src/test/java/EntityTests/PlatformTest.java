package EntityTests;

import org.junit.Test;
import stickman.model.Entity.PlatformEntity;
import static org.junit.Assert.*;

public class PlatformTest {

    @Test
    public void basicTest() {
        PlatformEntity platform = new PlatformEntity(100, 500);
        assertEquals(500, platform.getYPos(), 0.01);
        assertEquals(100, platform.getXPos(), 0.01);
    }

    @Test
    public void imagePathTest() {
        PlatformEntity platform = new PlatformEntity(100, 500);
        assertSame("foot_tile_medium.png", platform.getImagePath());
    }
}
