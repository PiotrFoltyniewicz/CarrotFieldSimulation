package simulation.entities;

import simulation.actions.*;

public class Carrot extends StaticEntity {

    public Carrot(int posX, int posY, int turnsLeft) {
        super(posX, posY, turnsLeft);
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new CarrotReadyAction(position.x, position.y) : new NoneAction();
    }
}
