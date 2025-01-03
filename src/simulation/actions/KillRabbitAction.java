package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.*;
import simulation.entities.*;

public class KillRabbitAction extends EntityAction {

    public KillRabbitAction(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentPosition.x;
        int y = currentPosition.y;
        List<Entity> entitiesOnTile = manager.getEntitiesOnTile(x, y);
        manager.getField().getTile(x, y).setHasRabbit(false);
        for (Entity entity : entitiesOnTile) {
            if (entity instanceof Rabbit) {
                manager.removeEntity(entity);
            }
        }
        FileHandler.getInstance().incrementStatisticsParameter("totalRabbitsKilled");
    }

}
