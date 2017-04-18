package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.persistence.IGameSessionDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by funkemarkus on 18.04.17.
 */
public class GameSessionHibernateDAO implements IGameSessionDAO {
    @Override
    public void saveSession(IGameSession gameSession) {
        Transaction tx = null;
        Session session = null;

        PersistentGameSession foo = new PersistentGameSession();
        foo.setId("hallo");
        foo.setBoard(new PersistentBoard());

        try {
            session = HibernateUtil.getSession().getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(foo);

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(ex.getMessage());

        }
    }

    @Override
    public boolean containsSession(IGameSession session) {
        return false;
    }

    @Override
    public IGameSession getSession(String id) {
        return null;
    }

    @Override
    public void deleteSession(IGameSession board) {

    }

    @Override
    public List<IGameSession> getAllSessions() {
        return null;
    }

    @Override
    public void closeDb() {

    }
}
