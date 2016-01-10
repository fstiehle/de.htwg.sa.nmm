package de.htwg.se.nmm.aview.gui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.util.observer.IObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MasterFrame extends JFrame implements IObserver {

    IGameController controller;
    BufferedImage boardImage;
    private final String resourcePath = "src/de/htwg/se/nmm/aview/gui/";
    private final String boardImageUrl = "board.png";
    private final String puckImageUrl = "puck.png";

    /* Where everything comes together */
    JPanel MasterPanel;

    JTextArea consoleArea;
    JPanel consolePanel;
    String statusMessage;
    BufferedImage puckImage;

    @Override
    public void update() {

    }

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
        JunctionPanel a1 = new JunctionPanel(puckImage, firstRow, aColumn);
        JunctionPanel a4 = new JunctionPanel(puckImage, fourthRow, aColumn);
        JunctionPanel a7 = new JunctionPanel(puckImage, seventhRow, aColumn);

        //----b2-----b4-----b6----
        JunctionPanel b2 = new JunctionPanel(puckImage, secondRow, bColumn);
        JunctionPanel b4 = new JunctionPanel(puckImage, fourthRow, bColumn);
        JunctionPanel b6 = new JunctionPanel(puckImage, sixthRow, bColumn);

        //-------c3--c4--c5-------
        JunctionPanel c3 = new JunctionPanel(puckImage, thirdRow, cColumn);
        JunctionPanel c4 = new JunctionPanel(puckImage, fourthRow, cColumn);
        JunctionPanel c5 = new JunctionPanel(puckImage, fifthRow, cColumn);

        //d1--d2--d3-----d5--d6--d7
        JunctionPanel d1 = new JunctionPanel(puckImage, firstRow, dColumn);
        JunctionPanel d2 = new JunctionPanel(puckImage, secondRow, dColumn);
        JunctionPanel d3 = new JunctionPanel(puckImage, thirdRow, dColumn);
        JunctionPanel d5 = new JunctionPanel(puckImage, fifthRow, dColumn);
        JunctionPanel d6 = new JunctionPanel(puckImage, sixthRow, dColumn);
        JunctionPanel d7 = new JunctionPanel(puckImage, seventhRow, dColumn);

        //-------e3--e4--e5-------
        JunctionPanel e3 = new JunctionPanel(puckImage, thirdRow, eColumn);
        JunctionPanel e4 = new JunctionPanel(puckImage, fourthRow, eColumn);
        JunctionPanel e5 = new JunctionPanel(puckImage, fifthRow, eColumn);

        //----f2-----f4-----f6----
        JunctionPanel f2 = new JunctionPanel(puckImage, secondRow, fColumn);
        JunctionPanel f4 = new JunctionPanel(puckImage, fourthRow, fColumn);
        JunctionPanel f6 = new JunctionPanel(puckImage, sixthRow, fColumn);

        //g1---------g4--------g7
        JunctionPanel g1 = new JunctionPanel(puckImage, firstRow, gColumn);
        JunctionPanel g4 = new JunctionPanel(puckImage, fourthRow, gColumn);
        JunctionPanel g7 = new JunctionPanel(puckImage, seventhRow, gColumn);

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
        board.setLayout(new OverlayLayout(board));

        board.add(a1); board.add(a4); board.add(a7); board.add(b2); board.add(b4);
        board.add(c3); board.add(c4); board.add(c5); board.add(d1); board.add(d2);
        board.add(d3); board.add(d5); board.add(d6); board.add(d7); board.add(e3);
        board.add(e4); board.add(e5); board.add(f2); board.add(f4); board.add(f6);
        board.add(g1); board.add(g4); board.add(g7); board.add(b6);

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
}
