package de.htwg.sa.nmm.persistence.couch;

import de.htwg.sa.nmm.Game;
import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by fabianstiehle on 26.05.17.
 */
public class GameSessionCouchDAO implements IGameSessionDAO {

    private CouchDbConnector db = null;

    public GameSessionCouchDAO() {
        db = getCouchInstance();
    }

    private CouchDbConnector getCouchInstance() {
        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder().url("http://0.0.0.0:5984").build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("nmm", true);
        return db;
    }

    @Override
    public void saveSession(IGameSession session) {
        db.update(new PersistentGameSession(session));
    }

    @Override
    public boolean containsSession(IGameSession session) {
        return false;
    }

    @Override
    public IGameSession getSession(UUID id) {
        IPersistentGameSession persGameSession = db.find(PersistentGameSession.class, id.toString());
        if (persGameSession == null) {
            return null;
        }
        return new GameSession(persGameSession);
    }

    @Override
    public void deleteSession(IGameSession board) {

    }

    @Override
    public List<IGameSession> getAllSessions() {
       return null;
    }

    @Override
    public void closeDb() { }

    @Override
    public ArrayList<HashMap<String, String>> getData(UUID id1, UUID id2) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ViewQuery q = new ViewQuery()
            .allDocs()
            .includeDocs(true)
            .key(id1)
            .key(id2);
        List<PersistentGameSession> persGameSession = db.queryView(q, PersistentGameSession.class);
        persGameSession.forEach((session) -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", session.getSessionName());
            map.put("id", session.getSessionID().toString());
            list.add(map);
        });
        return list;
    }
}
