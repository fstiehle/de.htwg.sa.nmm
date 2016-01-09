package de.htwg.se.nmm.controller;

import com.google.inject.Injector;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.util.observer.IObservable;

public interface IGameController extends IObservable {

    IPlayer getOtherPlayer();

    void changePlayer();

    IPlayer getCurrentIPlayer();

    void addStatusMessage(String statusMessage);

    void millAfterMove(IJunction j);

    boolean checkformill(IJunction j, IPlayer p);

    void update();

    IBoard getBoard();

    void createPlayer(String name1, String name2);

    String getBoardString();

    String getStatus();

    public void setInjector(Injector injector);

    public Injector getInjector();
}
