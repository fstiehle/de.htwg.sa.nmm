package de.htwg.se.nmm.model;

import java.util.HashMap;
import java.util.Map;

public interface IBoard {
    Map<String, IJunction> getBoardMap();

    HashMap<String, Object> getData();
}
