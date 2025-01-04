
import general.*;

public class Main {

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler("../resources/settings.txt", "../resources/statistics.txt");
        GameManager gameManager = new GameManager();
        gameManager.startSimulation();

    }
}
