package simulation.actions;

import general.FileHandler;
import general.GameManager;

public class CarrotReadyAction extends EntityAction {

    public CarrotReadyAction(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void execute(GameManager manager) {
        FileHandler.getInstance().incrementStatisticsParameter("totalCarrotsFullyGrown");
    }
}
