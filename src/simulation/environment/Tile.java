package simulation.environment;

import java.awt.Point;
import java.util.concurrent.locks.ReentrantLock;

public class Tile {

    private Point position;
    private boolean hasRabbit;
    private boolean hasCarrot;
    private boolean isDestroyed;
    private final ReentrantLock lock;

    public Tile(int posX, int posY) {
        position = new Point(posX, posY);
        lock = new ReentrantLock();
    }

    // Lock getter
    public ReentrantLock getLock() {
        return lock;
    }

    public boolean getHasRabbit() {
        return hasRabbit;
    }

    public boolean getHasCarrot() {
        return hasCarrot;
    }

    public boolean getIsDestroyed() {
        return isDestroyed;
    }

    public void setHasRabbit(boolean hasRabbit) {
        this.hasRabbit = hasRabbit;
    }

    public void setHasCarrot(boolean hasCarrot) {
        this.hasCarrot = hasCarrot;
    }

    public void setIsDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public Point getPosition() {
        return position;
    }
}
