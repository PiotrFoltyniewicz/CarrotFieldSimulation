
import general.*;

public class Main {

    public static void main(String[] args) throws Exception {

        FileHandler fileHandler = new FileHandler();
        fileHandler.openSettingsFile("../resources/settings.txt");
        fileHandler.openStatisticsFile("../resources/statistics.txt");

    }
}
