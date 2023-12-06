import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest{




    @org.junit.jupiter.api.Test
    void okInitialPoint() {
        int row1 = 8;
        int row2 = 7;
        int row3 = 8;
        int row4 = 7;
        int row5 = 5;
        int row6 = 0;

        int column1 = 9;
        int column2 = 5;
        int column3 = 0;
        int column4 = 9;
        int column5 = 0;
        int column6 = 6;

        Ship ship1 = new Battleship();

        assertFalse(ship1.okInitialPoint(row1,column1,true));
        assertTrue(ship1.okInitialPoint(row6,column6,true));
        assertTrue(ship1.okInitialPoint(row2,column2,true));
        assertFalse(ship1.okInitialPoint(row3,column3,false));
        assertFalse(ship1.okInitialPoint(row4,column4,false));
        assertTrue(ship1.okInitialPoint(row5,column5,false));

        }


    @Test
    void pointOk() {

        Ship ship2 = new Cruiser();
        int row1 = 0;
        int row2 = 9;
        int row3 = -1;
        int row4 = 8;
        int row5 = 10;
        int row6 = 1;

        int column1 = 0;
        int column2 = 9;
        int column3 = 8;
        int column4 = -1;
        int column5 = 1;
        int column6 = 10;

        assertTrue(ship2.pointOk(row1, column1));
        assertTrue(ship2.pointOk(row2, column2));
        assertFalse(ship2.pointOk(row3, column3));
        assertFalse(ship2.pointOk(row4, column4));
        assertFalse(ship2.pointOk(row5, column5));
        assertFalse(ship2.pointOk(row6, column6));
    }

    @Test
    void getShipType() {
        Ship battleship = new Battleship();
        Ship destroyer = new Destroyer();
        Ship submarine = new Submarine();
        Ship Cruiser = new Cruiser();
        Ship emptySea = new EmptySea(1,1);

        assertEquals(4, battleship.getLength());
        assertEquals(2, destroyer.getLength());
        assertEquals(1, submarine.getLength());
        assertEquals(3, Cruiser.getLength());
        assertEquals(1, emptySea.getLength());

        assertEquals("battleship", battleship.getShipType());
        assertEquals("destroyer", destroyer.getShipType());
        assertEquals("submarine", submarine.getShipType());
        assertEquals("cruiser", Cruiser.getShipType());

    }

    @Test
    void shootAt() {
        Ship battleShip = new Battleship();
        Ship cruiser = new Cruiser();
        cruiser.setHorizontal(true);
        cruiser.setBowRow(5);
        cruiser.setBowColumn(5);
        battleShip.setBowColumn(1);
        battleShip.setBowRow(1);
        battleShip.setHorizontal(true);

        assertTrue(cruiser.shootAt(5,5));
        assertTrue(cruiser.shootAt(5,5));
        assertTrue(cruiser.shootAt(5,5));
        assertTrue(cruiser.shootAt(5,5));
        assertFalse(cruiser.isSunk());
        assertTrue(cruiser.shootAt(5,6));
        assertTrue(cruiser.shootAt(5,7));
        assertTrue(cruiser.isSunk());
        assertFalse(cruiser.shootAt(5,5));


        assertFalse(cruiser.shootAt(5,8));
        assertFalse(cruiser.shootAt(5,9));

        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,1));

        assertFalse(battleShip.isSunk());
        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,2));
        assertTrue(battleShip.shootAt(1,3));
        assertTrue(battleShip.shootAt(1,4));
        assertTrue(battleShip.isSunk());

        assertFalse(battleShip.shootAt(2,1));
        assertFalse(battleShip.shootAt(2,2));
        assertFalse(battleShip.shootAt(0,0));
        assertFalse(battleShip.shootAt(9,9));


    }

    @Test
    void isSunk() {
        Ship battleShip = new Battleship();
        battleShip.setHorizontal(true);
        battleShip.setBowRow(0);
        battleShip.setBowRow(0);
        battleShip.shootAt(0,0);
        battleShip.shootAt(0,1);
        battleShip.shootAt(0,2);
        battleShip.shootAt(0,3);

        assertTrue(battleShip.isSunk());

        Ship cruiser = new Cruiser();
        cruiser.setHorizontal(false);
        cruiser.setBowRow(0);
        cruiser.setBowRow(0);
        cruiser.shootAt(1,0);
        cruiser.shootAt(1,0);
        cruiser.shootAt(2,0);

        assertFalse(cruiser.isSunk());

    }

    @Test
    void okToPlaceShipAt() {
        Ship battleShip = new Battleship();
        Ocean ocean = new Ocean();

        battleShip.placeShipAt(1,2,true,ocean);
        assertEquals("battleship", ocean.getShipArray()[1][2].getShipType());
        assertEquals("battleship", ocean.getShipArray()[1][3].getShipType());
        assertEquals("battleship", ocean.getShipArray()[1][4].getShipType());
        assertEquals("battleship", ocean.getShipArray()[1][5].getShipType());
        assertEquals("emptySea", ocean.getShipArray()[1][6].getShipType());
        assertEquals("emptySea", ocean.getShipArray()[1][1].getShipType());
        assertEquals("emptySea", ocean.getShipArray()[2][1].getShipType());
    }

    @Test
    void testOkToPlaceShipAt() {
        Ship battleShip = new Battleship();
        Ship destroyer = new Destroyer();

        Ship cruiser = new Cruiser();
        Ocean ocean = new Ocean();
        battleShip.placeShipAt(1,2,true,ocean);
        battleShip.placeShipAt(5,5,true,ocean);


        assertFalse(destroyer.okToPlaceShipAt(5,3,true,ocean));


       assertFalse(cruiser.okToPlaceShipAt(1,2,true,ocean));
       assertFalse(cruiser.okToPlaceShipAt(2,2,true,ocean));
       assertFalse(cruiser.okToPlaceShipAt(1,6,true,ocean));
       assertFalse(cruiser.okToPlaceShipAt(1,8,true,ocean));
       assertFalse(cruiser.okToPlaceShipAt(2,3,true,ocean));

       assertFalse(cruiser.okToPlaceShipAt(1,3,true,ocean));

       assertTrue(cruiser.okToPlaceShipAt(1,7,true,ocean));
       assertTrue(cruiser.okToPlaceShipAt(3,7,true,ocean));
       assertTrue(cruiser.okToPlaceShipAt(8,2,true,ocean));
       assertTrue(cruiser.okToPlaceShipAt(9,2,true,ocean));

       assertFalse(cruiser.okToPlaceShipAt(1,2,false,ocean));
       assertFalse(cruiser.okToPlaceShipAt(1,3,false,ocean));
       assertFalse(cruiser.okToPlaceShipAt(1,4,false,ocean));
       assertFalse(cruiser.okToPlaceShipAt(2,4,false,ocean));
       assertFalse(cruiser.okToPlaceShipAt(3,4,false,ocean));

    }
}