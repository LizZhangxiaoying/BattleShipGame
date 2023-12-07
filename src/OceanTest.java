import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class OceanTest {
    private Ocean ocean;

    @BeforeEach
    public void oceanSetup() {
        ocean = new Ocean();
    }

    @Test
    void buildShips() {
        assertEquals("battleship" , ocean.buildShips()[0].getShipType());
        assertEquals("cruiser" , ocean.buildShips()[1].getShipType());
        assertEquals("cruiser" , ocean.buildShips()[2].getShipType());
        assertEquals("destroyer" , ocean.buildShips()[3].getShipType());
        assertEquals("destroyer" , ocean.buildShips()[5].getShipType());
        assertEquals("destroyer" , ocean.buildShips()[4].getShipType());
        assertEquals("submarine" , ocean.buildShips()[6].getShipType());
        assertEquals("submarine" , ocean.buildShips()[7].getShipType());
        assertEquals("submarine" , ocean.buildShips()[8].getShipType());
        assertEquals("submarine" , ocean.buildShips()[9].getShipType());
    }

    @Test
    void placeAllShipsRandomly() {
        ocean.placeAllShipsRandomly();
        Ship[][] ships = ocean.getShipArray();
        int battleship = 0;
        int cruiser = 0;
        int destroyer = 0;
        int submarine = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship currentShip = ships[i][j];
                if (currentShip instanceof Battleship) {
                    battleship++;
                } else if (currentShip instanceof Cruiser) {
                    cruiser++;
                } else if (currentShip instanceof Destroyer) {
                    destroyer++;
                } else if (currentShip instanceof Submarine) {
                    submarine++;
                }
            }
        }
        assertEquals(4, battleship); //1*4
        assertEquals(6, cruiser);    //2*3
        assertEquals(6, destroyer);  //3*2
        assertEquals(4, submarine);  //4*1
    }

        @Test
    public void testGameOver() {
        assertFalse(ocean.isGameOver());
        ocean.shipsSunk= 9;
        assertFalse(ocean.isGameOver());
        ocean.shipsSunk = 10;
        assertTrue(ocean.isGameOver());
    }

        @Test
    public void testOccupy() {
            Ship battleShip = new Battleship();
            Ship cruiser = new Cruiser();
            Ship destroyer = new Destroyer();

        battleShip.placeShipAt(0,0,true,ocean);
        cruiser.placeShipAt(5,5,false,ocean);
        destroyer.placeShipAt(7, 9,false,ocean);

        assertFalse(ocean.isOccupied(1,1));
        assertTrue(ocean.isOccupied(0,0));
        assertTrue(ocean.isOccupied(0,3));

        assertFalse(ocean.isOccupied(5,4));
        assertTrue(ocean.isOccupied(5,5));
        assertTrue(ocean.isOccupied(7,5));

        assertFalse(ocean.isOccupied(6,9));
        assertTrue(ocean.isOccupied(7,9));
        assertTrue(ocean.isOccupied(8,9));

    }




//
//    @Test
//    public void testShootAt() {
//        Cruiser c = new Cruiser();
//        c.placeShipAt(3,3,true,o);
//
//        //hit the target
//        assertTrue(o.shootAt(3,3));
//        assertEquals(1, o.getHitCount());
//        assertEquals(1, o.getShotsFired());
//
//        //repeat hitting
//        assertTrue(o.shootAt(3,3));
//        assertEquals(2, o.getHitCount());
//        assertEquals(2, o.getShotsFired());
//
//        //repeat hitting
//        assertTrue(o.shootAt(3,3));
//        assertEquals(3, o.getHitCount());
//        assertEquals(3, o.getShotsFired());
//
//        //hit the middle part
//        assertTrue(o.shootAt(3,4));
//        assertEquals(4, o.getHitCount());
//        assertEquals(4, o.getShotsFired());
//
//        //hit the end
//        assertTrue(o.shootAt(3,5));
//        assertEquals(5, o.getHitCount());
//        assertEquals(5, o.getShotsFired());
//
//        //ship has been sunk
//        assertEquals(1, o.getShipsSunk());
//        assertFalse(o.shootAt(3,3));
//        assertEquals(5, o.getHitCount());
//        assertEquals(6, o.getShotsFired());
//
//        //miss shot
//        assertFalse(o.shootAt(3,2));
//        assertFalse(o.shootAt(3,6));
//        assertFalse(o.shootAt(5,5));
//        assertFalse(o.shootAt(5,5)); // repeated hit
//        assertEquals(10, o.getShotsFired());
//
//        //check the vertical direction
//        Destroyer d = new Destroyer();
//        d.placeShipAt(7,7,false,o);
//        assertTrue(o.shootAt(7,7));
//        assertEquals(6, o.getHitCount());
//        assertEquals(11, o.getShotsFired());
//        assertTrue(o.shootAt(7,7));
//        assertEquals(7, o.getHitCount());
//        assertEquals(12, o.getShotsFired());
//        assertTrue(o.shootAt(8,7));
//        assertEquals(8, o.getHitCount());
//        assertEquals(13, o.getShotsFired());
//        assertFalse(o.shootAt(8,7));
//        assertEquals(8, o.getHitCount());
//        assertEquals(14, o.getShotsFired());
//
//        //final result
//        Ocean newOcean = new Ocean();
//        newOcean.placeAllShipsRandomly();
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                newOcean.shootAt(row, col);
//            }
//        }
//        assertEquals(20, newOcean.getHitCount());
//        assertEquals(100, newOcean.getShotsFired());
//
//        for (int i = 0; i < 10; i+=1) {
//            for (int j = 0; j < 10; j+=1) {
//                newOcean.shootAt(i, j);
//            }
//        }
//        assertEquals(20, newOcean.getHitCount());
//        assertTrue(newOcean.getShotsFired() > 100);
//    }
//

//

//
//    @Test
//    public void testPrint() {
//        PrintStream originalOut = System.out;
//        OutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//        Submarine s = new Submarine();
//        Cruiser c = new Cruiser();
//        s.placeShipAt(0,0,false,o);
//        c.placeShipAt(0,6,true,o);
//        o.shootAt(0,0);
//        o.shootAt(0,4);
//        o.shootAt(0,6);
//        o.shootAt(0,8);
//        o.print();
//        String printedContents = outputStream.toString();
//        int start = printedContents.indexOf("0 x");
//        String row1 = printedContents.substring(start,start+32);
//        System.out.println(printedContents);
//        assertEquals("0 x  .  .  .  _  .  S  .  S  .  ", row1);  //check row 1 meet
//        System.out.close();
//        System.setOut(originalOut);
//    }


}
