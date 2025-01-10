package simulation.entities;

import simulation.actions.*;
import simulation.environment.Tile;

public class Rabbit extends StaticEntity {

    public Rabbit(Tile currentTile, int turnsLeft) {
        super(currentTile, turnsLeft);
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new EatCarrotAction(currentTile) : new NoneAction();
    }
}
