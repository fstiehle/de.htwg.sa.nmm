package de.htwg.se.nmm.persistence;

import java.util.List;

import de.htwg.se.nmm.model.IGameSession;

public interface IGameSessionDAO {
	
	void saveSession(IGameSession session);

	boolean containsSession(IGameSession session);

	IGameSession getSession(String id);
	
	void deleteSession(IGameSession board);
	
	List<IGameSession> getAllSessions();

	void closeDb();
}