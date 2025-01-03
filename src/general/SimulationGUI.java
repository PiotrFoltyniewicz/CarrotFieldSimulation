package general;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import simulation.entities.Carrot;
import simulation.entities.Entity;
import simulation.entities.Rabbit;
import simulation.environment.Field;

public class SimulationGUI {
    private JLabel[][] grid;
    private JPanel panel;
    private GameManager gameManager;

    private ImageIcon carrotIcon;
    private ImageIcon rabbitIcon;

    public SimulationGUI(GameManager gameManager) {
        this.gameManager = gameManager;
        Field field = gameManager.getField();
        int fieldSize = field.getFieldSize();

        JFrame frame = new JFrame("Carrot Field Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        panel = new JPanel(new GridLayout(fieldSize, fieldSize));
        grid = new JLabel[fieldSize][fieldSize];

        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                JLabel tile = new JLabel("Tile", SwingConstants.CENTER);
                tile.setOpaque(true);
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(tile);
                grid[row][col] = tile;
            }
        }
        this.loadImages();

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
        carrotIcon = resizeIcon(carrotIcon, 30, 30);
        rabbitIcon = resizeIcon(rabbitIcon, 30, 30);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void renderTurn() {
        Field field = gameManager.getField();
        int fieldSize = field.getFieldSize();

        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                List<Entity> entitiesOnTile = gameManager.getEntitiesOnTile(row, col);
                for (Entity entity : entitiesOnTile) {
                    System.out.println(entity.getClass().getSimpleName());
                    if (entity instanceof Carrot) {
                        grid[row][col].setIcon(carrotIcon);
                    }
                    if (entity instanceof Rabbit) {
                        grid[row][col].setIcon(rabbitIcon);
                    }
                }

                if (field.getTile(row, col).getIsDestroyed()) {
                    grid[row][col].setBackground(Color.gray);
                } else {
                    grid[row][col].setBackground(Color.GREEN);
                }
            }
        }
        refresh();
    }

    private void refresh() {
        panel.revalidate();
        panel.repaint();
    }
}
