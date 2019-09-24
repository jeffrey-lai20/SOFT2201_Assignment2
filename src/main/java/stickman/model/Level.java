package stickman.model;

import java.util.List;

/** Interface for Level. */
public interface Level {
    /** Returns a list of entities in the current level. */
    List<Entity> getEntities();
    /** Returns the height of the level. */
    double getHeight();
    /** Returns the width of the level. */
    double getWidth();

    /**
     * Updates the position of entities for each frame. Cloud positions
     * are updated with the given cloud velocity, and speed up or slow down
     * relative to the hero's movement. Handles the hero's movement and
     * restricts double jumps, moving past boundaries, and sets the camera
     * to follow the hero.
     */
    void tick();

    /** Returns the floor's height. */
    double getFloorHeight();
    /** Returns the hero's x-position. */
    double getHeroX();

    /** Tells the hero to jump. */
    boolean jump();
    /** Tells the hero to move left. */
    boolean moveLeft();
    /** Tells the hero to move right. */
    boolean moveRight();
    /** Tells the hero to stop moving left or right. */
    boolean stopMoving();
}
