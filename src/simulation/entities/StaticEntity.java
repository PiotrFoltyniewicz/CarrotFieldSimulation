package simulation.entities;

public abstract class StaticEntity extends Entity {

    protected int turnsLeft;

    public abstract boolean tryAction();

    public StaticEntity(int posX, int posY, int turnsLeft) {
        super(posX, posY);
        this.turnsLeft = turnsLeft;
    }
}
