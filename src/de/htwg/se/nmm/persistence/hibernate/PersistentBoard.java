package de.htwg.se.nmm.persistence.hibernate;

import com.sun.istack.internal.Nullable;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IJunction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "board")
public class PersistentBoard implements Serializable {

    public PersistentBoard(Map<String, IJunction> boardMap) {
        createPersistentBoard(boardMap);
    }

    public PersistentBoard() {
    }

    private void createPersistentBoard(Map<String, IJunction> boardMap) {
        Map<String, PersistentJunction> persBoardMap = new HashMap<>();
        for (IJunction junction : boardMap.values()) {
            PersistentJunction persJunction = new PersistentJunction(junction);
            persBoardMap.put(junction.getName(), persJunction);
        }
        this.setBoardMap(persBoardMap);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "board",
            joinColumns = @JoinColumn(name = "junction_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id"))
    @MapKey(name = "since")
    private Map<String, PersistentJunction> boardMap;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, PersistentJunction> getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(Map<String, PersistentJunction> boardMap) {
        this.boardMap = boardMap;
    }
}
