package de.htwg.se.nmm.aview.tui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.util.observer.IObserver;
import org.apache.log4j.Logger;

import java.util.Map;

public class TextUI implements IObserver {

    private static final Logger logger = Logger.getLogger("de.htwg.se.nmm.aview.tui");

    private IGameController controller;
    String strBoard;
    String strMenu;
    Map<String, IJunction> board;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_HIGHLIGHT = "\u001B[32m";

    @Inject
    public TextUI(IGameController controller) {
        this.controller = controller;
        controller.addObserver(this);
        this.board = controller.getBoard().getBoardMap();

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

        this.strBoard = strbuilderBoard.toString();

        StringBuilder strbuilderMenu = new StringBuilder();
        strbuilderMenu.append("\nPlease enter a command:\n" +
                ANSI_HIGHLIGHT + "pick(xy)" + ANSI_RESET + ": pick puck from (xy)\n" +
                ANSI_HIGHLIGHT + "move(xy,xy)" + ANSI_RESET + ": move puck from (xy) to (xy)\n" +
                ANSI_HIGHLIGHT + "set(xy)" + ANSI_RESET + ": place puck on (xy),\n" +
                "q - quit\n\n");

        this.strMenu = strbuilderMenu.toString();
    }

    @Override
    public void update() {
        printTUI();
    }

    public void printTUI() {
        String tmpBoard = refreshBoard();

        if(controller.getCurrentIPlayer().hasLost()) {
            this.logger.info(ANSI_RED);
            this.logger.info(tmpBoard);
            this.logger.info(controller.getStatus());
            System.exit(0);
        }

        String tmpMenu = refreshMenu();

        this.logger.info(tmpBoard);
        this.logger.info(tmpMenu);
        this.logger.info(controller.getStatus());
    }

    private String refreshBoard() {
        String tmpBoard = this.strBoard;
        for (Map.Entry<String, IJunction> entry : board.entrySet()) {
            tmpBoard = tmpBoard.replace(entry.getKey(), entry.getValue().toString());
        }

        return tmpBoard;
    }

    private String refreshMenu() {
        String tmpMenu = this.strMenu;
        tmpMenu += "Puck's left:  "  + ANSI_HIGHLIGHT +  controller.getCurrentIPlayer().getNumPucks() + ANSI_RESET + "\n";
        tmpMenu += "You're: " + ANSI_HIGHLIGHT + controller.getCurrentIPlayer().getMan().toString() + ANSI_RESET + "\n";
        tmpMenu += "You're in modus: " + ANSI_HIGHLIGHT + controller.getCurrentIPlayer().getStatus().toString() + ANSI_RESET + "\n";

        return tmpMenu;
    }

    public boolean processInputLine(String s) {
        boolean game = true;

        if (s == null) {
            throw new RuntimeException("Invalid input");
        }
        if (s.equals("q")) {
            game = false;
        } else if (s.equals("u")) {
            controller.update();
        } else if (s.matches("set\\([a-z]\\d\\)")) {
            StringBuilder pos = new StringBuilder();
            pos.append(s.charAt(4));
            pos.append(s.charAt(5));

            IPuck p = controller.getInjector().getInstance(IPuck.class);
            p.setPlayer(controller.getCurrentIPlayer());
            controller.setPuck(pos.toString(), p);
            controller.update();
        } else if (s.matches("pick\\([a-z]\\d\\)")) {
            StringBuilder pos = new StringBuilder();
            pos.append(s.charAt(5));
            pos.append(s.charAt(6));

            controller.pickPuck(pos.toString());
            controller.update();
        } else if (s.matches("move\\([a-z]\\d,[a-z]\\d\\)")) {
            StringBuilder posFrom = new StringBuilder();
            posFrom.append(s.charAt(5));
            posFrom.append(s.charAt(6));

            StringBuilder posTo = new StringBuilder();
            posTo.append(s.charAt(8));
            posTo.append(s.charAt(9));

            controller.movePuck(posFrom.toString(), posTo.toString());
            controller.update();
        }
        return game;
    }

}
