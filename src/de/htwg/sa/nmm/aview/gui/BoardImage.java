package de.htwg.sa.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;

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
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 900, 900, this);
    }
}
