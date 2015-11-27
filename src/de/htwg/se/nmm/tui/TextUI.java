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

        s.append("Please enter a command:\n" +
                "q - quit,\n" +
                "u - update,\n" +
                "r - reset,\n" +
                "ayz - set cell(a,y) to z");
        s.append(this.getBoard());

        System.out.println(s.toString());
    }

    public String getBoard() {
        StringBuilder strBoard = new StringBuilder();
        strBoard.append("         a     b     c   d   e     f     g\n" +
                "\n" +
                "    1    x---------------x---------------x\n" +
                "         |               |               |\n" +
                "    2    |     x---------x---------x     |\n" +
                "         |     |         |         |     |\n" +
                "    3    |     |     x---x---x     |     |\n" +
                "         |     |     |       |     |     |\n" +
                "    4    x-----x-----x       x-----x-----x\n" +
                "         |     |     |       |     |     |\n" +
                "    5    |     |     x---x---x     |     |\n" +
                "         |     |         |         |     |\n" +
                "    6    |     x---------x---------x     |\n" +
                "         |               |               |\n" +
                "    7    x---------------x---------------x\n");

        return strBoard.toString();
    }

}
