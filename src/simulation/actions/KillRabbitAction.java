package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.*;
import simulation.entities.*;
import simulation.environment.Tile;

public class KillRabbitAction extends EntityAction {

    public KillRabbitAction(Tile currentTile) {
        super(currentTile);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentTile.getPosition().x;
        int y = currentTile.getPosition().y;
        List<Entity> entitiesOnTile = manager.getEntitiesOnTile(x, y);
        currentTile.setHasRabbit(false);
        for (Entity entity : entitiesOnTile) {
            if (entity instanceof Rabbit) {
                manager.removeEntity(entity);
            }
        }
        FileHandler.getInstance().incrementStatisticsParameter("totalRabbitsKilled");
    }

}
