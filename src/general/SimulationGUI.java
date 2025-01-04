package general;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import simulation.entities.Carrot;
import simulation.entities.Entity;
import simulation.entities.Farmer;
import simulation.entities.Rabbit;
import simulation.environment.Field;

public class SimulationGUI {
    private JPanelWithBackground[][] grid;
    private JPanel panel;
    private GameManager gameManager;

    private ImageIcon carrotIcon;
    private ImageIcon rabbitIcon;
    private ImageIcon farmerIcon;

    public SimulationGUI(GameManager gameManager) {
        this.gameManager = gameManager;
        Field field = gameManager.getField();
        int fieldSize = field.getFieldSize();

        JFrame frame = new JFrame("Carrot Field Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        panel = new JPanel(new GridLayout(fieldSize, fieldSize));
        grid = new JPanelWithBackground[fieldSize][fieldSize];

        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                try {
                    JPanelWithBackground tile = new JPanelWithBackground("../resources/img/grass.png");
                    tile.setOpaque(true);
                    tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panel.add(tile);
                    grid[row][col] = tile;
                } catch (IOException e) {
                    System.err.println("Error loading grass image: " + e.getMessage());
                    JPanel tile = new JPanel();
                    tile.setBackground(Color.GREEN);
                    panel.add(tile);
                    grid[row][col] = null;
                }
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
        farmerIcon = new ImageIcon("../resources/img/farmer.png");
        if (farmerIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("farmer image not loaded!");
        }
        carrotIcon = resizeIcon(carrotIcon, 30, 30);
        rabbitIcon = resizeIcon(rabbitIcon, 30, 30);
        farmerIcon = resizeIcon(farmerIcon, 30, 30);
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
                grid[row][col].clearEntityIcon();
                for (Entity entity : entitiesOnTile) {

                    System.out.println(entity.getClass().getSimpleName());
                    if (entity instanceof Farmer) {
                        grid[row][col].setEntityIcon(farmerIcon);
                    }
                    // if (entity instanceof Carrot) {
                    // grid[row][col].setEntityIcon(carrotIcon);
                    // }
                    // if (entity instanceof Rabbit) {
                    // grid[row][col].setEntityIcon(rabbitIcon);
                    // }
                }

                if (field.getTile(row, col).getIsDestroyed()) {
                    try {
                        grid[row][col].setBackgroundImage("../resources/img/dirt.png");
                    } catch (IOException e) {
                        System.err.println("Error loading dirt image: " + e.getMessage());
                        grid[row][col].setBackground(Color.gray);
                    }
                } else {
                    try {
                        grid[row][col].setBackgroundImage("../resources/img/grass.png");
                    } catch (IOException e) {
                        System.err.println("Error loading grass image: " + e.getMessage());
                        grid[row][col].setBackground(Color.GREEN);
                    }
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

class JPanelWithBackground extends JPanel {

    private Image backgroundImage;
    private JLabel entityLabel;

    public JPanelWithBackground(String fileName) throws IOException {
        setBackgroundImage(fileName);
        entityLabel = new JLabel();
        setLayout(new BorderLayout());
        add(entityLabel, BorderLayout.CENTER);
    }

    public void setBackgroundImage(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
        repaint();
    }

    public void setEntityIcon(ImageIcon icon) {
        entityLabel.setIcon(icon);
    }

    public void clearEntityIcon() {
        entityLabel.setIcon(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}