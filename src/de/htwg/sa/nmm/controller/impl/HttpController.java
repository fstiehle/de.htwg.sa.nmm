package de.htwg.sa.nmm.controller.impl;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.ResponseEntity;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.util.ByteString;
import scala.concurrent.ExecutionContextExecutor;

import static akka.pattern.Patterns.pipe;

import java.net.URI;
import java.util.concurrent.CompletionStage;

public class HttpController {

    final static String ACTOR_SYSTEM_NAME = "out";
    final Materializer materializer;
    final ActorSystem system;
    final String uri;

    public HttpController(String uri) {
        this.uri = uri;

        system = ActorSystem.create(ACTOR_SYSTEM_NAME);
        materializer = ActorMaterializer.create(system);
    }

    /**
     * HTTP POST Request
     *
     * @param path
     * @param body
     * @return
     */
    public CompletionStage<HttpResponse> httpPOSTRequest(String path, ByteString body) {

        return Http.get(system).singleRequest(HttpRequest.POST(
                            String.format("%s/%s", this.uri, path)).withEntity(body),
                            this.materializer
        );

    }

    /**
     * HTTP GET Request
     *
     * @param path
     * @return
     */
    public CompletionStage<HttpResponse> httpGETRequest(String path) {

        return Http.get(system).singleRequest(HttpRequest.GET(
                String.format("%s/%s", this.uri.toString(), path)),
                this.materializer
        );

    }
}