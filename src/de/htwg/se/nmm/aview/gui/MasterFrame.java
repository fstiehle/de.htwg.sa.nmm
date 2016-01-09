package de.htwg.se.nmm.aview.gui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.util.observer.IObserver;

import javax.swing.*;

public class MasterFrame extends JFrame implements IObserver{

    IGameController controller;

    @Override
    public void update() {

    }

    @Inject
    public MasterFrame(final IGameController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }
}
