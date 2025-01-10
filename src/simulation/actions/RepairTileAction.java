package simulation.actions;

import general.FileHandler;
import general.GameManager;
import simulation.environment.Tile;

public class RepairTileAction extends EntityAction {

    public RepairTileAction(Tile currentTile) {
        super(currentTile);
    }

    @Override
    public void execute(GameManager manager) {
        currentTile.setIsDestroyed(false);
        FileHandler.getInstance().incrementStatisticsParameter("totalTilesRepaired");
    }
}
