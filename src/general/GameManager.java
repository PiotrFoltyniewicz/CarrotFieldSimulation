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
}
