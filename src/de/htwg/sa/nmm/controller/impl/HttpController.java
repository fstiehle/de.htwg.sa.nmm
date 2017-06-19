package de.htwg.sa.nmm.controller.impl;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.util.ByteString;
import scala.concurrent.ExecutionContextExecutor;

import static akka.pattern.Patterns.pipe;

import java.util.concurrent.CompletionStage;

/**
 * Delegate of GameController
 */
public class HttpController {

    private final GameController gameController;

    public HttpController(GameController gameController) {
        this.gameController = gameController;
    }

    public void httpRequest() {
        final ActorSystem system = ActorSystem.create("out");
        final Materializer materializer = ActorMaterializer.create(system);

        ByteString wurst = ByteString.fromString("{\n" +
                "    \"board\": {\n" +
                "        \"g1\": {\n" +
                "            \"man\": \"WHITE\"\n" +
                "        },\n" +
                "        \"f2\": {},\n" +
                "        \"d1\": {\n" +
                "            \"man\": \"WHITE\"\n" +
                "        },\n" +
                "        \"g4\": {},\n" +
                "        \"d2\": {},\n" +
                "        \"e3\": {},\n" +
                "        \"f4\": {},\n" +
                "        \"d3\": {},\n" +
                "        \"e4\": {},\n" +
                "        \"a1\": {\n" +
                "            \"man\": \"WHITE\"\n" +
                "        },\n" +
                "        \"b2\": {},\n" +
                "        \"c3\": {},\n" +
                "        \"e5\": {},\n" +
                "        \"f6\": {},\n" +
                "        \"g7\": {},\n" +
                "        \"c4\": {},\n" +
                "        \"d5\": {},\n" +
                "        \"b4\": {},\n" +
                "        \"c5\": {},\n" +
                "        \"d6\": {},\n" +
                "        \"a4\": {},\n" +
                "        \"d7\": {},\n" +
                "        \"b6\": {},\n" +
                "        \"a7\": {}\n" +
                "    },\n" +
                "    \"move\": {\n" +
                "        \"modus\": \"SET\",\n" +
                "        \"man\": \"WHITE\",\n" +
                "        \"junction\": \"a7\"\n" +
                "    }\n" +
                "}");

        final CompletionStage<HttpResponse> responseFuture =
                Http.get(system)
                        .singleRequest(HttpRequest.POST("http://localhost:8081/checkMill").withEntity(wurst), materializer);

        responseFuture.whenComplete((response, error) -> {
            if (error != null) {
                System.out.println(error.getMessage());
                return;
            }

            System.out.println(response.entity());
        });
    }
}