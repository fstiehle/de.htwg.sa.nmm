package de.htwg.se.nmm;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.se.nmm.aview.gui.MasterFrame;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.aview.tui.TextUI;

import com.google.inject.Guice;
import com.google.inject.Injector;


public final class Game {

    private static Scanner scanner;
    private static TextUI tui;
    private static MasterFrame gui;
    private IGameController controller;
    private static Game instance = null;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public IGameController getController() {
        return controller;
    }

    public TextUI getTUI() {
        return tui;
    }

    private Game() {
        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new GameModule());

        // Build up the application, resolving dependencies automatically by Guice
        controller = injector.getInstance(IGameController.class);
        controller.setInjector(injector);
        gui = injector.getInstance(MasterFrame.class);
        tui = injector.getInstance(TextUI.class);
        tui.printTUI();
    }

    public static void main(String[] args) {
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");

        Game.getInstance();

        boolean game = true; // quit on -q
        scanner = new Scanner(System.in);
        while (game) {
            game = tui.processInputLine(scanner.next());
        }
    }
}