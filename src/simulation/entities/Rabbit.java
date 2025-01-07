package simulation.entities;

import simulation.actions.*;

public class Rabbit extends StaticEntity {

    public Rabbit(int posX, int posY, int turnsLeft) {
        super(posX, posY, turnsLeft);
    }

    @Override
    public EntityAction getAction() {
        return tryAction() ? new EatCarrotAction(position.x, position.y) : new NoneAction();
    }
}
