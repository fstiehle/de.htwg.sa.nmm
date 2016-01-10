package de.htwg.se.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class JunctionPanel extends AbstractButton {

    BufferedImage image;
    int x;
    int y;

    public JunctionPanel(BufferedImage image, int y, int x, MouseAdapter m) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.setBounds(x-15, y-15, 50, 50);

        this.addMouseListener(m);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBorder(BorderFactory.createTitledBorder(this.getName()));
    }

}
