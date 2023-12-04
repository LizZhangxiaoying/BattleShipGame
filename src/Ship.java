public abstract class Ship {

    protected int bowColumn;

    protected int bowRow;

    protected boolean[] hit;

    protected boolean horizontal;

    protected int length;

    public int getBowColumn() {
        return bowColumn;
    }
    // Returns:the column of the bow (front) of the ship

    public int getBowRow() {
        return bowRow;
    }

    public int getLength() {
        return length;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }
    //Parameters:bowColumn - the bowColumn to set

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public boolean isHorizontal() {
        return false;
    }
    //Returns:true if this boat is horizontal (facing left), false otherwise.

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    // Parameters: horizontal - the horizontal to set

    public abstract String getShipType();

    //Returns the String representing the type of this ship.
//    //Determines whether or not this is represents a valid placement configuration for this Ship in this Ocean. Ship objects in an Ocean must not overlap other Ship objects or touch them vertically, horizontally, or diagonally. Additionally, the placement cannot be such that the Ship would extend beyond the extents of the 2D array in which it is placed. Calling this method should not actually change either the Ship or the provided Ocean.
//    Parameters:
//    row - the candidate row to place the ship
//    column - the candidate column to place the ship
//    horizontal - whether or not to have the ship facing to the left
//    ocean - the Ocean in which this ship might be placed
//    Returns:
//            true if it is valid to place this ship of this length in this location with this orientation, and false otherwise.
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        if (!okInitialPoint(row, column, horizontal)) {
            return false;
        }
        if (horizontal) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < length+1; j++) {
                    if (pointOk(row + i,column + j)) {
                        if (ocean.isOccupied(row + i, column + j)) {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < length+1; j++) {
                    if (pointOk(row + j, column + i)) {
                        if (ocean.isOccupied(row + j, column + i)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean okInitialPoint(int row, int column, boolean horizontal) {
        if (horizontal) {
            if (10 - column >= length) {
                return true;
            }
        } else {
            if (10 - row >=  length) {
                return true;
            }
        }
        return false;
    }

    public boolean pointOk(int row, int column) {
        if (row < 0 || column < 0 || row > 9 || column > 9) {
            return false;
        }
        return true;
    }

    //    Puts the Ship in the Ocean. This will give values to the bowRow, bowColumn, and horizontal instance variables in the Ship. This should also place a reference to this Ship in each of the one or more locations (up to four) in the corresponding Ocean array this Ship is being placed in. Each of the references placed in the Ocean will be identical since it is not possible to refer to a "part" of a ship, only the whole ship.
//    Parameters:
//    row - the row to place the ship
//    column - the column to place the ship
//    horizontal - whether or not to have the ship facing to the left
//    ocean - the Ocean in which this ship will be placed

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
            setBowRow(row);
            setBowColumn(column);
            setHorizontal(horizontal);

            if (horizontal){
                for (int i = 0; i < length; i++) {
                    ocean.getShipArray()[row][column + i] = this;
                }
            }else {
                for (int i = 0; i < length; i++) {
                    ocean.getShipArray()[row + i][column] = this;
                }
            }
    }

    public boolean shootAt(int row, int column) {
        if (horizontal) {
            if (bowRow == row && column >= bowColumn && column <= bowColumn + length - 1) {
                hit[column - bowColumn] = true;
                return true;
            } else {
                return false;
            }
        } else {
            if (bowColumn == column && row >= bowRow && row <= bowRow + length - 1) {
                hit[row - bowRow] = true;
                return true;
            } else {
                return false;
            }
        }
    }
//    If a part of this ship occupies this coordinate, and if the ship hasn't been sunk, mark the part of the ship at that coordinate as "hit".
//    Parameters:
//    row - the row of the shot
//    column - the column of the shot
//    Returns:
//            true if this ship hasn't been sunk and a part of this ship occupies the given row and column and false otherwise.

    public boolean isSunk() {
        for(int i = 0; i < length; i ++){
            if (!hit[i]) {
                return false;
            }
        }
        return true;
    }
//    Returns true if this ship has been sunk and false otherwise.
//            Returns:
//            true if every part of the ship has been hit, and false otherwise.

    public String toString() {
        if (isSunk()){
            return "x";
        }
        return "S";
    }
//    Returns a single character String to use in the Ocean's print method. This method should return "x" if the ship has been sunk, and "S" if it has not yet been sunk. This method can only be used to print out locations in the ocean that have been shot at; it should not be used to print locations that have not been the target of a shot yet.
//    Overrides:
//    toString in class Object
//      Returns:
//   "x" if this ship has been sunk, and "S" otherwise.


    //Parameters:bowRow - the bowRow to set

    // An abstract class
    // facing up or to the left
    // should not be a constructor
    // Ships will always be facing up or to the left. That is, relative to the “bow”, or front,
    //  of the ship, all other tiles will be in higher numbered rows or columns. ?

    //protected length unique to all the ships put all the ship length here?

    //public method location
    //place the ship with the initial given location


    //public method being hit
    //decrease length
    //change status
    //how many hits have left


    //public method status
    // return status
    // Use 'S' to indicate a location that you have fired upon and hit a (real)
    //  '-' to indicate a location that you have fired upon and found nothing
    //'x' to indicate a location containing a sunken ship</li>
    //  '.' (a period) to indicate a location that you have never fired


    //public method sunk
    //boolean true when length = 0
    //print out what has been sunk?


    //public method getShipType()
    //if sunk is true then return ship type

}
