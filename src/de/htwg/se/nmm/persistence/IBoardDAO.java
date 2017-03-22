package de.htwg.se.nmm.persistence;

import java.util.List;

import de.htwg.se.nmm.model.IBoard;

public interface IBoardDAO {
	
	void saveBoard(IBoard board);
	
	IBoard getBoard(String name);
	
	void deleteBoard(IBoard board);
	
	List<IBoard> getAllBoards();
}