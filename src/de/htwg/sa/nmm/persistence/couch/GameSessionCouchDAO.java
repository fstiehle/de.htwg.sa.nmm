package de.htwg.sa.nmm.persistence.couch;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.hibernate.cfg.NotYetImplementedException;

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
        throw new NotYetImplementedException();
    }

    @Override
    public void closeDb() { }

    /**
     * CouchDB Map Function:
     *
     * <pre>
     * {@code
     *  function(doc) {
     *      emit([doc.playerBlack.id, doc.playerWhite.id], doc);
     *  }
     * </pre>
     *
     * @param blackID
     * @param whiteID
     * @return results ArrayList<HashMap<String, String>>
     */
    @Override
    public ArrayList<HashMap<String, String>> getData(UUID blackID, UUID whiteID) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ViewQuery query = new ViewQuery()
                .designDocId("_design/nmm")
                .viewName("game_session_by_UIDs")
                .key(new String[]{blackID.toString(), whiteID.toString()});
        List<PersistentGameSession> persGameSession = db.queryView(query, PersistentGameSession.class);
        persGameSession.forEach((session) -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", session.getSessionName());
            map.put("id", session.getSessionID().toString());
            list.add(map);
        });

        return list;
    }
}
