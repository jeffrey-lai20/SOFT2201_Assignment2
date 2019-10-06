import org.junit.Test;
import stickman.model.GameEngineImpl;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Set of tests for GameEngineImpl.
 */
public class GameEngineTest {

    /**
     * Test for basic functionality.
     */
    @Test
    public void basicTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        engine.tick();
        assertNotEquals(0, engine.getStartTime());
    }

    /**
     * Tests for proper movement of the hero.
     * @throws FileNotFoundException
     */
    @Test
    public void moveTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertTrue(engine.jump());
        assertTrue(engine.moveLeft());
        assertTrue(engine.moveRight());
        assertTrue(engine.stopMoving());
    }

    /**
     * Tests for whether or not the game has finished.
     * @throws FileNotFoundException
     */
    @Test
    public void finishTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.finish());
    }

    /**
     * Tests for correct number of lives.
     * @throws FileNotFoundException
     */
    @Test
    public void getLives() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertEquals(3, engine.getLives());
    }

    /**
     * Tests for correct signalling if the game is over.
     * @throws FileNotFoundException
     */
    @Test
    public void gameOverTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.gameOver());
    }

    /**
     * Tests for correct signalling if the hero is dead.
     * @throws FileNotFoundException
     */
    @Test
    public void heroDeadTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        assertFalse(engine.heroDead());
    }

    /**
     * Tests for events after restarting the level.
     * @throws FileNotFoundException
     */
    @Test
    public void restartLevelTest() throws FileNotFoundException {
        GameEngineImpl engine = new GameEngineImpl("src/test/resources/jsonTest.json");
        engine.restartLevel();
        assertNotEquals(0, engine.getStartTime());
        assertNotNull(engine.getCurrentLevel());
    }
}
