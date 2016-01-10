package de.htwg.se.nmm.aview.gui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.util.observer.IObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MasterFrame extends JFrame implements IObserver {

    IGameController controller;
    BufferedImage boardImage;
    private final String resourcePath = "src/de/htwg/se/nmm/aview/gui/";
    private final String boardImageUrl = "board.png";
    private final String puckImageUrl = "puck.png";

    Map<String, JunctionPanel> jpMap = new HashMap<>();

    /* Where everything comes together */
    JPanel MasterPanel;

    JTextArea consoleArea;
    JPanel consolePanel;
    String statusMessage;
    BufferedImage puckImage;

    @Inject
    public MasterFrame(final IGameController controller) {
        this.controller = controller;
        controller.addObserver(this);
        statusMessage = controller.getStatus();

        System.out.println(System.getProperty("user.dir"));

        /* Settings */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(new BoardListener());
        this.setSize(new Dimension(900, 1020));
        this.setResizable(false); // for now

        try {
            File file = new File(resourcePath + boardImageUrl);
            FileInputStream fis = new FileInputStream(file);
            boardImage = ImageIO.read(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(resourcePath + puckImageUrl);
            FileInputStream fis = new FileInputStream(file);
            puckImage = ImageIO.read(fis);
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
        JunctionPanel a1 = new JunctionPanel("a1", jpMap, firstRow, aColumn, mA);
        JunctionPanel a4 = new JunctionPanel("a4", jpMap, fourthRow, aColumn, mA);
        JunctionPanel a7 = new JunctionPanel("a7", jpMap, seventhRow, aColumn, mA);

        //----b2-----b4-----b6----
        JunctionPanel b2 = new JunctionPanel("b2", jpMap, secondRow, bColumn, mA);
        JunctionPanel b4 = new JunctionPanel("b4", jpMap, fourthRow, bColumn, mA);
        JunctionPanel b6 = new JunctionPanel("b6", jpMap, sixthRow, bColumn, mA);

        //-------c3--c4--c5-------
        JunctionPanel c3 = new JunctionPanel("c3", jpMap, thirdRow, cColumn, mA);
        JunctionPanel c4 = new JunctionPanel("c4", jpMap, fourthRow, cColumn, mA);
        JunctionPanel c5 = new JunctionPanel("c5", jpMap, fifthRow, cColumn, mA);

        //d1--d2--d3-----d5--d6--d7
        JunctionPanel d1 = new JunctionPanel("d1", jpMap, firstRow, dColumn, mA);
        JunctionPanel d2 = new JunctionPanel("d2", jpMap, secondRow, dColumn, mA);
        JunctionPanel d3 = new JunctionPanel("d3", jpMap, thirdRow, dColumn, mA);
        JunctionPanel d5 = new JunctionPanel("d5", jpMap, fifthRow, dColumn, mA);
        JunctionPanel d6 = new JunctionPanel("d6", jpMap, sixthRow, dColumn, mA);
        JunctionPanel d7 = new JunctionPanel("d7", jpMap, seventhRow, dColumn, mA);;

        //-------e3--e4--e5-------
        JunctionPanel e3 = new JunctionPanel("e3", jpMap, thirdRow, eColumn, mA);
        JunctionPanel e4 = new JunctionPanel("e4", jpMap, fourthRow, eColumn, mA);
        JunctionPanel e5 = new JunctionPanel("e5", jpMap, fifthRow, eColumn, mA);

        //----f2-----f4-----f6----
        JunctionPanel f2 = new JunctionPanel("f2", jpMap, secondRow, fColumn, mA);
        JunctionPanel f4 = new JunctionPanel("f4", jpMap, fourthRow, fColumn, mA);
        JunctionPanel f6 = new JunctionPanel("f6", jpMap, sixthRow, fColumn, mA);

        //g1---------g4--------g7
        JunctionPanel g1 = new JunctionPanel("g1", jpMap, firstRow, gColumn, mA);
        JunctionPanel g4 = new JunctionPanel("g4", jpMap, fourthRow, gColumn, mA);
        JunctionPanel g7 = new JunctionPanel("g7", jpMap, seventhRow, gColumn, mA);

        /* Console */
        consolePanel = new JPanel();
        consoleArea = new JTextArea(statusMessage);
        consoleArea.setRows(5);
        consoleArea.setEditable(false);
        consoleArea.setMargin(new Insets(10,10,10,10));
        consolePanel.add(new JScrollPane(consoleArea));
        consolePanel.setMaximumSize(new Dimension(1000, 340));
        BoxLayout layout = new BoxLayout(consolePanel, BoxLayout.X_AXIS);
        consolePanel.setLayout(layout);

        /* Board */
        JComponent board = new BoardImage(boardImage);
        board.setLayout(null);

        for (Map.Entry<String, JunctionPanel> entry: jpMap.entrySet()) {
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
        refreshGUI();
    }

    private void refreshGUI() {
        Map<String, Junction> board = controller.getBoard().getBoardMap();

        for (Map.Entry<String, Junction> entryx : board.entrySet()) {
            for (Map.Entry<String, JunctionPanel> entryy: jpMap.entrySet()) {
                if(entryx.getKey().equals(entryy.getKey()) && entryx.getValue().hasPuck()) {
                    entryy.getValue().say();
                }
            }
        }

    }
}
