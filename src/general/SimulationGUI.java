package general;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import simulation.entities.Entity;
import simulation.environment.Field;

public class SimulationGUI {
    private JLabel[][] grid;
    private JPanel panel;

    public SimulationGUI(int fieldSize) {
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

        frame.add(panel);
        frame.setVisible(true);
    }

    public void renderTurn(List<Entity> entities, Field field) {
        int fieldSize = field.getFieldSize();
        for (int row = 0; row < fieldSize; row++) {
            for (int col = 0; col < fieldSize; col++) {
                if (field.getTile(row, col).getIsDestroyed()) {
                    grid[row][col].setBackground(Color.GREEN);
                } else {
                    grid[row][col].setBackground(Color.gray);
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
