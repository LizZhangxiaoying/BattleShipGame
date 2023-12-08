
/**
 * This is the EmptySea class.
 * EmptySea is also considered as a type of ship, and its default length is 1.
 * EmptyOcean class extends the abstract Ship class.
 *
 * @author Jason Su and Xiaoying Zhang
 */


public class EmptySea extends Ship{

    /**
     * Constructor for the EmptySea class.
     * Initializes the EmptySea object with a length of 1, representing a single empty grid cell.
     * Creates a boolean array 'hit' to track its status.
     * Sets the bowColumn and bowRow to the specified column and row, indicating the position of the empty cell.
     * Sets the 'horizontal' attribute to false since EmptySea does not have a specific orientation.
     *
     * @param row The row coordinate of the EmptySea grid cell.
     * @param column The column coordinate of the EmptySea grid cell.
     */
public EmptySea(int row, int column) {
    length = 1;
    hit = new boolean[length];
    bowColumn = column;
    bowRow = row;
    horizontal = false;
}

    /**
     * Overrides the getShipType method from the Ship class.
     * Returns a String indicating the type of ship,"emptySea" in this case.
     *
     * @return The ship type as a String, which is "emptySea" in this case.
     */
    @Override
    public String getShipType() {
    return "emptySea";
    }

    /**
     * Overrides the toString method from the Object class.
     * Returns a String representation of the EmptySea object, which is "-" indicating an empty grid cell.
     *
     * @return A String representation of the EmptySea object, which is "-".
     */
    @Override
    public String toString() {
        return "-";
    }
}




