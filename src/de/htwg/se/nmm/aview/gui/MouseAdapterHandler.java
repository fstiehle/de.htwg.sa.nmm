package de.htwg.se.nmm.aview.gui;

import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPuck;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

public class MouseAdapterHandler extends MouseAdapter {

    IGameController controller;
    Queue mouseClicked = new LinkedList();

    MouseAdapterHandler(IGameController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        IJunction panel;
        try {
            panel = (IJunction)e.getSource();
        } catch (NullPointerException ex) {
            return;
        }

        String name = panel.getName();

        if (controller.getCurrentIPlayer().getStatus().equals(controller.getCurrentIPlayer().getSET())) {
            IPuck p = controller.getInjector().getInstance(IPuck.class);
            p.setPlayer(controller.getCurrentIPlayer());
            controller.getCurrentIPlayer().setPuck(name, p);
            controller.update();
        } else if (controller.getCurrentIPlayer().getStatus().equals(controller.getCurrentIPlayer().getMOVE())) {

            mouseClicked.add(panel);
            if (mouseClicked.size() == 2) {
                IJunction p0 = (IJunction)mouseClicked.poll();
                IJunction p1 = (IJunction)mouseClicked.poll();
                controller.getCurrentIPlayer().movePuck(p0.getName(), p1.getName());
                mouseClicked.clear();
                controller.update();
            }
        } else if (controller.getCurrentIPlayer().getStatus().equals(controller.getCurrentIPlayer().getPICK())) {
            controller.getCurrentIPlayer().pickPuck(panel.getName());
            controller.update();
        }
    }
}
