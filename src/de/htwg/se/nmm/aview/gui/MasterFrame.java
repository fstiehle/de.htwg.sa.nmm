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

    /* Where everything comes together */
    JPanel MasterPanel;

    JTextArea consoleArea;
    JPanel consolePanel;
    String statusMessage;

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
        this.setSize(new Dimension(1000, 1000));

        try {
            File file = new File(resourcePath + boardImageUrl);
            FileInputStream fis = new FileInputStream(file);
            boardImage = ImageIO.read(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Console */
        consolePanel = new JPanel();
        consoleArea = new JTextArea(statusMessage);
        consolePanel.setPreferredSize(new Dimension(1000,200));
        consolePanel.setMaximumSize(new Dimension(1000, 200));
        consoleArea.setRows(5);
        consoleArea.setEditable(false);
        consoleArea.setMargin(new Insets(10,10,10,10));
        consolePanel.add(new JScrollPane(consoleArea));
        consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.PAGE_AXIS));

        /* Board */
        JComponent board = new BoardImage(boardImage);

        /* Packing */
        MasterPanel = new JPanel();
        MasterPanel.add(board);
        MasterPanel.add(consolePanel);
        MasterPanel.setLayout(new BoxLayout(MasterPanel, BoxLayout.PAGE_AXIS));

        this.setContentPane(MasterPanel);
        this.setVisible(true);
    }
}
