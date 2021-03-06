package de.htwg.sa.nmm.controller;

import com.google.inject.Injector;
import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.util.observer.IObservable;

import java.util.UUID;

public interface IGameController extends IObservable {

    void initNewGame();

    void initNewGame(IBoard board);

    IPlayer getOtherPlayer();

    void changePlayer();

    IPlayer getCurrentIPlayer();

    void addStatusMessage(String statusMessage);

    void update();

    IBoard getBoard();

    void createPlayer(String name1, String name2);

    String getBoardString();

    String getJson();

    String getStatus();

    String getStatus(boolean clean);

    IPlayer getPlayer(IPlayer.Man man);

    IPlayer getPlayerWithoutUserID(UUID userID);

    void setInjector(Injector injector);

    Injector getInjector();

    void setPuck(String s, IPuck puck);

    void pickPuck(String s);

    void movePuck(String from, String to);

    void loadGame(UUID sessionID);

    void saveGame(String sessionName);
}
