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
        assertFalse(ship1.okInitialPoint(row6,column6,true));
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
        Ship emptySea = new EmptySea(1,1);
        Ship battleShip = new Battleship();
        battleShip.setBowColumn(1);
        battleShip.setBowRow(1);
        battleShip.setHorizontal(true);

        assertTrue(emptySea.shootAt(1,1));
        assertFalse(emptySea.shootAt(1,2));
        assertFalse(emptySea.shootAt(2,1));

        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(1,2));
        assertTrue(battleShip.shootAt(1,3));
        assertTrue(battleShip.shootAt(1,4));

        assertFalse(battleShip.shootAt(2,1));
        assertFalse(battleShip.shootAt(2,2));
        assertFalse(battleShip.shootAt(0,0));
        assertFalse(battleShip.shootAt(9,9));

        battleShip.setHorizontal(false);

        assertTrue(battleShip.shootAt(1,1));
        assertTrue(battleShip.shootAt(2,1));
        assertTrue(battleShip.shootAt(3,1));
        assertTrue(battleShip.shootAt(4,1));

        assertFalse(battleShip.shootAt(2,2));
        assertFalse(battleShip.shootAt(4,3));
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
}