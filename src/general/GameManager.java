package general;

import java.util.*;
import simulation.entities.*;
import simulation.environment.Field;

public class GameManager {

    private Field field;
    private SimulationGUI renderer;
    private List<Entity> entities;
    private int maxTurns;

    public GameManager() {
        maxTurns = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("maxTurnLimit"));
        field = new Field(Integer.parseInt(FileHandler.getInstance().readSettingsParameter("fieldSize")));
        entities = new ArrayList<>();
        renderer = new SimulationGUI(this);
        /*
        field.getTile(3, 2).setIsDestroyed(true);
        field.getTile(2, 2).setHasCarrot(true);
        field.getTile(2, 1).setHasRabbit(false);
        Rabbit rabbit = new Rabbit(0, 0, 10);
        Carrot carrot = new Carrot(1, 0, 10);

        entities.add(rabbit);
        entities.add(carrot);
         */
    }

    private void spawnEntities() {
        int farmerCount = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("initialFarmerCount"));
        int entitySightRange = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("entitySightRange"));
        Random random = new Random();

        for (int i = 0; i < farmerCount; i++) {
            int x = random.nextInt(field.getFieldSize());
            int y = random.nextInt(field.getFieldSize());
            Farmer newFarmer = new Farmer(x, y, entitySightRange);
            addEntity(newFarmer);
            addEntity(newFarmer.getDog());
        }
    }

    public void startSimulation() {
        int turn = 0;
        System.out.println("starting");
        spawnEntities();

        while (turn < maxTurns) {
            this.manageTurn();
            turn++;
        }
        System.out.println("simulation finished");
    }

    public void manageTurn() {

        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            iterator.next().getAction().execute(this);
        }
        renderer.renderTurn();

    }

    public Field getField() {
        return field;
    }

    public List<Entity> getEntitiesOnTile(int x, int y) {
        List<Entity> output = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getPosition().x == x && entity.getPosition().y == y) {
                output.add(entity);
            }
        }
        return output;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
