import org.junit.Test;
import stickman.model.Hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HeroTest {

    @Test
    public void basicTest() {
        Hero myHero = new Hero(20, 300, 1);
//    assertEquals(2, 1+1);
        assertEquals(300, myHero.getYPos(), 0.01);

    }


}
