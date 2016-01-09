package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayerState;

public class PlayerSET implements IPlayerState {

    GameController controller;
    Player player;

    public PlayerSET(Player player, GameController controller) {
        this.controller = controller;
        this.player = player;
    }

    @Override
    public void setPuck(String s, Puck puck) {
        Junction j = controller.getBoard().getBoardMap().get(s);

        if(j == null) {
            controller.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if (j.hasPuck()) {
            controller.addStatusMessage("There already is a Puck.");
            return;
        }
        j.setPuck(puck);
        player.decrementPucks();
        controller.millAfterMove(j);
    }

    @Override
    public void pickPuck(String s) {
        controller.addStatusMessage("You don't have a valid mill, you can't pick yet.");
    }

    @Override
    public void movePuck(String from, String to) {
        controller.addStatusMessage("You must set all your pucks before you may move.");
    }

    @Override
    public String toString() {
        return "SET";
    }
}
