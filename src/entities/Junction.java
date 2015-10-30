package entities;

/**
 * a Junction knows its neighbours
 */
public class Junction {
    coordinates = {x,y};

    Junction up;
    Junction right;

    Junction down;
    Junction left;

    public class Junction() {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public class setPosition(x,y) {
        this.x = x;
        this.y = y;
    }

    public class addUp(Junction r) {
        this.up = up;
    }

    public class addRight(Junction r) {
        this.right = right;
    }

    public class addDown(Junction r) {
        this.down = down;
    }

    public class addLeft(Junction r) {
        this.left = left;
    }
}
