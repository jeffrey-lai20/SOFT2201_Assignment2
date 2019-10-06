package stickman.model.Entity;

public interface HeroControl {
    void moveLeft();
    void moveRight();
    void jump(int jumpY);
    boolean desc(double floorHeight);
}
