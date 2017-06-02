package de.htwg.sa.nmm.controller.impl;

import java.util.*;

import akka.http.javadsl.server.Route;
import de.htwg.sa.nmm.controller.IJsonController;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.model.IPlayer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.http.javadsl.model.StatusCodes;

import static akka.http.javadsl.server.Directives.*;



public class JsonController implements IJsonController {

    private final IGameController gameController;

    public JsonController(IGameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public JsonNode refreshGame(JsonNode json) throws IllegalArgumentException {
        gameController.update();
        return null;
    }

    @Override
    public JsonNode setPlayerName(JsonNode json) throws IllegalArgumentException {

        // convert params to String
        String man = json.findPath("command").textValue();
        String name = json.findPath("query").textValue();
        if(man == null) {
            throw new IllegalArgumentException("Bad parameter [command]");
        }
        if(name == null) {
            throw new IllegalArgumentException("Bad parameter [query]");
        }

        switch (man) {
            case "WHITE":
                gameController.getPlayer(IPlayer.Man.WHITE).setName(name);
                break;
            case "BLACK":
                gameController.getPlayer(IPlayer.Man.BLACK).setName(name);
                break;
            default:
                throw new IllegalArgumentException("Bad parameter [man]");
        }
        gameController.update();
        return null;
    }

    @Override
    public JsonNode resetGame(JsonNode json) throws IllegalArgumentException {
        gameController.initNewGame();
        gameController.update();
        return null;
    }

    @Override
    public Route processCommand(JsonNode json) throws IllegalArgumentException {
        String command = null;
        JsonNode queryNode = null;
        List<String> queryList = null;

        // convert command to String
        command = json.findPath("command").textValue();
        if(command == null) {
            throw new IllegalArgumentException("Bad parameter [command]");
        }

        // convert query to ArrayList
        try {
            queryNode = json.findPath("query");
            queryList = new ObjectMapper().convertValue(queryNode, ArrayList.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameter [query]");
        }

        switch (command) {
            case "set":
                IPuck p = gameController.getInjector().getInstance(IPuck.class);
                p.setPlayer(gameController.getCurrentIPlayer());
                gameController.setPuck(queryList.get(0), p);
                break;
            case "pick":
                gameController.pickPuck(queryList.get(0));
                break;
            case "move":
                gameController.movePuck(queryList.get(0), queryList.get(1));
                break;
            default:
                throw new IllegalArgumentException("Bad parameter [command]");
        }
        gameController.update();
        return null;
        //return complete(StatusCodes.OK, RawHeader("Content-Type", "application/json"), "");
    }
}
