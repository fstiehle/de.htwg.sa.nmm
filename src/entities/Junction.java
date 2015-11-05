package entities;

import java.util.Arrays;
import java.util.HashMap;

/**
 * a Junction knows its neighbours
 */
public class Junction {
    // x,y
    private int[] coordinates = new int[2];

    private Junction up;
    private Junction right;

    private Junction down;
    private Junction left;

    //---------------------------
    // SETTER

    public void setPosition(int x,int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public void setNeighbours(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public void setUp(Junction r) {
        this.up = up;
    }

    public void setRight(Junction r) {
        this.right = right;
    }

    public void setDown(Junction r) {
        this.down = down;
    }

    public void setLeft(Junction r) {
        this.left = left;
    }

    public void setPositionX(int x) {
        this.coordinates[0] = x;
    }

    public void setPositionY(int y) {
        this.coordinates[1] = y;
    }

    //---------------------------
    // GETTER
    public int[] getCoordinates() {
        return coordinates;
    }

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

    //---------------------------
    // IMPLEMENTATIONS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Junction junction = (Junction) o;

        return Arrays.equals(coordinates, junction.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
