
/**
 * This is the Submarine class.
 * Submarine is a certain type of Ship, and its default length is 1.
 * The Submarine class extends the abstract Ship class.
 *
 * @author Jason Su and Xiaoying Zhang
 */

public class Submarine extends Ship {

    /**
     * Constructor for the Submarine class.
     * Initializes the Submarine object by calling the constructor of its parent class (Ship).
     * Sets the length of the Submarine to 1, indicating it only occupies 1 tile.
     * Creates a boolean array 'hit' to track the status of each part of the Battleship, initializing it to match the length.
     */
    public Submarine () {
        super();
        length = 1;
        hit = new boolean[length];
    }

    /**
     * Overrides the getShipType method from the Ship class.
     * Returns a String indicating the type of ship,"submarine" in this case.
     *
     * @return The ship type as a String, which is "submarine" in this case.
     */
    @Override
    public String getShipType() {
        return "submarine";
    }
}



