package de.htwg.sa.nmm.controller.impl;

import java.io.IOException;
import java.util.*;

import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.server.Route;
import de.htwg.sa.nmm.controller.IJsonController;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.model.IPlayer;

import akka.http.javadsl.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static akka.http.javadsl.server.Directives.*;

import akka.http.javadsl.model.StatusCodes;
import de.htwg.sa.nmm.model.impl.Player;

public class JsonController implements IJsonController {

    private final IGameController gameController;

    public JsonController(IGameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public Route refreshGame() {
        gameController.update();
        return complete(StatusCodes.OK);
    }

    @Override
    public Route setPlayerName(String jsonStr) throws IllegalArgumentException {

        System.out.println(jsonStr);

        // convert string to JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new IllegalArgumentException("Illegal Json format");
        }

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
        return complete(StatusCodes.OK);
    }

    @Override
    public Route resetGame() {
        gameController.initNewGame();
        gameController.update();

        return complete(StatusCodes.OK);
    }

    @Override
    public Route processCommand(String jsonStr) throws IllegalArgumentException {
        // convert string to JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new IllegalArgumentException("Illegal Json format");
        }

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
        return complete(StatusCodes.OK);
    }

    @Override
    public Route getGameSession() {
        String jsonData = gameController.getJson();

        return complete(
                StatusCodes.OK,
                HttpEntities.create(ContentTypes.APPLICATION_JSON, jsonData)
        );
    }

    @Override
    public Route getPlayerWithoutUID(String content) {
        // convert string to JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json;
        try {
            json = mapper.readTree(content);
        } catch (IOException e) {
            throw new IllegalArgumentException("Illegal Json format");
        }

        List<String> list;
        try {
            list = new ObjectMapper().convertValue(json, ArrayList.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameter [query]");
        }

        if (list.isEmpty()) {
            throw new IllegalArgumentException("Bad parameter [query]");
        }

        IPlayer player = gameController.getPlayerWithoutUserID(UUID.fromString(list.get(0)));
        if (player != null) {
            player.setName(list.get(1));
            player.setUserID(UUID.fromString(list.get(0)));
        }
        return complete(StatusCodes.OK,
            HttpEntities.create(ContentTypes.APPLICATION_JSON, gameController.getJson()));
    }
}
