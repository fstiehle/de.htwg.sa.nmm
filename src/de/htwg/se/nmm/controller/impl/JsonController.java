package de.htwg.se.nmm.controller.impl;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.inject.Inject;
import de.htwg.se.nmm.Game;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.controller.IJsonController;
import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.model.IPlayer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonController implements IJsonController {

    @Override
    public JsonNode refreshGame(IGameController gameController, JsonNode json) throws IllegalArgumentException {
        gameController.update();
    }

    @Override
    public JsonNode setPlayerName(IGameController gameController, JsonNode json) throws IllegalArgumentException {

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
    }

    @Override
    public JsonNode resetGame(IGameController gameController, JsonNode json) throws IllegalArgumentException {
        gameController.initNewGame();
        gameController.update();
    }

    @Override
    public JsonNode processCommand(IGameController gameController, JsonNode json) throws IllegalArgumentException {
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
    }
}
