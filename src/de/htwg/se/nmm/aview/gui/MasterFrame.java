package de.htwg.se.nmm.aview.gui;

import com.google.inject.Inject;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.util.observer.IObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        JunctionPanel a1 = new JunctionPanel(puckImage, firstRow, aColumn, mA);
        a1.setName("a1");
        JunctionPanel a4 = new JunctionPanel(puckImage, fourthRow, aColumn, mA);
        a4.setName("a4");
        JunctionPanel a7 = new JunctionPanel(puckImage, seventhRow, aColumn, mA);
        a7.setName("a7");

        //----b2-----b4-----b6----
        JunctionPanel b2 = new JunctionPanel(puckImage, secondRow, bColumn, mA);
        b2.setName("b2");
        JunctionPanel b4 = new JunctionPanel(puckImage, fourthRow, bColumn, mA);
        b4.setName("b4");
        JunctionPanel b6 = new JunctionPanel(puckImage, sixthRow, bColumn, mA);
        b6.setName("b6");

        //-------c3--c4--c5-------
        JunctionPanel c3 = new JunctionPanel(puckImage, thirdRow, cColumn, mA);
        c3.setName("c3");
        JunctionPanel c4 = new JunctionPanel(puckImage, fourthRow, cColumn, mA);
        c4.setName("c4");
        JunctionPanel c5 = new JunctionPanel(puckImage, fifthRow, cColumn, mA);
        c5.setName("c5");

        //d1--d2--d3-----d5--d6--d7
        JunctionPanel d1 = new JunctionPanel(puckImage, firstRow, dColumn, mA);
        d1.setName("d1");
        JunctionPanel d2 = new JunctionPanel(puckImage, secondRow, dColumn, mA);
        d2.setName("d2");
        JunctionPanel d3 = new JunctionPanel(puckImage, thirdRow, dColumn, mA);
        d3.setName("d3");
        JunctionPanel d5 = new JunctionPanel(puckImage, fifthRow, dColumn, mA);
        d5.setName("d5");
        JunctionPanel d6 = new JunctionPanel(puckImage, sixthRow, dColumn, mA);
        d6.setName("d6");
        JunctionPanel d7 = new JunctionPanel(puckImage, seventhRow, dColumn, mA);
        d7.setName("d7");

        //-------e3--e4--e5-------
        JunctionPanel e3 = new JunctionPanel(puckImage, thirdRow, eColumn, mA);
        e3.setName("e3");
        JunctionPanel e4 = new JunctionPanel(puckImage, fourthRow, eColumn, mA);
        e4.setName("e4");
        JunctionPanel e5 = new JunctionPanel(puckImage, fifthRow, eColumn, mA);
        e5.setName("e5");

        //----f2-----f4-----f6----
        JunctionPanel f2 = new JunctionPanel(puckImage, secondRow, fColumn, mA);
        f2.setName("f2");
        JunctionPanel f4 = new JunctionPanel(puckImage, fourthRow, fColumn, mA);
        f4.setName("f4");
        JunctionPanel f6 = new JunctionPanel(puckImage, sixthRow, fColumn, mA);
        f6.setName("f6");

        //g1---------g4--------g7
        JunctionPanel g1 = new JunctionPanel(puckImage, firstRow, gColumn, mA);
        g1.setName("g1");
        JunctionPanel g4 = new JunctionPanel(puckImage, fourthRow, gColumn, mA);
        g4.setName("g4");
        JunctionPanel g7 = new JunctionPanel(puckImage, seventhRow, gColumn, mA);
        g7.setName("g7");

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

    @Override
    public void update() {
        refreshGUI();
    }

    private void refreshGUI() {

    }
}
