package de.htwg.se.nmm.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.util.observer.Observable;

import java.lang.reflect.Method;

@Singleton
public class GameController extends Observable implements IGameController {

    private static final int HOP_THRESHOLD = 6;
    private static final int LOST_THRESHOLD = 7;

    private StringBuilder statusMessage;
    private IPlayer white;
    private IPlayer black;
    private IPlayer currentPlayer;
    private Injector injector;
    private IBoard board;

    @Inject
    public GameController(IBoard board) {
        this.board = board;
        this.white = null;
        this.black = null;
        this.currentPlayer = null;
        this.statusMessage = new StringBuilder();
        this.statusMessage.append("Welcome! ");
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    public Injector getInjector() {
        return this.injector;
    }

    @Override
    public IPlayer getOtherPlayer() {
        if (this.currentPlayer == this.white) {
            return this.black;
        } else {
            return this.white;
        }
    }

    @Override
    public void changePlayer() {
        IPlayer otherPlayer = this.getOtherPlayer();
        if (otherPlayer.getPucksTakenAway() == LOST_THRESHOLD) {
            otherPlayer.setStatus(otherPlayer.getSET());
        }

        this.currentPlayer = otherPlayer;
        IPlayer current = otherPlayer;
        String name = this.currentPlayer.getName();

        if(current.hasLost()) {
            this.addStatusMessage(name + ": You failed.");

        } else if (!current.hasPucks() && current.getPucksTakenAway() < HOP_THRESHOLD) {
            currentPlayer.setStatus(current.getMOVE());
            this.addStatusMessage(name + ": It's now time to move.");

        } else if (current.getPucksTakenAway() == HOP_THRESHOLD ) {
            current.setStatus(current.getHOP());
            this.addStatusMessage(name + ": It's now time to hop.");

        }  else if(this.currentPlayer.hasPucks()) {
            current.setStatus(current.getSET());
            this.addStatusMessage(name + ": It's now time to set.");
        }
    }

    @Override
    public IPlayer getCurrentIPlayer() {
        return this.currentPlayer;
    }

    public Player getCurrentPlayer() {
        return (Player) this.currentPlayer;
    }

    @Override
    public void addStatusMessage(String statusMessage) {
        this.clearStatusMessage();
        this.statusMessage.append("\n");
        this.statusMessage.append(statusMessage);
    }

    private void clearStatusMessage() {
        this.statusMessage = new StringBuilder();
    }

    @Override
    public void millAfterMove(IJunction j) {
        if (checkformill(j, this.currentPlayer)) {
            this.addStatusMessage("Congratulations, Sir!\n" +
                    "You may now pick one of your opponents pucks that is not part of a mill.");
            this.currentPlayer.setStatus(this.currentPlayer.getPICK());
        } else {
            this.changePlayer();
        }
    }

    @Override
    public boolean checkformill(IJunction j, IPlayer p) {
        int mill = -1;
        mill += checkformillR(j, 0, "Down", p);
        mill += checkformillR(j, 0, "Up", p);
        if(mill >= 3) {
            return true;
        }

        mill = -1;
        mill += checkformillR(j, 0, "Left", p);
        mill += checkformillR(j, 0, "Right", p);
        if(mill >= 3) {
            return true;
        }

        return false;
    }

    private int checkformillR(IJunction j, int sum, String direction, IPlayer p) {
        int t = 1;
        Method method;

        String m = "get" + direction;
        try {
            method = j.getClass().getMethod(m);

            if (method.invoke(j) != null) {
                if (((IJunction) method.invoke(j)).hasPuck() &&
                        ((IJunction) method.invoke(j)).getPuck().getPlayer().equals(p)) {
                    t += sum;
                    t += checkformillR((IJunction) method.invoke(j), sum + 1, direction, p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void update() {
        notifyObservers();
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public void createPlayer(String name1, String name2) {
        this.white = new Player(name1, Player.Man.WHITE, this);
        this.black = new Player(name2, Player.Man.BLACK, this);
        this.currentPlayer = this.white;
        this.statusMessage.append(this.getCurrentIPlayer().getName());
        this.statusMessage.append(" may start by setting the first puck.");
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }

    @Override
    public String getStatus() {
        return this.statusMessage.toString();
    }

}