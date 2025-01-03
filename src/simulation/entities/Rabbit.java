package simulation.entities;

import simulation.actions.*;

public class Rabbit extends StaticEntity {

    public Rabbit(int posX, int posY, int turnsLeft) {
        super(posX, posY, turnsLeft);
    }

    @Override
    protected boolean tryAction() {
        turnsLeft--;
        return turnsLeft <= 0;
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new EatCarrotAction() : new NoneAction();
    }
}
