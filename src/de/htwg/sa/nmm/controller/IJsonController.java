package de.htwg.sa.nmm.controller;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by fabianstiehle on 01.06.17.
 */
public interface IJsonController {

    JsonNode setPlayerName(JsonNode jsonNode);
    JsonNode resetGame(JsonNode jsonNode);
    JsonNode processCommand(JsonNode jsonNode);
    JsonNode refreshGame(JsonNode jsonNode);

}
