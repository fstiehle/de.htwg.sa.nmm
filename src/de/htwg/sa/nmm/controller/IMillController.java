package de.htwg.sa.nmm.controller;

import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IPlayer;

public interface IMillController {

    boolean checkForMill(IBoard board, String junctionName, IPlayer p);

}
