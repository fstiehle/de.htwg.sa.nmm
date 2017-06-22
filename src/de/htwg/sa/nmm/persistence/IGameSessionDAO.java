package de.htwg.sa.nmm.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import de.htwg.sa.nmm.model.IGameSession;

public interface IGameSessionDAO {
	
	void saveSession(IGameSession session);

	boolean containsSession(IGameSession session);

	IGameSession getSession(UUID id);
	
	void deleteSession(IGameSession board);
	
	List<IGameSession> getAllSessions();

	void closeDb();

	ArrayList<HashMap<String, String>> getData(UUID id1, UUID id2);
}