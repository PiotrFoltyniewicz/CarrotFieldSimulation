package general;

import java.util.*;
import simulation.entities.Entity;
import simulation.entities.Rabbit;
import simulation.entities.Carrot;
import simulation.environment.Field;

public class GameManager {

    private Field field;
    private SimulationGUI renderer;
    private List<Entity> entities;

    public GameManager() {
        field = new Field(4);
        field.getTile(3, 2).setIsDestroyed(true);
        field.getTile(2, 2).setHasCarrot(true);
        field.getTile(2, 1).setHasRabbit(false);
        Rabbit rabbit = new Rabbit(0, 0, 10);
        Carrot carrot = new Carrot(1, 0, 10);

        entities = new ArrayList<>();
        entities.add(rabbit);
        entities.add(carrot);
    }

    public void startSimulation() {
        System.out.println("starting ");
        renderer = new SimulationGUI(this);
        this.manageTurn();
    }

    public void manageTurn() {
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
