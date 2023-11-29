
public class Battleship extends Ship{

    public Battleship(){
        super();
        length = 4;
        hit = new boolean[length];
    }


    @Override
    public String getShipType() {
        return "battleship";
    }
}
