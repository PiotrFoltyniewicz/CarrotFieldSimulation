package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.Random;
import simulation.entities.Carrot;
import simulation.environment.Tile;

public class PlantCarrotAction extends EntityAction {

    public PlantCarrotAction(Tile currentTile) {
        super(currentTile);
    }

    @Override
    public void execute(GameManager manager) {
        FileHandler fileHandler = FileHandler.getInstance();
        int minTime = Integer.parseInt(fileHandler.readSettingsParameter("minCarrotGrowTime"));
        int maxTime = Integer.parseInt(fileHandler.readSettingsParameter("maxCarrotGrowTime"));
        fileHandler.incrementStatisticsParameter("totalCarrotsPlanted");

        Random random = new Random();
        int carrotGrowTime = random.nextInt(maxTime - minTime + 1) + minTime;

        currentTile.setHasCarrot(true);
        manager.addEntity(new Carrot(currentTile, carrotGrowTime));
    }
}
