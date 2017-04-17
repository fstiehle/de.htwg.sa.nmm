package de.htwg.se.nmm.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.persistence.IGameSessionDAO;

import java.util.List;

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
	public void saveSession(final IGameSession session) {
		db.store(session);
	}


	@Override
	public void deleteSession(final IGameSession board) {
		db.delete(board);
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
	public IGameSession getSession(final String name) {
		return null;
	}
}
