package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.persistence.IGameSessionDAO;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by funkemarkus on 18.04.17.
 */
public class GameSessionHibernateDAOTest {
    @Test
    public void saveSession() throws Exception {
        IGameSessionDAO dao = new GameSessionHibernateDAO();
        dao.saveSession(null);
    }

}