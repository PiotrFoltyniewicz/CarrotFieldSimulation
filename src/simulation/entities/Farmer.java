package simulation.entities;

import simulation.actions.*;

public class Farmer extends DynamicEntity {

    private Dog ownedDog;

    public Farmer(int posX, int posY, int sightRange, int movementSpeed) {
        super(posX, posY, sightRange, movementSpeed);
        ownedDog = new Dog(posX, posY, sightRange, movementSpeed);
    }

    private void repairCurrentTile() {

    }

    private void spotNearestRabbit() {

    }

    private void plantCarrot() {

    }

    @Override
    public EntityAction getAction() {
        return new NoneAction();
    }
}
