package general;

import java.io.*;
import java.util.*;

public class FileHandler {

    private String settingsFilename;
    private String statisticsFilename;
    private static FileHandler instance;

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler("../resources/settings.txt", "../resources/statistics.txt");
        }
        return instance;
    }

    public FileHandler(String settingsFilename, String statisticsFilename) {
        this.settingsFilename = settingsFilename;
        this.statisticsFilename = statisticsFilename;
    }

    public File openSettingsFile() {
        try {
            return new File(settingsFilename);

        } catch (Exception e) {
            System.err.println("Failed to open the settings file");
            e.printStackTrace();
        }
        return null;
    }

    public File openStatisticsFile() {
        try {
            return new File(statisticsFilename);

        } catch (Exception e) {
            System.err.println("Failed to open the statistics file");
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String readSettingsParameter(String key) {

        try {
            File settingsFile = openSettingsFile();
            BufferedReader reader = new BufferedReader(new FileReader(settingsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(key + ":")) {
                    return line.substring(key.length() + 1).trim();
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Failed to extract parameter from settings file");
            e.printStackTrace();
        }
        return null;
    }

    public synchronized <T> void writeStatisticsParameter(String key, T value) {
        List<String> fileLines = new ArrayList<>();
        boolean keyFound = false;

        try {
            File statisticsFile = openStatisticsFile();
            BufferedReader reader = new BufferedReader(new FileReader(statisticsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(key + ":")) {
                    fileLines.add(key + ": " + value);
                    keyFound = true;
                } else {
                    fileLines.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Failed to read data from statistics file");
            e.printStackTrace();
        }

        if (!keyFound) {
            fileLines.add(key + ": " + value);
        }

        try {
            File statisticsFile = openStatisticsFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(statisticsFile));
            for (String line : fileLines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Failed to write data to the statistics file");
            e.printStackTrace();
        }
    }

    public synchronized void incrementStatisticsParameter(String key) {
        try {
            File statisticsFile = openStatisticsFile();
            BufferedReader reader = new BufferedReader(new FileReader(statisticsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(key + ":")) {
                    String[] parts = line.split(":");
                    String valueStr = parts[1].trim();
                    int value = Integer.parseInt(valueStr);
                    writeStatisticsParameter(key, value + 1);
                    return;
                }
            }
            reader.close();
            writeStatisticsParameter(key, 1);
        } catch (IOException e) {
            System.err.println("Failed to read statistics file");
            e.printStackTrace();
        }
    }

    public synchronized void clearStatistics() {
        try {
            File statisticsFile = openStatisticsFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(statisticsFile));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.err.println("Failed to clear statistics file");
            e.printStackTrace();
        }
    }
}
