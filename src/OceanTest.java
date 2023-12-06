import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class OceanTest {
    private Ocean o;

    @BeforeEach
    public void setUpBeforeClass() {
        o = new Ocean();
    }

    @Test
    public void testPlaceAllShipsRandomly() {
        o.placeAllShipsRandomly();
        int battleshipCount = 0;
        int cruiserCount = 0;
        int destroyerCount = 0;
        int submarineCount = 0;

        Ship[][] shipsArray = o.getShipArray();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship currentShip = shipsArray[i][j];
                if (currentShip instanceof Battleship) {
                    battleshipCount++;
                } else if (currentShip instanceof Cruiser) {
                    cruiserCount++;
                } else if (currentShip instanceof Destroyer) {
                    destroyerCount++;
                } else if (currentShip instanceof Submarine) {
                    submarineCount++;
                }
            }
        }

        assertEquals(4, battleshipCount); //1*4
        assertEquals(6, cruiserCount);    //2*3
        assertEquals(6, destroyerCount);  //3*2
        assertEquals(4, submarineCount);  //4*1
    }

    @Test
    public void testShootAt() {
        Cruiser c = new Cruiser();
        c.placeShipAt(3,3,true,o);

        //hit the target
        assertTrue(o.shootAt(3,3));
        assertEquals(1, o.getHitCount());
        assertEquals(1, o.getShotsFired());

        //repeat hitting
        assertTrue(o.shootAt(3,3));
        assertEquals(2, o.getHitCount());
        assertEquals(2, o.getShotsFired());

        //repeat hitting
        assertTrue(o.shootAt(3,3));
        assertEquals(3, o.getHitCount());
        assertEquals(3, o.getShotsFired());

        //hit the middle part
        assertTrue(o.shootAt(3,4));
        assertEquals(4, o.getHitCount());
        assertEquals(4, o.getShotsFired());

        //hit the end
        assertTrue(o.shootAt(3,5));
        assertEquals(5, o.getHitCount());
        assertEquals(5, o.getShotsFired());

        //ship has been sunk
        assertEquals(1, o.getShipsSunk());
        assertFalse(o.shootAt(3,3));
        assertEquals(5, o.getHitCount());
        assertEquals(6, o.getShotsFired());

        //miss shot
        assertFalse(o.shootAt(3,2));
        assertFalse(o.shootAt(3,6));
        assertFalse(o.shootAt(5,5));
        assertFalse(o.shootAt(5,5)); // repeated hit
        assertEquals(10, o.getShotsFired());

        //check the vertical direction
        Destroyer d = new Destroyer();
        d.placeShipAt(7,7,false,o);
        assertTrue(o.shootAt(7,7));
        assertEquals(6, o.getHitCount());
        assertEquals(11, o.getShotsFired());
        assertTrue(o.shootAt(7,7));
        assertEquals(7, o.getHitCount());
        assertEquals(12, o.getShotsFired());
        assertTrue(o.shootAt(8,7));
        assertEquals(8, o.getHitCount());
        assertEquals(13, o.getShotsFired());
        assertFalse(o.shootAt(8,7));
        assertEquals(8, o.getHitCount());
        assertEquals(14, o.getShotsFired());

        //final result
        Ocean newOcean = new Ocean();
        newOcean.placeAllShipsRandomly();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                newOcean.shootAt(row, col);
            }
        }
        assertEquals(20, newOcean.getHitCount());
        assertEquals(100, newOcean.getShotsFired());

        for (int i = 0; i < 10; i+=1) {
            for (int j = 0; j < 10; j+=1) {
                newOcean.shootAt(i, j);
            }
        }
        assertEquals(20, newOcean.getHitCount());
        assertTrue(newOcean.getShotsFired() > 100);
    }

    @Test
    public void testOccupy() {
        assertFalse(o.isOccupied(1,1));
        o.getShipArray()[1][1] = new Submarine();
        assertTrue(o.isOccupied(1,1));
    }

    @Test
    public void testGameOver() {
        assertFalse(o.isGameOver());
        o.shipsSunk = 5;
        assertFalse(o.isGameOver());
        o.shipsSunk = 10;
        assertTrue(o.isGameOver());
    }

    @Test
    public void testPrint() {
        PrintStream originalOut = System.out;
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Submarine s = new Submarine();
        Cruiser c = new Cruiser();
        s.placeShipAt(0,0,false,o);
        c.placeShipAt(0,6,true,o);
        o.shootAt(0,0);
        o.shootAt(0,4);
        o.shootAt(0,6);
        o.shootAt(0,8);
        o.print();
        String printedContents = outputStream.toString();
        int start = printedContents.indexOf("0 x");
        String row1 = printedContents.substring(start,start+32);
        System.out.println(printedContents);
        assertEquals("0 x  .  .  .  _  .  S  .  S  .  ", row1);  //check row 1 meet
        System.out.close();
        System.setOut(originalOut);
    }
}
