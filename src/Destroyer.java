public class Destroyer extends Ship{

    public Destroyer(){
        super();
        length = 2;
        hit = new boolean[length];
    }

    @Override
    public String getShipType() {
        return "destroyer";
    }
}
