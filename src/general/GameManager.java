package general;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import simulation.entities.*;
import simulation.environment.*;

public class GameManager {

    private Field field;
    private SimulationGUI renderer;
    private CopyOnWriteArrayList<Entity> entities;
    private int maxTurns;
    private int betweenTurnTime;

    private float rabbitSpawnChance;
    private int minRabbitEatTime;
    private int maxRabbitEatTime;

    public GameManager() {
        maxTurns = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("maxTurnLimit"));
        field = new Field(Integer.parseInt(FileHandler.getInstance().readSettingsParameter("fieldSize")));
        betweenTurnTime = Integer
                .parseInt(FileHandler.getInstance().readSettingsParameter("millisecondsBetweenFrames"));

        rabbitSpawnChance = Float.parseFloat(FileHandler.getInstance().readSettingsParameter("rabbitSpawnChance"));
        minRabbitEatTime = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("minRabbitEatTime"));
        maxRabbitEatTime = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("maxRabbitEatTime"));

        entities = new CopyOnWriteArrayList<>();
        renderer = new SimulationGUI(this);
    }

    private void spawnEntities() {
        int farmerCount = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("initialFarmerCount"));
        int entitySightRange = Integer.parseInt(FileHandler.getInstance().readSettingsParameter("entitySightRange"));
        Random random = new Random();

        for (int i = 0; i < farmerCount; i++) {
            int x = random.nextInt(field.getFieldSize());
            int y = random.nextInt(field.getFieldSize());
            Farmer newFarmer = new Farmer(field.getTile(x, y), entitySightRange);
            addEntity(newFarmer);
            addEntity(newFarmer.getDog());
        }
    }

    private void spawnRabbits() {
        Random random = new Random();
        int fieldSize = field.getFieldSize();
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                Tile tile = field.getTile(x, y);
                if (tile.getHasCarrot() && !tile.getHasRabbit()) {
                    if (random.nextFloat() <= rabbitSpawnChance) {
                        int eatTime = random.nextInt(maxRabbitEatTime - minRabbitEatTime + 1) + minRabbitEatTime;
                        tile.setHasRabbit(true);
                        addEntity(new Rabbit(tile, eatTime));
                    }
                }
            }
        }
    }

    public void startSimulation() {
        int turn = 0;
        FileHandler.getInstance().clearStatistics();
        System.out.println("starting");
        spawnEntities();

        while (turn < maxTurns) {
            this.manageTurn();
            turn++;
        }
        System.out.println("simulation finished");
    }

    public void manageTurn() {
        spawnRabbits();
        List<Thread> threads = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity instanceof DynamicEntity) {
                DynamicEntity dEntity = (DynamicEntity) entity;
                dEntity.setSurroundingTiles(
                        getField().getSurroundingTiles(entity.getPosition(), dEntity.getSightRange()));
            }
            Thread thread = new Thread(() -> entity.getAction().execute(this));
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        renderer.renderTurn();

        // we need to slowdown the code, because it would be impossible to watch
        try {
            Thread.sleep(betweenTurnTime);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted!");
        }
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

    public List<Entity> getEntities() {
        return entities;
    }
}
