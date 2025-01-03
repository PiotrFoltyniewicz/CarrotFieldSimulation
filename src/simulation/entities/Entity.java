package simulation.entities;

import java.awt.*;
import simulation.actions.*;

public abstract class Entity {

    protected Point position;

    public Entity(int posX, int posY) {
        position = new Point(posX, posY);
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public Point getPosition() {
        return position;
    }

    public abstract EntityAction getAction();
}
