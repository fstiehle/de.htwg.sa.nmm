package de.htwg.sa.nmm;

import com.google.inject.AbstractModule;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.controller.IMillController;
import de.htwg.sa.nmm.controller.impl.GameController;
import de.htwg.sa.nmm.controller.impl.MillController;
import de.htwg.sa.nmm.controller.impl.MillHttpController;
import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.persistence.IPersistentJunction;
import de.htwg.sa.nmm.persistence.hibernate.GameSessionHibernateDAO;
import de.htwg.sa.nmm.persistence.hibernate.PersistentJunction;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.model.impl.Puck;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(IGameController.class).to(GameController.class);
        bind(IPuck.class).to(Puck.class);
        bind(IBoard.class).to(Board.class);
        bind(IGameSessionDAO.class).to(GameSessionHibernateDAO.class);
        bind(IPersistentJunction.class).to(PersistentJunction.class);
        bind(IMillController.class).to(MillHttpController.class);

    }
}
