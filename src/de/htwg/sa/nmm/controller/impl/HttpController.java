package de.htwg.sa.nmm.controller.impl;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.util.ByteString;
import com.google.inject.Inject;

import java.util.concurrent.CompletionStage;

public class HttpController {

    private final static String ACTOR_SYSTEM_NAME = "out";
    private static Materializer materializer = null;
    private static ActorSystem system = null;
    private static HttpController instance = null;
    private String uri;

    public static HttpController getInstance(String uri) {
        if (instance == null) {
            instance = new HttpController();
        }
        instance.setUri(uri);
        return instance;
    }

    private HttpController() {
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

    public void setUri(String uri) {
        this.uri = uri;
    }
}