package de.htwg.se.nmm.model;

import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.model.impl.Puck;

import java.awt.event.MouseAdapter;

public interface IJunction {

    void setNeighbours(Junction up, Junction right, Junction down, Junction left);

    void setUp(Junction up);

    boolean isPressed();

    void setPressed(boolean pressed);

    void setRight(Junction right);

    void setDown(Junction down);

    void setLeft(Junction left);

    void setPuck(Puck puck);

    Junction getUp();

    Junction getRight();

    Junction getDown();

    Junction getLeft();

    Puck getPuck();

    boolean hasPuck();

    String getName();

    void placeOnGui(String name, int y, int x, MouseAdapter m);
}
