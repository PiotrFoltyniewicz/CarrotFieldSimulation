package simulation.actions;

import general.FileHandler;
import general.GameManager;

public class RepairTileAction extends EntityAction {

    public RepairTileAction(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentPosition.x;
        int y = currentPosition.y;

        manager.getField().getTile(x, y).setIsDestroyed(false);
        FileHandler.getInstance().incrementStatisticsParameter("totalTilesRepaired");
    }
}
