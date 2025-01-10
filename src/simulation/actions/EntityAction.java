package simulation.actions;

import general.GameManager;
import simulation.environment.Tile;

public abstract class EntityAction {

    protected Tile currentTile;

    public EntityAction(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public abstract void execute(GameManager manager);
}
