package EntityTests;

import org.junit.Test;
import stickman.model.Entity.Hero;
import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void basicTest() {
        Hero myHero = new Hero(20, 300, 1);
        assertEquals(300, myHero.getYPos(), 0.01);
        assertEquals(20, myHero.getXPos(), 0.01);
    }

    @Test
    public void moveRightTest() {
        Hero myHero = new Hero(20, 300, 1);
        myHero.moveRight();
        assertTrue(myHero.getXPos() > 20);
    }

    @Test
    public void moveLeftTest() {
        Hero myHero = new Hero(20, 300, 1);
        myHero.moveLeft();
        assertTrue(myHero.getXPos() < 20);
    }

    @Test
    public void jumpTest() {
        Hero myHero = new Hero(20, 300, 1);
        myHero.jump(0);
        assertTrue(myHero.getYPos() < 300);
    }

    @Test
    public void descTest() {
        Hero myHero = new Hero(20, 350, 1);
        assertFalse(myHero.desc(300));
    }

    @Test
    public void setYTest() {
        Hero myHero = new Hero(20, 300, 1);
        myHero.setY(350);
        assertEquals(350, myHero.getYPos(), 0.01);
    }

    @Test
    public void deadTest() {
        Hero myHero = new Hero(20, 300, 1);
        assertFalse(myHero.isDead());
        myHero.died();
        assertTrue(myHero.isDead());
    }

    @Test
    public void getImageTest() {
        Hero myHero = new Hero(20, 300, 1);
        assertSame("ch_stand1.png", myHero.getImagePath());
    }

    @Test
    public void sizeTest() {
        Hero myHero = new Hero(20, 300, 0.75);
        assertEquals(34*0.75, myHero.getHeight(), 0.01);
        assertEquals(20*0.75, myHero.getWidth(), 0.01);

        Hero myHero2 = new Hero(20, 300, 1);
        assertEquals(34, myHero2.getHeight(), 0.01);
        assertEquals(20, myHero2.getWidth(), 0.01);

        Hero myHero3 = new Hero(20, 300, 1.5);
        assertEquals(34*1.5, myHero3.getHeight(), 0.01);
        assertEquals(20*1.5, myHero3.getWidth(), 0.01);

        Hero myHero4 = new Hero(20, 300, 2);
        assertEquals(34*2, myHero4.getHeight(), 0.01);
        assertEquals(20*2, myHero4.getWidth(), 0.01);



    }
}
