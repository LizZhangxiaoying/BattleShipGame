public class Cruiser extends Ship{

    public Cruiser() {
    super();
    length = 3;
    hit = new boolean[length];
    }

    @Override
    public String getShipType() {
        return "cruiser";
    }
}
