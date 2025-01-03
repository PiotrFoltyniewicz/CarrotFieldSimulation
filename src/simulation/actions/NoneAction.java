package simulation.actions;

import general.GameManager;

public class NoneAction extends EntityAction {

    public NoneAction() {
        super(-1, -1);
    }

    @Override
    public void execute(GameManager manager) {
    }
}
