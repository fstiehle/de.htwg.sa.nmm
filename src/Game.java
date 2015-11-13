/**
 * Created by mafunke on 23.10.2015.
 */

import java.util.Scanner;
import controller.GameController;
import entities.Board;
import tui.TextUI;

public class Game {

    static Scanner scanner;

    public static void main(String[] args) {
        TextUI textUI = new TextUI(new GameController(new Board()));

    }
}
