package de.htwg.se.nmm.persistence.hibernate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "junction")
public class PersistentJunction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToOne
    private PersistentJunction up;
    @OneToOne
    private PersistentJunction right;

    @OneToOne
    private PersistentJunction down;
    @OneToOne
    private PersistentJunction left;

    @OneToOne
    private PersistentPuck puck;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersistentJunction getUp() {
        return up;
    }

    public void setUp(PersistentJunction up) {
        this.up = up;
    }

    public PersistentJunction getRight() {
        return right;
    }

    public void setRight(PersistentJunction right) {
        this.right = right;
    }

    public PersistentJunction getDown() {
        return down;
    }

    public void setDown(PersistentJunction down) {
        this.down = down;
    }

    public PersistentJunction getLeft() {
        return left;
    }

    public void setLeft(PersistentJunction left) {
        this.left = left;
    }

    public PersistentPuck getPuck() {
        return puck;
    }

    public void setPuck(PersistentPuck puck) {
        this.puck = puck;
    }
}
