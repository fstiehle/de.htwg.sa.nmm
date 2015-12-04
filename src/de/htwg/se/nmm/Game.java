package de.htwg.se.nmm;

import java.util.Scanner;
import de.htwg.se.nmm.controller.GameController;
import de.htwg.se.nmm.entities.Board;
import de.htwg.se.nmm.tui.TextUI;

public class Game {

    static Scanner scanner;

    private Game() {
        // nothing to do
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI(new GameController(new Board()));
        textUI.printTUI();

        boolean game = true; // quit on -q
        scanner = new Scanner(System.in);
        while (game) {
            game = textUI.processInputLine(scanner.next());
        }
    }
}
