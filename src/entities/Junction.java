package entities;

import java.util.Arrays;

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

    private boolean occupied;

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

    public void setPositionX(int x) {
        this.coordinates[0] = x;
    }

    public void setPositionY(int y) {
        this.coordinates[1] = y;
    }

    public void setOccupation(boolean b) { this.occupied = b; }

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

    public Junction getLeft() { return left; }

    public boolean isOccupied() { return occupied; }

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
