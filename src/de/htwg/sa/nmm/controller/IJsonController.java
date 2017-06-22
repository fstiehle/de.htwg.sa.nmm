package de.htwg.sa.nmm.controller;

import akka.http.javadsl.server.Route;


public interface IJsonController {

    Route setPlayerName(String json);

    Route resetGame();

    Route processCommand(String jsonNode);

    Route refreshGame();

    Route getGameSession();

    Route getPlayerWithoutUID(String content);

    Route loadGame(String content);

    Route saveGame(String content);

}
