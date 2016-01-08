package de.htwg.se.nmm.model;

import de.htwg.se.nmm.model.impl.Junction;

import java.util.Map;

public interface IBoard {
    Map<String, Junction> getBoardMap();
}
