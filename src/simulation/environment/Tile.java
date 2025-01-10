package simulation.environment;

import java.awt.Point;

public class Tile {

    private Point position;
    private boolean hasRabbit;
    private boolean hasCarrot;
    private boolean isDestroyed;

    public Tile(int posX, int posY) {
        position = new Point(posX, posY);
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

    public synchronized void setHasRabbit(boolean hasRabbit) {
        this.hasRabbit = hasRabbit;
    }

    public synchronized void setHasCarrot(boolean hasCarrot) {
        this.hasCarrot = hasCarrot;
    }

    public synchronized void setIsDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public Point getPosition() {
        return position;
    }
}
