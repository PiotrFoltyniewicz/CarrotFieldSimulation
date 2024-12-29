package general;
import general.SimulationGUI;
import simulation.environment.Field;
import simulation.entities.Entity;
import java.util.*;

public class GameManager {
    private Field field;
    private SimulationGUI renderer;
    private List<Entity> entities;

    public void startSimulation(){
		System.out.println("starting");
	}

    public void manageTurn() {
        
    }
    
    public Field getField() {
        return field;
    }
}
