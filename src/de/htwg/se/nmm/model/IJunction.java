package de.htwg.se.nmm.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public interface IJunction {

    void setNeighbours(IJunction up, IJunction right, IJunction down, IJunction left);

    void setUp(IJunction up);

    boolean isPressed();

    void setPressed(boolean pressed);

    void setRight(IJunction right);

    void setDown(IJunction down);

    void setLeft(IJunction left);

    void setPuck(IPuck puck);

    IJunction getUp();

    IJunction getRight();

    IJunction getDown();

    IJunction getLeft();

    IPuck getPuck();

    boolean hasPuck();

    String getName();

    void placeOnGui(String name, int y, int x, MouseAdapter m);
}
