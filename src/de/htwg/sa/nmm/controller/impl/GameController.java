package de.htwg.sa.nmm.controller.impl;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.model.*;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import de.htwg.sa.nmm.util.observer.Observable;

import java.util.*;

@Singleton
public class GameController extends Observable implements IGameController {

    private static final int HOP_THRESHOLD = 6;
    private static final int LOST_THRESHOLD = 7;
    private final MillController millController = new MillController(this);

    private StringBuilder statusMessage;
    private IPlayer whitePlayer;
    private IPlayer blackPlayer;
    private IPlayer currentPlayer;
    private Injector injector;
    private IBoard board;
    private IGameSessionDAO sessionDAO;
    private UUID sessionID;

    @Inject
    public GameController(IBoard board, IGameSessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
        this.sessionID = null;
        initNewGame(board);
    }

    @Override
    public void initNewGame() {
        initNewGame(new Board());
    }

    @Override
    public void initNewGame(IBoard board) {
        this.board = board;
        this.whitePlayer = null;
        this.blackPlayer = null;
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
        if (this.currentPlayer == this.whitePlayer) {
            return this.blackPlayer;
        } else {
            return this.whitePlayer;
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
        objList.put("black", this.blackPlayer.getData());
        objList.put("white", this.whitePlayer.getData());
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
        this.whitePlayer = new Player(name1, Player.Man.WHITE);
        this.blackPlayer = new Player(name2, Player.Man.BLACK);
        this.currentPlayer = this.whitePlayer;
        this.statusMessage.append(this.getCurrentIPlayer().getName());
        this.statusMessage.append(" may start by setting the first puck.");
    }

    @Override
    public IPlayer getPlayerWithoutUserID(UUID userID) {
        if (this.whitePlayer.getUserID() == null && this.blackPlayer.getUserID() != userID) {
            return this.whitePlayer;
        }
        if (this.blackPlayer.getUserID() == null && this.whitePlayer.getUserID() != userID) {
            return this.blackPlayer;
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
                return this.whitePlayer;
            case BLACK:
                return this.blackPlayer;
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
        millController.millAfterMove(j);
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
        if (millController.checkformill(j, this.getOtherPlayer())) {
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
        millController.millAfterMove(jTo);
    }

    public void loadGame(UUID sessionID) {
        IGameSession gameSession = sessionDAO.getSession(sessionID);
        board = gameSession.getBoard();
        blackPlayer = gameSession.getPlayerBlack();
        whitePlayer = gameSession.getPlayerWhite();
        currentPlayer = gameSession.getPlayerCurrent();
    }

    public void saveGame(String sessionName) {
        sessionDAO.saveSession(new GameSession(generateSessionID(sessionName),
                sessionName,
                board,
                blackPlayer,
                whitePlayer,
                currentPlayer));
    }

    public UUID generateSessionID(String sessionName) {
        if (whitePlayer.getUserID() == null || blackPlayer.getUserID() == null || sessionName == null) {
            return UUID.randomUUID();
        }
        return UUID.nameUUIDFromBytes(whitePlayer.getUserID().toString().concat(blackPlayer.getUserID().toString()).concat(sessionName).getBytes());
    }
}