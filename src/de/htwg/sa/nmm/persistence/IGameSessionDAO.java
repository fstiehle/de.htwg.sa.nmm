package de.htwg.sa.nmm.persistence;

import java.util.List;

import de.htwg.sa.nmm.model.IGameSession;

public interface IGameSessionDAO {
	
	void saveSession(IGameSession session);

	boolean containsSession(IGameSession session);

	IGameSession getSession(String id);
	
	void deleteSession(IGameSession board);
	
	List<IGameSession> getAllSessions();

	void closeDb();
}