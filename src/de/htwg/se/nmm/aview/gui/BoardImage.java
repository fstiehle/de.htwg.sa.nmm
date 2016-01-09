package de.htwg.se.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;

public class BoardImage extends JComponent {
    private Image image;
    Dimension size;

    public BoardImage(Image image) {
        this.image = image;
        this.size = new Dimension(image.getWidth(null), image.getHeight(null));

        setPreferredSize(size);
        setMaximumSize(new Dimension(1000,800));
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
