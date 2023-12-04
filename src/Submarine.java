public class Submarine extends Ship {

    public Submarine () {
        super();
        length = 1;
        hit = new boolean[length];
    }

    @Override
    public String getShipType() {
        return "submarine";
    }
}



