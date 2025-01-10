package simulation.entities;

import java.util.List;
import simulation.environment.*;

public abstract class DynamicEntity extends Entity {

    protected List<Tile> surroundingTiles;
    protected int sightRange;

    public void setSurroundingTiles(List<Tile> tiles) {
        surroundingTiles = tiles;
    }

    public DynamicEntity(Tile currentTile, int sightRange) {
        super(currentTile);
        this.sightRange = sightRange;
    }

    public int getSightRange() {
        return sightRange;
    }

}
