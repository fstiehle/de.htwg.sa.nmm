package de.htwg.se.nmm;

import com.google.inject.AbstractModule;
import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Puck;
import de.htwg.se.nmm.persistence.IGameSessionDAO;
import de.htwg.se.nmm.persistence.db4o.GameSessionDb4oDAO;
import de.htwg.se.nmm.persistence.hibernate.GameSessionHibernateDAO;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(IGameController.class).to(GameController.class);
        bind(IPuck.class).to(Puck.class);
        bind(IBoard.class).to(Board.class);
        bind(IGameSessionDAO.class).to(GameSessionHibernateDAO.class);
    }
}
