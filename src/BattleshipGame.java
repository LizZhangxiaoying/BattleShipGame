import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class BattleshipGame {

    private ArrayList<Integer> alreadyPlaced;
    private ArrayList<String> hitNumber;
    private Scanner inputScanner;
    private Ocean ocean;
    private int row;
    private int column;
    private int numOfShip;


    public BattleshipGame() {
        row = 0;
        column = 0;
        numOfShip = 10;
        ocean = new Ocean();
        alreadyPlaced = new ArrayList<>();
        hitNumber = new ArrayList<>();
        inputScanner = new Scanner(System.in);
    }

    private void start () {
        ocean.placeAllShipsRandomly();
        while(!ocean.isGameOver()) {
            ocean.print();
            System.out.print("grid already entered");
            System.out.println(getAlreadyPlaced());
            enterGrid();
            System.out.println(ocean.shootAt(row,column));

        }
    }

    private void enterGrid() {
            while(true) {
                int inputRow = -1;
                int inputColumn = -1;

                System.out.printf("please enter THE ROW number and ");
                while (!validPoint(inputRow)) {
                    inputRow = userInput();
                }

                System.out.printf("please enter THE COLUMN number and ");
                while (!validPoint(inputColumn)) {
                    inputColumn = userInput();
                }

                if (validGrid(inputRow, inputColumn)) {
                    row = inputRow;
                    column = inputColumn;
                    alreadyPlaced.add (inputRow *10 + inputColumn);
                    return;
                }
            }
    }

    public int userInput() {
        Scanner scnr = new Scanner(System.in);
        int numberEntry = 0;
        while (!scnr.hasNextInt()) {
            System.out.printf("please enter an integer\n");
            scnr.next();
        }
        numberEntry = scnr.nextInt();
        return numberEntry;
    }

    public boolean validPoint(int number) {
        if (number > 9 || number < 0 ){
            System.out.printf("please enter an integer between 0 to 9\n");
            return false;
        }
        return true;
    }

    public boolean validGrid (int row, int column) {
        int gridNumber = row * 10 + column;
        if (alreadyPlaced.contains(gridNumber)){
            System.out.printf("the grid has already entered before, please re-enter\n");
            return false;
        }
        return true;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

    public ArrayList<Integer> getAlreadyPlaced() {
        return alreadyPlaced;
    }

    public static void main(String[] args) {

        BattleshipGame battleGame = new BattleshipGame();
//        battleGame.enterGrid();
//
//        System.out.println(battleGame.getColumn());
//        System.out.println(battleGame.getRow());
//        System.out.println(battleGame.getAlreadyPlaced());

            battleGame.start();

    }

}
