import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    @Test
    void buildShips() {

        Ocean ocean = new Ocean();
      assertEquals("battleship" , ocean.buildShips()[0].getShipType());
      assertEquals("cruiser" , ocean.buildShips()[2].getShipType());
      assertEquals("destroyer" , ocean.buildShips()[5].getShipType());
      assertEquals("submarine" , ocean.buildShips()[9].getShipType());

    }
}