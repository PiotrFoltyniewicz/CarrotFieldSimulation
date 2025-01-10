package simulation.entities;

import simulation.actions.*;
import simulation.environment.Tile;

public class Carrot extends StaticEntity {

    public Carrot(Tile currentTile, int turnsLeft) {
        super(currentTile, turnsLeft);
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new CarrotReadyAction(currentTile) : new NoneAction();
    }
}
