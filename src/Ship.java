public abstract class Ship {
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
