package de.htwg.se.nmm.aview.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;

public class BoardImage extends JComponent {
    private Image image;

    public BoardImage(Image image) {
        this.image = image;


        LayoutManager overlay = new OverlayLayout(this);
        this.setLayout(overlay);
        setMinimumSize(new Dimension(900, 900));
        setSize(new Dimension(900, 900));

    }

    @Override
    protected void paintComponent(Graphics g) {

        System.out.println(getHeight());
        System.out.println(getWidth());
        g.clearRect(0,0, getWidth(), getHeight());
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 900, 900, this);
    }
}
