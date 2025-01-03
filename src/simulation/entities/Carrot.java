package simulation.entities;

import simulation.actions.*;

public class Carrot extends StaticEntity {

    public Carrot(int posX, int posY, int turnsLeft) {
        super(posX, posY, turnsLeft);
    }

    @Override
    protected boolean tryAction() {
        if (turnsLeft == 0) {
            return false;
        }
        turnsLeft--;
        return turnsLeft == 0;
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new CarrotReadyAction(position.x, position.y) : new NoneAction();
    }
}
