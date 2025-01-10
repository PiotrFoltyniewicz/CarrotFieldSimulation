package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.List;
import simulation.entities.*;
import simulation.environment.Tile;

public class EatCarrotAction extends EntityAction {

    public EatCarrotAction(Tile currentTile) {
        super(currentTile);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentTile.getPosition().x;
        int y = currentTile.getPosition().y;
        List<Entity> entitiesOnTile = manager.getEntitiesOnTile(x, y);
        currentTile.setHasCarrot(false);
        currentTile.setIsDestroyed(true);
        for (Entity entity : entitiesOnTile) {
            if (entity instanceof Carrot || entity instanceof Rabbit) {
                manager.removeEntity(entity);
            }
        }
        FileHandler.getInstance().incrementStatisticsParameter("totalCarrotsEaten");
    }
}
