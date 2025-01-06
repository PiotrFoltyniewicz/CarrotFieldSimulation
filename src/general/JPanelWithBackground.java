package general;

import java.awt.*;
import javax.swing.*;

class JPanelWithBackground extends JPanel {

    private Image backgroundImage;
    private JPanel entityLayer;

    public JPanelWithBackground(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        entityLayer = new JPanel();
        entityLayer.setOpaque(false);
        entityLayer.setLayout(new OverlayLayout(entityLayer)); // Stack entities
        setLayout(new BorderLayout());
        add(entityLayer, BorderLayout.CENTER);
    }

    public void addEntityIcon(ImageIcon icon) {
        JLabel entityLabel = new JLabel(icon);
        entityLayer.add(entityLabel);
    }

    public void setBackgroundImage(Image image) {
        backgroundImage = image;
        repaint();
    }

    public void clearEntityIcons() {
        entityLayer.removeAll();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
