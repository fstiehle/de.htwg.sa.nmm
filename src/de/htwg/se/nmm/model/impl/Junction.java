package de.htwg.se.nmm.model.impl;


import de.htwg.se.nmm.model.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Map;

/**
 * a Junction knows its neighbours
 */
public class Junction extends AbstractButton implements de.htwg.se.nmm.model.IJunction {

    private Junction up;
    private Junction right;

    private Junction down;
    private Junction left;

    private Puck puck;

    int x;
    int y;

    @Override
    public void setNeighbours(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.puck = null;
    }

    public void placeOnGui(String name, int y, int x, MouseAdapter m) {

        this.setName(name);
        this.x = x;
        this.y = y;
        this.setBounds(x-15, y-15, 50, 50);

        this.addMouseListener(m);
    }

    @Override
    public void setUp(Junction up) {
        this.up = up;
    }

    @Override
    public void setRight(Junction right) {
        this.right = right;
    }

    @Override
    public void setDown(Junction down) {
        this.down = down;
    }

    @Override
    public void setLeft(Junction left) {
        this.left = left;
    }

    @Override
    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    @Override
    public Junction getUp() {
        return up;
    }

    @Override
    public Junction getRight() {
        return right;
    }

    @Override
    public Junction getDown() {
        return down;
    }

    @Override
    public Junction getLeft() {
        return left;
    }

    @Override
    public Puck getPuck() {
        return puck;
    }

    @Override
    public boolean hasPuck() {
        if(this.puck == null) {
            return false;
        }
        return true;
    }

    //---------------------------
    // IMPLEMENTATIONS

    @Override
    public String toString() {
        if (this.puck == null) {
            return "x-";
        } else if (this.puck.getPlayer().getMan() == Player.Man.WHITE) {
            return "\u25CB-";
        } else {
            return "\u25CF-";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        this.repaint();
        if (this.getPuck() != null) {

            super.paintComponent(g);
            int radius = 25;
            int diameter = radius * 2;
            if(this.getPuck().getPlayer().getMan() == IPlayer.Man.WHITE) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            //shift x and y by the radius of the circle in order to correctly center it
            g.fillOval(0, 0, diameter, diameter);
        }
    }
}
