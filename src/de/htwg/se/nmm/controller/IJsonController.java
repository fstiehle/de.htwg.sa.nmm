package de.htwg.se.nmm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

/**
 * Created by fabianstiehle on 01.06.17.
 */
public interface IJsonController {

    JsonNode setPlayerName(IGameController controller, JsonNode jsonNode);
    JsonNode resetGame(IGameController controller, JsonNode jsonNode);
    JsonNode processCommand(IGameController controller, JsonNode jsonNode);
    JsonNode refreshGame(IGameController controller, JsonNode jsonNode);

}
