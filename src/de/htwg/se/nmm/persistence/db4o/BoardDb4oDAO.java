package de.htwg.se.nmm.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.persistence.IBoardDAO;

import java.util.List;


public class BoardDb4oDAO implements IBoardDAO {

	private ObjectContainer db;
	private static BoardDb4oDAO instance;

	public static BoardDb4oDAO getInstance() {
		if (instance == null) {
			instance = new BoardDb4oDAO();
		}
		return instance;
	}

	public BoardDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"board.data");
	}



	@Override
	public void saveBoard(final IBoard board) {
		db.store(board);
	}


	@Override
	public void deleteBoard(final IBoard board) {
		db.delete(board);
	}

	@Override
	public List<IBoard> getAllBoards() {
		return db.query(IBoard.class);
	}

	@Override
	public void closeDb() {
		db.close();
	}

	@Override
	public IBoard getBoard(final String name) {
		return null;
	}
}
