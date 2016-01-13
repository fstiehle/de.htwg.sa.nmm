package de.htwg.se.nmm.aview.gui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.impl.Junction; // für die HashMap
import de.htwg.se.nmm.util.observer.IObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class MasterFrame extends JFrame implements IObserver {

    IGameController controller;
    BufferedImage boardImage;
    private final String resourcePath = "src/de/htwg/se/nmm/aview/gui/";
    private final String boardImageUrl = "board.png";

    /* Where everything comes together */
    JPanel MasterPanel;

    JTextArea consoleArea;
    JPanel consolePanel;
    String statusMessage;
    String manual = "\nUse Your Mouse to Interact with the Board.\n" +
            "This statusbar will give you required information about your current state.\n";

    @Inject
    public MasterFrame(final IGameController controller) {
        this.controller = controller;
        controller.addObserver(this);
        statusMessage = controller.getStatus();

        System.out.println(System.getProperty("user.dir"));

        /* Settings */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(900, 1020));
        this.setResizable(false); // for now

        try {
            File file = new File(resourcePath + boardImageUrl);
            FileInputStream fis = new FileInputStream(file);
            boardImage = ImageIO.read(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Mouse Adapter */
        java.awt.event.MouseAdapter mA = new MouseAdapterHandler(controller);

        /* Junctions y / x */
        final int firstRow, aColumn;
        firstRow = aColumn = 30;
        final int secondRow, bColumn;
        secondRow = bColumn = 167;
        final int thirdRow, cColumn;
        thirdRow = cColumn = 303;
        final int fourthRow, dColumn;
        fourthRow = dColumn = 440;
        final int fifthRow, eColumn;
        fifthRow = eColumn = 577;
        final int sixthRow, fColumn;
        sixthRow = fColumn = 713;
        final int seventhRow, gColumn;
        seventhRow = gColumn = 849;

        //a1---------a4--------a7
        controller.getBoard().getBoardMap().get("a1").placeOnGui("a1", firstRow, aColumn, mA);
        controller.getBoard().getBoardMap().get("a4").placeOnGui("a4", fourthRow, aColumn, mA);
        controller.getBoard().getBoardMap().get("a7").placeOnGui("a7", seventhRow, aColumn, mA);

        //----b2-----b4-----b6----
        controller.getBoard().getBoardMap().get("b2").placeOnGui("b2", secondRow, bColumn, mA);
        controller.getBoard().getBoardMap().get("b4").placeOnGui("b4", fourthRow, bColumn, mA);
        controller.getBoard().getBoardMap().get("b6").placeOnGui("b6", sixthRow, bColumn, mA);

        //-------c3--c4--c5-------
        controller.getBoard().getBoardMap().get("c3").placeOnGui("c3", thirdRow, cColumn, mA);
        controller.getBoard().getBoardMap().get("c4").placeOnGui("c4", fourthRow, cColumn, mA);
        controller.getBoard().getBoardMap().get("c5").placeOnGui("c5", fifthRow, cColumn, mA);

        //d1--d2--d3-----d5--d6--d7
        controller.getBoard().getBoardMap().get("d1").placeOnGui("d1", firstRow, dColumn, mA);
        controller.getBoard().getBoardMap().get("d2").placeOnGui("d2", secondRow, dColumn, mA);
        controller.getBoard().getBoardMap().get("d3").placeOnGui("d3", thirdRow, dColumn, mA);
        controller.getBoard().getBoardMap().get("d5").placeOnGui("d5", fifthRow, dColumn, mA);
        controller.getBoard().getBoardMap().get("d6").placeOnGui("d6", sixthRow, dColumn, mA);
        controller.getBoard().getBoardMap().get("d7").placeOnGui("d7", seventhRow, dColumn, mA);

        //-------e3--e4--e5-------
        controller.getBoard().getBoardMap().get("e3").placeOnGui("e3", thirdRow, eColumn, mA);
        controller.getBoard().getBoardMap().get("e4").placeOnGui("e4", fourthRow, eColumn, mA);
        controller.getBoard().getBoardMap().get("e5").placeOnGui("e5", fifthRow, eColumn, mA);

        //----f2-----f4-----f6----
        controller.getBoard().getBoardMap().get("f2").placeOnGui("f2", secondRow, fColumn, mA);
        controller.getBoard().getBoardMap().get("f4").placeOnGui("f4", fourthRow, fColumn, mA);
        controller.getBoard().getBoardMap().get("f6").placeOnGui("f6", sixthRow, fColumn, mA);

        //g1---------g4--------g7
        controller.getBoard().getBoardMap().get("g1").placeOnGui("g1", firstRow, gColumn, mA);
        controller.getBoard().getBoardMap().get("g4").placeOnGui("g4", fourthRow, gColumn, mA);
        controller.getBoard().getBoardMap().get("g7").placeOnGui("g7", seventhRow, gColumn, mA);

        /* Console */
        consolePanel = new JPanel();
        consoleArea = new JTextArea();
        consoleArea.setRows(4);
        consoleArea.setEditable(false);
        consoleArea.setMargin(new Insets(10,20,10,20));
        consoleArea.setText(statusMessage + manual);
        consolePanel.add(new JScrollPane(consoleArea));
        consolePanel.setMaximumSize(new Dimension(1000, 340));
        BoxLayout layout = new BoxLayout(consolePanel, BoxLayout.X_AXIS);
        consolePanel.setLayout(layout);

        /* Board */
        JComponent board = new BoardImage(boardImage);
        board.setLayout(null);


        for (Map.Entry<String, Junction> entry: controller.getBoard().getBoardMap().entrySet()) {
            board.add(entry.getValue());
        }

        JPanel InteractiveBoard = new JPanel();
        InteractiveBoard.setLayout(new BoxLayout(InteractiveBoard, BoxLayout.X_AXIS));
        InteractiveBoard.add(board);

        /* Packing */
        MasterPanel = new JPanel();
        MasterPanel.add(InteractiveBoard);
        MasterPanel.add(consolePanel);
        MasterPanel.setLayout(new BoxLayout(MasterPanel, BoxLayout.Y_AXIS));
        this.setContentPane(MasterPanel);
        this.setVisible(true);
    }

    @Override
    public void update() {
        String tmpMenu;
        tmpMenu = controller.getStatus();
        tmpMenu += "\nPuck's left: " + controller.getCurrentIPlayer().getNumPucks() + " | ";
        tmpMenu += "You're: "  + controller.getCurrentIPlayer().getMan().toString();

        consoleArea.setText(tmpMenu);
    }
}
