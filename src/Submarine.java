public class Submarine extends Ship {

    public Submarine () {
        super();
        length = 1;
        hit = new boolean[length];
    }

    @Override
    public String toString() {
        if (isSunk()){
            return "S";
        }
        return ".";
    }

    @Override
    public String getShipType() {
        return "submarine";
    }
}
