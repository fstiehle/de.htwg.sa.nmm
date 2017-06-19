package de.htwg.sa.nmm.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;

import java.util.List;
import java.util.UUID;

// TODO: Save a Session not a board


public class GameSessionDb4oDAO implements IGameSessionDAO {

	private ObjectContainer db;
	private static GameSessionDb4oDAO instance;

	public static GameSessionDb4oDAO getInstance() {
		if (instance == null) {
			instance = new GameSessionDb4oDAO();
		}
		return instance;
	}

	public GameSessionDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gameSession.data");
	}

	@Override
	public boolean containsSession(final IGameSession session) {
		final String sessionID = session.getSessionID().toString();
		List<IGameSession> sessions = db.query(new Predicate<IGameSession>() {

			private static final long serialVersionUID = 1L;

			public boolean match(IGameSession session) {
				return (session.getSessionID().toString().equals(sessionID));
			}
		});

		if (sessions.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void saveSession(final IGameSession session) {
		db.store(session);
	}


	@Override
	public void deleteSession(final IGameSession session) {
		db.delete(session);
	}

	@Override
	public List<IGameSession> getAllSessions() {
		return db.query(IGameSession.class);
	}

	@Override
	public void closeDb() {
		db.close();
	}

	@Override
	public IGameSession getSession(UUID id) {
		List<IGameSession> sessions = db.query(new Predicate<IGameSession>() {
			public boolean match(IGameSession session) {
				return (session.getSessionID().toString().equals(id.toString()));
			}
		});

		if (sessions.size() > 0) {
			return sessions.get(0);
		}
		return null;
	}
}
