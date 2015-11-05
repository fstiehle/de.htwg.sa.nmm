package entities;

/**
 * a Junction knows its neighbours
 */
public class Junction {
    // x,y
    int[] coordinates = new int[2];

    Junction up;
    Junction right;

    Junction down;
    Junction left;

    public void Junction(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public void setPosition(int x,int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public void addUp(Junction r) {
        this.up = up;
    }

    public void addRight(Junction r) {
        this.right = right;
    }

    public void addDown(Junction r) {
        this.down = down;
    }

    public void addLeft(Junction r) {
        this.left = left;
    }

    public int getPositionX() {
        return coordinates[0];
    }

    public int getPositionY() {
        return coordinates[1];
    }
}
