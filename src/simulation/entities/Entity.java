package simulation.entities;

import java.awt.*;
import simulation.actions.*;
import simulation.environment.Tile;

public abstract class Entity {

    protected Tile currentTile;

    public Entity(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Point getPosition() {
        return currentTile.getPosition();
    }

    public abstract EntityAction getAction();
}
