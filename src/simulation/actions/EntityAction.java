package simulation.actions;

import general.GameManager;
import java.awt.Point;

public abstract class EntityAction {

    protected Point currentPosition;

    public EntityAction(int posX, int posY) {
        currentPosition = new Point(posX, posY);
    }

    public abstract void execute(GameManager manager);
}
