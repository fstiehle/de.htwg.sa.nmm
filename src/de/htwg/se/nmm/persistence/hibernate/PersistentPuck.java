package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IPlayer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "puck")
public class PersistentPuck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToOne
    private PersistentPlayer player;

}
