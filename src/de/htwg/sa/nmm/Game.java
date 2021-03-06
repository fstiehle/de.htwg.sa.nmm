package de.htwg.sa.nmm;

import java.util.Scanner;

import de.htwg.sa.nmm.aview.http.HttpServer;
import de.htwg.sa.nmm.aview.tui.TextUI;
import de.htwg.sa.nmm.controller.IGameController;

import com.google.inject.Guice;
import com.google.inject.Injector;


public final class Game {

    private static Scanner scanner;
    private static TextUI tui;
    private static HttpServer httpServer;
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

    public TextUI getTui() {
        return tui;
    }

    public Game() {
        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new GameModule());

        // Build up the application, resolving dependencies automatically by Guice
        controller = injector.getInstance(IGameController.class);
        controller.setInjector(injector);

        // injector.getInstance(MasterFrame.class);
        controller.update();

        tui = injector.getInstance(TextUI.class);
        tui.printTUI();

        httpServer = injector.getInstance(HttpServer.class);
        httpServer.run();
    }

    public static void main(String[] args) {
        Game.getInstance();

        boolean game = true; // quit on -q
        scanner = new Scanner(System.in);
        while (game) {
            game = tui.processInputLine(scanner.next());
        }
    }
}