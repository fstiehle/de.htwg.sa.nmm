package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayerState;

public class PlayerPICK implements IPlayerState {

    GameController controller;
    Player player;

    public PlayerPICK(Player player, GameController controller) {
        this.controller = controller;
        this.player = player;
    }

    @Override
    public void setPuck(String s, Puck puck) {
        controller.addStatusMessage("No more Pucks to set.");
    }

    @Override
    public void pickPuck(String s) {
        Junction j = controller.getBoard().get(s);

        if(j == null) {
            controller.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if (!j.hasPuck()) {
            controller.addStatusMessage("There is no puck to take away.");
            return;
        }
        if (j.getPuck().getPlayer().equals(controller.getCurrentPlayer())) {
            controller.addStatusMessage("This is your own puck!");
            return;
        }
        if(controller.checkformill(j, controller.getOtherPlayer())) {
            controller.addStatusMessage("Can't take away a puck if it is part of a mill.");
            return;
        }
        j.setPuck(null);
        controller.getOtherPlayer().incrementPucksTakenAway();
        controller.changePlayer();
    }

    @Override
    public void movePuck(String from, String to) {
        controller.addStatusMessage("Don't you want to pick an enemy puck? (They won't enjoy that.)");
    }

    @Override
    public String toString() {
        return "PICK";
    }
}
