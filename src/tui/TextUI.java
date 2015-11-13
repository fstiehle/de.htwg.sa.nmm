package tui;

import util.observer.IObserver;
import controller.GameController;

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
        // TODO: System.out.println(controller.getBoardString());

        String s = "Please enter a command:\n" +
                "q - quit,\n" +
                "u - update,\n" +
                "r - reset,\n" +
                "ayz - set cell(a,y) to z";

        System.out.println(s);
    }

}
