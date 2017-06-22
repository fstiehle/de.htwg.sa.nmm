package de.htwg.sa.nmm.aview.http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.unmarshalling.Unmarshaller;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.sa.nmm.controller.IGameController;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import de.htwg.sa.nmm.controller.IJsonController;
import de.htwg.sa.nmm.controller.impl.JsonController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Singleton
public class HttpServer extends AllDirectives {

    private final IGameController gameController;
    private final IJsonController jsonController;

    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());

    @Inject
    public HttpServer(IGameController gameController) {
        this.gameController = gameController;
        this.jsonController = new JsonController(gameController);
    }

    public void run() {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = this.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        try {
            System.in.read(); // let it run until user presses return
        } catch (IOException e) {
            System.out.println("HttpServer failed!");
            e.printStackTrace();
        }

        binding
                .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
                .thenAccept(unbound -> system.terminate()); // and shutdown when done
    }

    private Route createRoute() {
        return route(

                path("game", () ->
                        get(() -> this.jsonController.getGameSession())
                ),

                path("resetGame", () ->
                        get(() -> this.jsonController.resetGame())
                ),

                path("refreshGame", () ->
                        get(() -> this.jsonController.refreshGame())
                ),

                path("setPlayerName", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.setPlayerName(content)))
                ),

                path("processCommand", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.processCommand(content)))
                ),

                path("getPlayerWithoutUID", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.getPlayerWithoutUID(content)))
                ),
                path("loadGame", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.loadGame(content)))
                ),
                path("saveGame", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.saveGame(content)))
                )
        );
    }
}