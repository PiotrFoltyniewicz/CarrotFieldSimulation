package general;

import java.io.*;
import java.util.*;

public class FileHandler {

    private File settingsFile;
    private File statisticsFile;
    private static FileHandler instance;

    public FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    public void openSettingsFile(String filename) {
        try {
            settingsFile = new File(filename);
        } catch (Exception e) {
            System.err.println("Failed to open the settings file");
            e.printStackTrace();
        }

    }

    public void openStatisticsFile(String filename) {
        try {
            statisticsFile = new File(filename);
        } catch (Exception e) {
            System.err.println("Failed to open the statistics file");
            e.printStackTrace();
        }
    }

    public String readSettingsParameter(String key) throws IOException {
        try {
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
        throw new IOException("Parameter " + key + " doesn't exist");
    }

    public <T> void writeStatisticsParameter(String key, T value) throws IOException {
        List<String> fileLines = new ArrayList<>();
        boolean keyFound = false;

        try {
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
}
