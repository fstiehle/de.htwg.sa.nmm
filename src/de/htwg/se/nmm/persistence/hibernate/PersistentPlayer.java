package de.htwg.se.nmm.persistence.hibernate;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player")
public class PersistentPlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name = "game_lost")
    boolean gameLost;

    private String name;

    @Column(name = "num_pucks")
    private int numPucks;

    @Column(name = "num_pucks_taken_away")
    private int numPucksTakenAway;


}
