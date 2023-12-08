
/**
 * This is the Destroyer class.
 * A Destroyer is a type of ship, and its default length is 2.
 * The Destroyer class extends the abstract Ship class.
 *
 * @author Jason Su and Xiaoying Zhang
 */


public class Destroyer extends Ship{

    /**
     * Constructor for the Destroyer class.
     * Initializes the Destroyer object by calling the constructor of its parent class (Ship).
     * Sets the length of the Destroyer to 2, indicating the number of tiles it occupies.
     * Creates a boolean array 'hit' to track the status of each part of the Destroyer, initializing it to match the length.
     */
    public Destroyer(){
        super();
        length = 2;
        hit = new boolean[length];
    }
    /**
     * Overrides the getShipType method from the Ship class.
     * Returns a String indicating the type of ship,"destroyer" in this case.
     *
     * @return The ship type as a String, which is "destroyer" in this case.
     */
    @Override
    public String getShipType() {
        return "destroyer";
    }
}
