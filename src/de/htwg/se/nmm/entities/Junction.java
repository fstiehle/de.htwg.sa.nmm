package de.htwg.se.nmm.entities;


/**
 * a Junction knows its neighbours
 */
public class Junction {

    private Junction up;
    private Junction right;

    private Junction down;
    private Junction left;

    private Puck puck;

    //---------------------------
    // SETTER
    public void setNeighbours(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.puck = null;
    }

    public void setUp(Junction up) {
        this.up = up;
    }

    public void setRight(Junction right) {
        this.right = right;
    }

    public void setDown(Junction down) {
        this.down = down;
    }

    public void setLeft(Junction left) {
        this.left = left;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    //---------------------------
    // GETTER

    public Junction getUp() {
        return up;
    }

    public Junction getRight() {
        return right;
    }

    public Junction getDown() {
        return down;
    }

    public Junction getLeft() {
        return left;
    }

    public Puck getPuck() {
        return puck;
    }

    public boolean hasPuck() {
        if(this.puck == null) {
            return false;
        }
        return true;
    }

    //---------------------------
    // IMPLEMENTATIONS

    @Override
    public int hashCode() {
        int result = getUp() != null ? getUp().hashCode() : 0;
        result = 31 * result + (getRight() != null ? getRight().hashCode() : 0);
        result = 31 * result + (getDown() != null ? getDown().hashCode() : 0);
        result = 31 * result + (getLeft() != null ? getLeft().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (this.puck == null) {
           return "x-";
        } else {
            return "o-";
        }
    }
}
