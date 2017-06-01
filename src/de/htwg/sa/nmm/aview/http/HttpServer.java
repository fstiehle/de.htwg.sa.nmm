package de.htwg.sa.nmm.aview.http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.controller.IJsonController;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

@Singleton
public class HttpServer extends AllDirectives {

    private final IGameController gameController;
    private final IJsonController jsonController;

    @Inject
    public HttpServer(IGameController gameController, IJsonController jsonController) {
        this.gameController = gameController;
        this.jsonController = jsonController;
    }

    public void run() throws Exception {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = this.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read(); // let it run until user presses return

        binding
                .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
                .thenAccept(unbound -> system.terminate()); // and shutdown when done
    }

    private Route createRoute() {
        return route(
                path("setPlayerName", () ->
                        get(() ->
                                complete("<h1>Say hello to akka-http</h1>"))),

                path("resetGame", () ->
                        get(() ->
                                complete("<h1>Say hello to akka-http</h1>"))),

                path("processCommand", () ->
                        get(() ->
                                complete("<h1>Say hello to akka-http</h1>"))),

                path("refreshGame", () ->
                        get(() ->
                                complete("<h1>Say hello to akka-http</h1>")))
                );
    }

    /**
     * ProcessJson
     *
     * @param jsonStr
     * @throws IllegalArgumentException
     */
    private JsonNode processJson(String jsonStr) throws IllegalArgumentException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readValue(jsonStr, JsonNode.class);
        String type = json.findPath("type").textValue();
        if(type == null) {
            throw new IllegalArgumentException("Parameter [type] not found");
        }

        return null;
    }
}