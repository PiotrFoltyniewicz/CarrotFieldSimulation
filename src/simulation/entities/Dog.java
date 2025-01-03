package simulation.entities;

import simulation.actions.*;

public class Dog extends DynamicEntity {

    public Dog(int posX, int posY, int sightRange, int movementSpeed) {
        super(posX, posY, sightRange, movementSpeed);
    }

    private void killRabbitOnCurrentTile() {

    }

    private void spotNearestRabbit() {

    }

    @Override
    public EntityAction getAction() {
        return new NoneAction();
    }

}
