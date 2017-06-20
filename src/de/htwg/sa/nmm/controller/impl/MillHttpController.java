package de.htwg.sa.nmm.controller.impl;

import akka.http.javadsl.model.HttpResponse;
import akka.util.ByteString;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.htwg.sa.nmm.controller.IMillController;
import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;

/**
 * Delegate of GameController
 */
public class MillHttpController implements IMillController {

    private final HttpController httpController;

    public MillHttpController() {
        this.httpController = new HttpController("http://localhost:8081");
    }


    // public to be testable TODO
    public boolean checkForMill(IBoard board, String junctionName, IPlayer p) {
        IJunction j = board.getBoardMap().get(junctionName);

        HashMap<String, Object> objList = new HashMap<>();
        objList.put("board", board.getData());

        HashMap<String, Object> move = new HashMap<>();
        move.put("modus", p.getStatus().toString());
        move.put("man", p.getMan().name());
        move.put("junction", junctionName);
        objList.put("move", move);

        Gson gson = new Gson();
        ByteString data = ByteString.fromString(gson.toJson(objList));

        CompletionStage<HttpResponse> responseFuture = this.httpController.httpPOSTRequest("checkMill", data);
        responseFuture.whenComplete((response, error) -> {
            if (error != null) {
                System.out.println(error.getMessage());
                return;
            }

            System.out.println(response.entity());
        });

        return false;
    }
}