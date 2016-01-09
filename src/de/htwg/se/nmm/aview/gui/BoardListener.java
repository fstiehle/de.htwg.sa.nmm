package de.htwg.se.nmm.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BoardListener implements ComponentListener {


    @Override
    public void componentResized(ComponentEvent arg0) {
        Rectangle b = arg0.getComponent().getBounds();
        arg0.getComponent().setBounds(b.x, b.y, b.width, b.width);

    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
