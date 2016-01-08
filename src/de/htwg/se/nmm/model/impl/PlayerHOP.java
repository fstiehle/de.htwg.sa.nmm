package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayerState;

public class PlayerHOP implements IPlayerState {

    GameController controller;
    Player player;

    public PlayerHOP(Player player, GameController controller) {
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
        jTo.setPuck(jFrom.getPuck());
        jFrom.setPuck(null);
        controller.millAfterMove(jTo);
    }

    @Override
    public String toString() {
        return "HOP";
    }
}
