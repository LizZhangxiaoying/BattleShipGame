
/**
 * This is the Cruiser class.
 * A Cruiser is a type of ship, and its default length is 3.
 * The Cruiser class extends the abstract Ship class.
 *
 * @author Jason Su and Xiaoying Zhang
 */

public class Cruiser extends Ship{
    /**
     * Constructor for the Cruiser class.
     * Initializes the Cruiser object by calling the constructor of its parent class (Ship).
     * Sets the length of the Cruiser to 3, indicating the number of tiles it occupies.
     * Creates a boolean array 'hit' to track the status of each part of the Cruiser, initializing it to match the length.
     */
    public Cruiser() {
    super();
    length = 3;
    hit = new boolean[length];
    }

    /**
     * Overrides the getShipType method from the Ship class.
     * Returns a String indicating the type of ship,"Cruiser" in this case.
     *
     * @return The ship type as a String, which is "Cruiser" in this case.
     */

    @Override
    public String getShipType() {
        return "cruiser";
    }
}
