package simulation.entities;

import java.awt.Point;
import java.util.List;
import simulation.environment.*;

public abstract class DynamicEntity extends Entity {

    protected List<Tile> surroundingTiles;
    protected Point targetPosition;
    protected int sightRange;
    protected int movementSpeed;

    public void setSurroundingTiles(List<Tile> tiles) {
        surroundingTiles = tiles;
    }

    public DynamicEntity(int posX, int posY, int sightRange, int movementSpeed) {
        super(posX, posY);
        this.sightRange = sightRange;
        this.movementSpeed = movementSpeed;
    }

}
