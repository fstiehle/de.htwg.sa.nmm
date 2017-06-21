package de.htwg.sa.nmm.controller.impl;

import de.htwg.sa.nmm.controller.IMillController;
import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;

import java.lang.reflect.Method;

/**
 * Delegate of GameController
 */
public class MillController implements IMillController {

    public MillController() {
        // nothing To Do
    }

    // public to be testable TODO
    public boolean checkForMill(IBoard board, String junctionName, IPlayer p) {
        IJunction j = board.getBoardMap().get(junctionName);
        int mill = -1;
        mill += checkformillR(j, 0, "Down", p);
        mill += checkformillR(j, 0, "Up", p);
        if (mill >= 3) {
            return true;
        }

        mill = -1;
        mill += checkformillR(j, 0, "Left", p);
        mill += checkformillR(j, 0, "Right", p);
        if (mill >= 3) {
            return true;
        }
        return false;
    }

    private int checkformillR(IJunction j, int sum, String direction, IPlayer p) {
        int t = 1;
        Method method;

        String m = "get" + direction;
        try {
            method = j.getClass().getMethod(m);

            if (method.invoke(j) != null) {
                if (((IJunction) method.invoke(j)).hasPuck() &&
                        ((IJunction) method.invoke(j)).getPuck().getPlayer().equals(p)) {
                    t += sum;
                    t += checkformillR((IJunction) method.invoke(j), sum + 1, direction, p);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}