package stickman.model;

/** Interface for GameEngine. */
public interface GameEngine {
    /** Returns the current level. */
    Level getCurrentLevel();
    /** Starts the level of the game. */
    void startLevel();
    /** Tells the hero jump. */
    boolean jump();
    /** Tells the hero to move left. */
    boolean moveLeft();
    /** Tells the hero to move right. */
    boolean moveRight();
    /** Tells the hero to stop moving. */
    boolean stopMoving();
    /** Tells the model to update the frame. */
    void tick();
}
