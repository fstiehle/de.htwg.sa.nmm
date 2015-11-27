package de.htwg.se.nmm.tui;

import de.htwg.se.util.observer.IObserver;
import de.htwg.se.nmm.controller.GameController;

public class TextUI implements IObserver {

    private GameController controller;

    public TextUI(GameController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        StringBuilder s = new StringBuilder();

        s.append(controller.getBoardString());
        s.append("Please enter a command:\n" +
                "q - quit,\n" +
                "u - update,\n" +
                "r - reset,\n" +
                "ayz - set cell(a,y) to z\n\n");

        System.out.println(s.toString());
    }

    public boolean processInputLine(String s) {
        boolean game = true;
        switch (s) {
            case "q":
                game = false;
            case "u":
                controller.update();
            case "r":
                controller.reset();
            case "t":
                System.out.println(" ");
        }
        return game;
    }

}
