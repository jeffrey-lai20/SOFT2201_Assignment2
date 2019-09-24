package stickman.model;

/** Interface for Entity. */
public interface Entity {
    /** Returns the image's relative path for the entity. */
    String getImagePath();
    /** Returns the entity's x-position. */
    double getXPos();
    /** Returns the entity's y-position. */
    double getYPos();
    /** Returns the entity's height. */
    double getHeight();
    /** Returns the entity's width. */
    double getWidth();
    /** Returns the layer of the entity. */
    Layer getLayer();
    /** Enumeration for Layer's possible named constants. */
    enum Layer{
        BACKGROUND, FOREGROUND, EFFECT
    }
}
