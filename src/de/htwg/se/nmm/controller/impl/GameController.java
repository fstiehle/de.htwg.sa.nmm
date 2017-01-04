package de.htwg.se.nmm.controller.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.util.observer.Observable;

import java.lang.reflect.Method;
import java.util.*;

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
        initNewGame(board);
    }

    @Override
    public void initNewGame() {
        initNewGame(new Board());
    }

    @Override
    public void initNewGame(IBoard board) {
        this.board = board;
        this.white = null;
        this.black = null;
        this.currentPlayer = null;
        this.statusMessage = new StringBuilder();
        this.statusMessage.append("Welcome! ");

        this.createPlayer("Player 1", "Player 2");
    }

    @Override
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
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
            throw new RuntimeException(e);
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
    public String getJson() {
        HashMap<String, Object> objList = new HashMap<>();
        objList.put("code", "200");
        objList.put("black", this.black.getData());
        objList.put("white", this.white.getData());
        objList.put("currentPlayer", this.currentPlayer.getData());
        objList.put("board", this.board.getData());

        HashMap<String, Object> status = new HashMap<>();
            status.put("message", this.getStatus(true));
            status.put("man", this.currentPlayer.getMan().toString());
            status.put("pucksLeft", this.currentPlayer.getNumPucks());
            status.put("modus", this.currentPlayer.getStatus().toString());
        objList.put("status", status);

        Gson gson = new Gson();
        return gson.toJson(objList);
    }

    @Override
    public void createPlayer(String name1, String name2) {
        this.white = new Player(name1, Player.Man.WHITE);
        this.black = new Player(name2, Player.Man.BLACK);
        this.currentPlayer = this.white;
        this.statusMessage.append(this.getCurrentIPlayer().getName());
        this.statusMessage.append(" may start by setting the first puck.");
    }

    @Override
    public IPlayer getPlayerWithoutUserID(UUID userID) {
        if (this.white.getUserID() == null && this.black.getUserID() != userID) {
            return this.white;
        }
        if (this.black.getUserID() == null && this.white.getUserID() != userID) {
            return this.black;
        }

        return null;
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }

    @Override
    public String getStatus() {
        return this.statusMessage.toString();
    }

    @Override
    public String getStatus(boolean clean) {
        if (clean) {
            return this.statusMessage.toString().replaceAll("\\s+", " ").trim();
        }
        return getStatus();
    }

    @Override
    public IPlayer getPlayer(IPlayer.Man man) {
        switch (man) {
            case WHITE:
                return this.white;
            case BLACK:
                return this.black;
            default:
                return null;
        }
    }

    @Override
    public void setPuck(String s, IPuck puck) {
        IJunction j = this.getBoard().getBoardMap().get(s);
        try {
            getCurrentIPlayer().getStatus().setPuck(j, puck, this.getCurrentIPlayer());
        } catch (Exception e) {
            this.addStatusMessage(e.getMessage());
            return;
        }
        j.setPuck(puck);
        currentPlayer.decrementPucks();
        this.millAfterMove(j);
    }

    @Override
    public void pickPuck(String s) {
        IJunction j = this.getBoard().getBoardMap().get(s);
        try {
            getCurrentIPlayer().getStatus().pickPuck(j, this.getCurrentIPlayer());
        } catch (Exception e) {
        this.addStatusMessage(e.getMessage());
        return;
        }
        if (this.checkformill(j, this.getOtherPlayer())) {
            this.addStatusMessage("Can't take away a puck if it is part of a mill.");
            return;
        }
        j.setPuck(null);
        this.getOtherPlayer().incrementPucksTakenAway();
        this.changePlayer();
    }

    @Override
    public void movePuck(String from, String to) {
        IJunction jFrom = this.getBoard().getBoardMap().get(from);
        IJunction jTo = this.getBoard().getBoardMap().get(to);
        try {
            getCurrentIPlayer().getStatus().movePuck(jFrom, jTo, this.getCurrentIPlayer());
        } catch (Exception e) {
            this.addStatusMessage(e.getMessage());
            return;
        }
        jTo.setPuck(jFrom.getPuck());
        jFrom.setPuck(null);
        millAfterMove(jTo);
    }

}