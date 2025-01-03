package simulation.actions;

import general.FileHandler;
import general.GameManager;
import java.util.Random;
import simulation.entities.Carrot;

public class PlantCarrotAction extends EntityAction {

    public PlantCarrotAction(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void execute(GameManager manager) {
        int x = currentPosition.x;
        int y = currentPosition.y;

        FileHandler fileHandler = FileHandler.getInstance();
        int minTime = Integer.parseInt(fileHandler.readSettingsParameter("minCarrotGrowTime"));
        int maxTime = Integer.parseInt(fileHandler.readSettingsParameter("maxCarrotGrowTime"));
        fileHandler.incrementStatisticsParameter("totalCarrotsPlanted");

        Random random = new Random();
        int carrotGrowTime = random.nextInt(maxTime - minTime + 1) + minTime;

        manager.getField().getTile(x, y).setHasCarrot(true);
        manager.addEntity(new Carrot(x, y, carrotGrowTime));
    }
}
