package simulation.entities;

import java.awt.Point;
import java.util.List;
import simulation.environment.*;

public abstract class DynamicEntity extends Entity {

    protected List<Tile> surroundingTiles;
    protected int sightRange;

    public void setSurroundingTiles(List<Tile> tiles) {
        surroundingTiles = tiles;
    }

    protected Tile getCurrentTile() {
        for (Tile tile : surroundingTiles) {
            Point tilePos = tile.getPosition();
            if (tilePos.x == position.x && tilePos.y == position.y) {
                return tile;
            }
        }
        return null;
    }

    public DynamicEntity(int posX, int posY, int sightRange) {
        super(posX, posY);
        this.sightRange = sightRange;
    }

    public int getSightRange() {
        return sightRange;
    }

}
