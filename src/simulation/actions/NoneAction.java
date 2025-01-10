package simulation.actions;

import general.GameManager;
import simulation.environment.Tile;

public class NoneAction extends EntityAction {

    public NoneAction() {
        super(new Tile(-1, -1));
    }

    @Override
    public void execute(GameManager manager) {
    }
}
