package de.htwg.se.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JunctionPanel extends JComponent{

    BufferedImage image;
    int x;
    int y;

    public JunctionPanel(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, x,  y, 20, 20, this);
    }
}
