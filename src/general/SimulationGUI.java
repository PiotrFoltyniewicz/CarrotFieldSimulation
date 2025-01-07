package general;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import simulation.entities.*;
import simulation.environment.Field;

public class SimulationGUI {

    private JPanelWithBackground[][] grid;
    private JPanel panel;
    private GameManager gameManager;

    private ImageIcon carrotIcon;
    private ImageIcon rabbitIcon;
    private ImageIcon farmerIcon;
    private ImageIcon dogIcon;
    private Image dirtImage;
    private Image grassImage;

    public SimulationGUI(GameManager gameManager) {
        this.gameManager = gameManager;
        Field field = gameManager.getField();
        int fieldSize = field.getFieldSize();

        JFrame frame = new JFrame("Carrot Field Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        panel = new JPanel(new GridLayout(fieldSize, fieldSize));
        grid = new JPanelWithBackground[fieldSize][fieldSize];

        this.loadImages();

        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                JPanelWithBackground tile = new JPanelWithBackground(grassImage);
                tile.setOpaque(true);
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(tile);
                grid[row][col] = tile;
            }
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadImages() {
        carrotIcon = new ImageIcon("../resources/img/carrot.png");
        if (carrotIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Carrot image not loaded!");
        }
        rabbitIcon = new ImageIcon("../resources/img/rabbit.png");
        if (carrotIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Rabbbit image not loaded!");
        }
        farmerIcon = new ImageIcon("../resources/img/farmer.png");
        if (farmerIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("farmer image not loaded!");
        }
        dogIcon = new ImageIcon("../resources/img/dog.png");
        if (dogIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("dog image not loaded!");
        }
        try {
            grassImage = ImageIO.read(new File("../resources/img/grass.png"));
        } catch (Exception e) {
            System.err.println("grass image not loaded!");
        }
        try {
            dirtImage = ImageIO.read(new File("../resources/img/dirt.png"));
        } catch (Exception e) {
            System.err.println("dirt image not loaded!");
        }

        carrotIcon = resizeIcon(carrotIcon, 30, 30);
        rabbitIcon = resizeIcon(rabbitIcon, 30, 30);
        farmerIcon = resizeIcon(farmerIcon, 30, 30);
        dogIcon = resizeIcon(dogIcon, 30, 30);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void renderTurn() {
        Field field = gameManager.getField();
        int fieldSize = field.getFieldSize();

        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                grid[y][x].clearEntityIcons();
                if (field.getTile(x, y).getIsDestroyed()) {
                    grid[y][x].setBackgroundImage(dirtImage);
                } else {
                    grid[y][x].setBackgroundImage(grassImage);
                }
            }
        }

        for (Entity entity : gameManager.getEntities()) {
            int x = entity.getPosition().x;
            int y = entity.getPosition().y;

            if (entity instanceof Carrot) {
                grid[y][x].addEntityIcon(carrotIcon);
            }
            if (entity instanceof Rabbit) {
                grid[y][x].addEntityIcon(rabbitIcon);
            }
            if (entity instanceof Farmer) {
                grid[y][x].addEntityIcon(farmerIcon);
            }
            if (entity instanceof Dog) {
                grid[y][x].addEntityIcon(dogIcon);
            }
        }

        refresh();
    }

    private void refresh() {
        panel.revalidate();
        panel.repaint();
    }
}
