package simulation.entities;

import simulation.environment.Tile;

public abstract class StaticEntity extends Entity {

    protected int turnsLeft;
    private int initTurnsLeft;

    protected boolean tryAction() {
        if (turnsLeft == 0) {
            return false;
        }
        turnsLeft--;
        return turnsLeft == 0;
    }

    public StaticEntity(Tile currentTile, int turnsLeft) {
        super(currentTile);
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
