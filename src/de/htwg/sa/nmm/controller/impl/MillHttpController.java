package de.htwg.sa.nmm.controller.impl;

import akka.http.javadsl.model.HttpEntity;
import akka.http.javadsl.model.HttpResponse;
import akka.util.ByteString;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.htwg.sa.nmm.controller.IMillController;
import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;

import java.util.HashMap;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Delegate of GameController
 */
public class MillHttpController implements IMillController {

    private final HttpController httpController;
    private boolean isMill;

    private Lock lock = new ReentrantLock();
    private Condition complete = lock.newCondition();

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

        this.isMill = false;
        CompletionStage<HttpResponse> responseFuture = this.httpController.httpPOSTRequest("checkMill", data);

            responseFuture.whenComplete((response, error) -> {
                lock.lock();
                if (error != null) {
                    System.out.println(error.getMessage());
                    return;
                }

                ByteString res = ((HttpEntity.Strict) response.entity()).getData();

                JsonElement jElement = new JsonParser().parse(res.utf8String());
                JsonElement jMill = jElement.getAsJsonObject().get("mill");

                this.isMill = gson.fromJson(jMill, Boolean.class);
                System.out.println("isMill INSIDE = " + this.isMill);
                this.complete.signalAll();
                this.lock.unlock();
            });

        this.lock.lock();
        try {
            this.complete.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }

        //System.out.println("isMill OUTSIDE = " + this.isMill);
        return isMill;
    }
}