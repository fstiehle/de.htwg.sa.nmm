package de.htwg.se.nmm.tui;

import de.htwg.se.nmm.entities.Board;
import de.htwg.se.nmm.entities.Junction;
import de.htwg.se.util.observer.IObserver;
import de.htwg.se.nmm.controller.GameController;

import java.util.Map;

public class TextUI implements IObserver {

    private GameController controller;
    String strBoard;
    Board board;

    public TextUI(GameController controller) {

        this.controller = controller;
        controller.addObserver(this);
        this.board = controller.getBoard();

        StringBuilder strbuilderBoard = new StringBuilder();
        strbuilderBoard.append("" +
                "         a     b     c   d   e     f     g\n" +
                "\n" +
                "    1    a1--------------d1--------------g1\n" +
                "         |               |               |\n" +
                "    2    |     b2--------d2--------f2    |\n" +
                "         |     |         |         |     |\n" +
                "    3    |     |     c3--d3--e3    |     |\n" +
                "         |     |     |       |     |     |\n" +
                "    4    a4----b4----c4      e4----f4----g4\n" +
                "         |     |     |       |     |     |\n" +
                "    5    |     |     c5--d5--e5    |     |\n" +
                "         |     |         |         |     |\n" +
                "    6    |     b6--------d6--------f6    |\n" +
                "         |               |               |\n" +
                "    7    a7--------------d7--------------g7\n");


        strbuilderBoard.append("Please enter a command:\n" +
                "q - quit,\n" +
                "u - update,\n" +
                "r - reset,\n" +
                "ayz - set cell(a,y) to z\n\n");

        this.strBoard = strbuilderBoard.toString();

    }

    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {

        refreshBoard();
        System.out.println(strBoard);
    }

    private void refreshBoard() {
        for (Map.Entry<String, Junction> entry : board.getBoardMap().entrySet()) {

            if (this.strBoard.contains(entry.getKey())) {
                this.strBoard = this.strBoard.toString().replace(entry.getKey(), entry.getValue().toString());
            }

            //System.out.println(entry.getKey() + "/" + entry.getValue());
        }

    }

    public boolean processInputLine(String s) {
        boolean game = true;
        switch (s) {
            case "q":
                game = false;
                break;
            case "u":
                controller.update();
                break;
            case "r":

            case "t":
                System.out.println(" ");
                break;
        }
        return game;
    }

}
