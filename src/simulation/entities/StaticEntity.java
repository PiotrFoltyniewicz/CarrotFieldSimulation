package simulation.entities;

public abstract class StaticEntity extends Entity {

    protected int turnsLeft;
    private int initTurnsLeft;

    protected abstract boolean tryAction();

    public StaticEntity(int posX, int posY, int turnsLeft) {
        super(posX, posY);
        this.turnsLeft = turnsLeft;
        initTurnsLeft = turnsLeft;
    }

    public float getProgressPercent() {
        if (initTurnsLeft <= 0) {
            return 100.0f;
        }
        float percent = ((initTurnsLeft - turnsLeft) / (float) initTurnsLeft) * 100;
        return Math.max(0, Math.min(percent, 100));
    }
}
