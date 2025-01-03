package general;

import java.util.*;
import simulation.entities.Entity;
import simulation.environment.Field;

public class GameManager {

    private Field field;
    private SimulationGUI renderer;
    private List<Entity> entities;

    public void startSimulation() {
        System.out.println("starting");
        Field field = new Field(4);
        renderer = new SimulationGUI(field.getFieldSize());
        renderer.renderTurn(entities, field);
    }

    public void manageTurn() {

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
