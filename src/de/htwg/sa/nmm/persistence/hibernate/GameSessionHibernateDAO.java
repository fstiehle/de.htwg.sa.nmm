package de.htwg.sa.nmm.persistence.hibernate;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * Created by funkemarkus on 18.04.17.
 */
public class GameSessionHibernateDAO implements IGameSessionDAO {

    @Override
    public void saveSession(IGameSession gameSession) {
        Transaction tx = null;
        Session session;

        IPersistentGameSession persGameSession = new PersistentGameSession(gameSession);

        try {
            session = HibernateUtil.getSession().getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            session.saveOrUpdate(persGameSession);

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
    public IGameSession getSession(UUID id) {
        Session s = HibernateUtil.getSession().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        PersistentGameSession persistentGameSession = null;
        try {

            tx = s.beginTransaction();

            // here get object
            persistentGameSession = s.get(PersistentGameSession.class, id);

            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace(System.err);
        } finally {
            s.close();
        }

        return new GameSession(persistentGameSession);
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
