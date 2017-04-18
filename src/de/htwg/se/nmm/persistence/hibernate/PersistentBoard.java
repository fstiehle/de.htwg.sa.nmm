package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IJunction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "board")
public class PersistentBoard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToOne
    @JoinColumn(name = "game_session_id")
    public PersistentGameSession gameSession;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private Map<String, PersistentJunction> boardMap;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersistentGameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(PersistentGameSession gameSession) {
        this.gameSession = gameSession;
    }

    public Map<String, PersistentJunction> getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(Map<String, PersistentJunction> boardMap) {
        this.boardMap = boardMap;
    }
}
