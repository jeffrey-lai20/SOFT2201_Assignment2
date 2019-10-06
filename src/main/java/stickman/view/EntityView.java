package stickman.view;

import javafx.scene.Node;
import stickman.model.Entity.Entity;

/** Interface for EntityView. */
public interface EntityView {
    /** Updates the entity's image and dimensions. */
    void update(double xViewportOffset);

    /** Compares entities. */
    boolean matchesEntity(Entity entity);

    /** Marks an entity for deletion. */
    void markForDelete();

    /** Returns the node. */
    Node getNode();

    /** Checks if an entity is marked for deletion. */
    boolean isMarkedForDelete();
}
