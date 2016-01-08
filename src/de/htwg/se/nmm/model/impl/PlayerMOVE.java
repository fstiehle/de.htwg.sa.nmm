package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayerState;

public class PlayerMOVE implements IPlayerState {

    GameController controller;
    Player player;

    public PlayerMOVE(Player player, GameController controller) {
        this.controller = controller;
        this.player = player;
    }

    @Override
    public void setPuck(String s, Puck puck) {
        controller.addStatusMessage("No more Pucks to set.");
    }

    @Override
    public void pickPuck(String s) {
        controller.addStatusMessage("You don't have a valid mill, you can't pick yet.");
    }

    @Override
    public void movePuck(String from, String to) {
        Junction jFrom = controller.getBoard().get(from);
        Junction jTo = controller.getBoard().get(to);

        if(jFrom == null || jTo == null) {
            controller.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if(jTo.hasPuck()) {
            controller.addStatusMessage("There already is a Puck.");
            return;
        }
        if(!jFrom.getPuck().getPlayer().equals(controller.getCurrentPlayer())) {
            controller.addStatusMessage("That's not your puck unfortunately.");
            return;
        }
        if (!checkMovement(jFrom, jTo)) {
            controller.addStatusMessage("Move is not allowed.");
            return;
        }
        jTo.setPuck(jFrom.getPuck());
        jFrom.setPuck(null);
        controller.millAfterMove(jTo);
    }

    private boolean checkMovement(Junction jFrom, Junction jTo) {
        if(jFrom.getDown() != null && jFrom.getDown().equals(jTo)) {
            return true;
        } else if(jFrom.getUp() != null && jFrom.getUp().equals(jTo)) {
            return true;
        } else if(jFrom.getRight() != null && jFrom.getRight().equals(jTo)) {
            return true;
        } else if(jFrom.getLeft() != null && jFrom.getLeft().equals(jTo)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MOVE";
    }
}
