package entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Builds and Returns the main board
 * The Board is comprised of Junctions
 * 24 junctions
 */
public class Board implements BoardSetup {

    Map<String, Junction> boardMap = new HashMap<>();

    public Map<String, Junction> Board() {

        //Connections
        // directions clockwise: up, right, down, left
        //a1---------a4--------a7
        a1.setNeighbours(null, a4, d1, null);
        a4.setNeighbours(null, a7, b4, a1);
        a7.setNeighbours(null, null, d7, a4);

        //----b2-----b4-----b6----

        return boardMap;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardMap=" + boardMap +
                '}';
    }
}
