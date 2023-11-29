public class EmptySea extends Ship{

public EmptySea(int row, int column) {
    length = 1;
    hit = new boolean[length];
    bowColumn = column;
    bowRow = row;
    horizontal = false;
}

    @Override
    public String getShipType() {
    return "emptySea";
    }
}
