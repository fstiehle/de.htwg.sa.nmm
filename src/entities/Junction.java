package entities;

/**
 * a Junction knows its neighbours
 */
public class Junction {
    Junction up;
    Junction right;

    Junction down;
    Junction left;

    public class Junction(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }
}
