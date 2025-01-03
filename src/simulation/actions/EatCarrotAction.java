package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.List;
import simulation.entities.*;

public class EatCarrotAction extends EntityAction {

    public EatCarrotAction(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentPosition.x;
        int y = currentPosition.y;
        List<Entity> entitiesOnTile = manager.getEntitiesOnTile(x, y);
        manager.getField().getTile(x, y).setHasCarrot(false);
        for (Entity entity : entitiesOnTile) {
            if (entity instanceof Carrot) {
                manager.removeEntity(entity);
            }
        }
        FileHandler.getInstance().incrementStatisticsParameter("totalCarrotsEaten");
    }
}
