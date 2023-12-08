
/**
 * This is the Battleship class.
 * Battleship is a certain type of Ship, and its default length is 4.
 * The Battleship class extends the abstract Ship class.
 *
 * @author Jason Su and Xiaoying Zhang
 */
public class Battleship extends Ship{

    /**
     * Constructor for the Battleship class.
     * Initializes the Battleship object by calling the constructor of its parent class (Ship).
     * Sets the length of the Battleship to 4, indicating the number of tiles it occupies.
     * Creates a boolean array 'hit' to track the status of each part of the Battleship, initializing it to match the length.
     */
    public Battleship(){
        super();
        length = 4;
        hit = new boolean[length];
    }

    /**
     * Overrides the getShipType method from the Ship class.
     * Returns a String indicating the type of ship,"battleship" in this case.
     *
     * @return The ship type as a String, which is "battleship" in this case.
     */
    @Override
    public String getShipType() {
        return "battleship";
    }
}
