package simulation.actions;

import general.FileHandler;
import general.GameManager;
import simulation.environment.Tile;

public class CarrotReadyAction extends EntityAction {

    public CarrotReadyAction(Tile currentTile) {
        super(currentTile);
    }

    @Override
    public void execute(GameManager manager) {
        FileHandler.getInstance().incrementStatisticsParameter("totalCarrotsFullyGrown");
    }
}
