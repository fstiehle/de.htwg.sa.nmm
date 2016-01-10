package de.htwg.se.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.Map;

public class JunctionPanel extends AbstractButton {

    int x;
    int y;

    public JunctionPanel(String name, Map map, int y, int x, MouseAdapter m) {

        map.put(name, this);
        this.setName(name);
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

    public void say() {
        System.out.println(getName());
    }
}
