
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This is the test for ocean class.
 * No comments here.
 * @author Jason Su and Xiaoying Zhang
 */
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
  void testGameOver() {
        ocean.placeAllShipsRandomly();
        for(int i = 0; i < 10; i++){
            ocean.shootAt(0, i);
        }
        assertFalse(ocean.isGameOver());
        for(int i = 0; i< 10; i++){
            for(int j =  0; j < 10; j++) {
                ocean.shootAt(i, j);
            }
        }
        assertTrue(ocean.isGameOver());
    }
        @Test
void testOccupy() {
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
    @Test
   void shootAt_H() {
        Battleship b = new Battleship();
        b.placeShipAt(5,5,false,ocean);

        assertTrue(ocean.shootAt(5,5));
        assertEquals(1, ocean.getHitCount());
        assertEquals(1, ocean.getShotsFired());
        assertTrue(ocean.shootAt(5,5));
        assertEquals(2, ocean.getHitCount());
        assertEquals(2, ocean.getShotsFired());
        assertTrue(ocean.shootAt(5,5));
        assertEquals(3, ocean.getHitCount());
        assertEquals(3, ocean.getShotsFired());

        assertTrue(ocean.shootAt(6,5));
        assertEquals(4, ocean.getHitCount());
        assertEquals(4, ocean.getShotsFired());

        assertTrue(ocean.shootAt(7,5));
        assertEquals(5, ocean.getHitCount());
        assertEquals(5, ocean.getShotsFired());

        assertTrue(ocean.shootAt(8,5));
        assertEquals(6, ocean.getHitCount());
        assertEquals(6, ocean.getShotsFired());

        assertEquals(1, ocean.getShipsSunk());

        assertFalse(ocean.shootAt(5,5));

        assertEquals(6, ocean.getHitCount());
        assertEquals(7, ocean.getShotsFired());

        assertFalse(ocean.shootAt(5,4));
        assertFalse(ocean.shootAt(4,6));
        assertFalse(ocean.shootAt(4,5));
        assertFalse(ocean.shootAt(5,5));

        assertEquals(11, ocean.getShotsFired());
        assertEquals(6, ocean.getHitCount());
    }
    @Test
    void shootAt_V() {
        Destroyer d = new Destroyer();
        d.placeShipAt(5,5,false,ocean);
        assertTrue(ocean.shootAt(5,5));
        assertEquals(1, ocean.getHitCount());
        assertEquals(1, ocean.getShotsFired());
        assertTrue(ocean.shootAt(5,5));
        assertEquals(2, ocean.getHitCount());
        assertEquals(2, ocean.getShotsFired());
        assertTrue(ocean.shootAt(6,5));
        assertEquals(3, ocean.getHitCount());
        assertEquals(3, ocean.getShotsFired());
        assertFalse(ocean.shootAt(6,5));
        assertEquals(3, ocean.getHitCount());
        assertEquals(4, ocean.getShotsFired());

        assertEquals(1, ocean.getShipsSunk());

        assertFalse(ocean.shootAt(5,4));
        assertFalse(ocean.shootAt(4,6));
        assertFalse(ocean.shootAt(4,5));
        assertFalse(ocean.shootAt(5,5));
    }
    @Test
    void shootAt_All() {
        ocean.placeAllShipsRandomly();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ocean.shootAt(i, j);
            }
        }
        assertEquals(20, ocean.getHitCount());
        assertEquals(100, ocean.getShotsFired());

        for (int i = 0; i < 10;i++) {
            for (int j = 0; j < 10; j++) {
                ocean.shootAt(i, j);
            }
        }
        assertEquals(20, ocean.getHitCount());
        assertEquals(200, ocean.getShotsFired());
        assertEquals(10, ocean.getShipsSunk());
    }
    @Test
    public void testPrint() {
        PrintStream originalOut = System.out;
        OutputStream outStream = new ByteArrayOutputStream();
        PrintStream inStream = new PrintStream(outStream);
        System.setOut(inStream);
        Submarine s = new Submarine();
        Destroyer d = new Destroyer();
        s.placeShipAt(0,5,false,ocean);
        d.placeShipAt(0,8,true,ocean);
        ocean.shootAt(0,7);
        ocean.shootAt(0,5);
        ocean.shootAt(0,8);
        ocean.shootAt(0,0);
        ocean.print();
        String print = outStream.toString();
        int start = print.indexOf("0\t-");
        String firstRow = print.substring(start,start+21);
        System.out.println(print);
        System.out.println(firstRow);
        assertEquals("0\t-\t.\t.\t.\t.\tX\t.\t-\tS\t.", firstRow);  //check row 1 meet
        System.out.close();
        System.setOut(originalOut);
    }
}
