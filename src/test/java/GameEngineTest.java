import org.junit.Test;
import stickman.model.GameEngineImpl;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class GameEngineTest {
    @Test
    public void basicTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        engine.tick();
        assertNotEquals(0, engine.getStartTime());
    }

    @Test
    public void moveTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertTrue(engine.jump());
        assertTrue(engine.moveLeft());
        assertTrue(engine.moveRight());
        assertTrue(engine.stopMoving());
    }

    @Test
    public void finishTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.finish());
    }

    @Test
    public void getLives() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertEquals(3, engine.getLives());
    }

    @Test
    public void gameOverTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.gameOver());
    }

    @Test
    public void heroDeadTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.heroDead());
    }

    @Test
    public void restartLevelTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        engine.restartLevel();
        assertNotEquals(0, engine.getStartTime());
        assertNotNull(engine.getCurrentLevel());

    }

}
