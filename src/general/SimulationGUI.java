package general;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    private ImageIcon[] carrotStages;

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

        carrotStages = new ImageIcon[]{
            resizeIcon(carrotIcon, 6, 6),
            resizeIcon(carrotIcon, 12, 12),
            resizeIcon(carrotIcon, 18, 18),
            resizeIcon(carrotIcon, 24, 24),
            resizeIcon(carrotIcon, 30, 30)
        };

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

    private int getPriority(Entity entity) {
        if (entity instanceof Farmer) {
            return 4;
        }
        if (entity instanceof Dog) {
            return 3;
        }
        if (entity instanceof Rabbit) {
            return 2;
        }
        if (entity instanceof Carrot) {
            return 1;
        }
        return 0;
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

        List<Entity> entities = new ArrayList<>(gameManager.getEntities());
        entities.sort((e1, e2) -> Integer.compare(getPriority(e2), getPriority(e1)));

        for (Entity entity : entities) {
            int x = entity.getPosition().x;
            int y = entity.getPosition().y;

            if (entity instanceof Farmer) {
                grid[y][x].addEntityIcon(farmerIcon);
            } else if (entity instanceof Dog) {
                grid[y][x].addEntityIcon(dogIcon);
            } else if (entity instanceof Rabbit) {
                grid[y][x].addEntityIcon(rabbitIcon);
            } else if (entity instanceof Carrot) {
                Carrot cEntity = (Carrot) entity;
                grid[y][x].addEntityIcon(carrotStages[(int) (cEntity.getProgressPercent() * 4)]);
            }
        }

        refresh();
    }

    private void refresh() {
        panel.revalidate();
    }
}
