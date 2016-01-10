package de.htwg.se.nmm.aview.gui;

import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Puck;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterHandler extends MouseAdapter {

    IGameController controller;

    MouseAdapterHandler(IGameController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JunctionPanel panel = (JunctionPanel)e.getSource();
        String name = panel.getName();

        if (controller.getCurrentIPlayer().getStatus().equals(controller.getCurrentIPlayer().getSET())) {
            Puck p = new Puck();
            p.setPlayer(controller.getCurrentIPlayer());
            controller.getCurrentIPlayer().setPuck(name, p);
            controller.update();
        }
    }
}
