

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * This is BattleshipGame class.
 * It represents the main logic and user interaction for the Battleship game.
 * Manages the game state, including setup, user input, and displaying results.
 *
 * @author Jason Su and Xiaoying Zhang
 */
public class BattleshipGame {

    private ArrayList<Integer> alreadyPlaced;
    private ArrayList<String> hitNumber;
    private Scanner inputScanner;
    private Ocean ocean;
    private int row;
    private int column;
    private int numOfShip;

    /**
     * Constructor for the BattleshipGame class.
     * Initializes the game parameters, such as row, column, and the total number of ships.
     * Creates an instance of the Ocean class to represent the game board.
     * Sets up data structures to keep track of placed ships, recorded hits, and utilizes a Scanner for user input.
     */

    public BattleshipGame() {
        row = 0;
        column = 0;
        numOfShip = 10;
        ocean = new Ocean();
        alreadyPlaced = new ArrayList<>();
        hitNumber = new ArrayList<>();
        inputScanner = new Scanner(System.in);
    }
    /**
     * This method displays the whole process of game playing.
     * Initiates the game by randomly placing all ships on the ocean grid.
     * Continues the game loop until all ships are sunk.
     * Displays the current state of the ocean, along with information such as the grid already entered,
     * the number of shots, hits, and sunk ships.
     * Notifies the player when a ship is sunk.
     * Collects user input for the next shot and updates the game accordingly.
     * Prints the final score once the game is over.
     */
    private void start () {
        ocean.placeAllShipsRandomly();

        while(!ocean.isGameOver()) {
            ocean.print();
            System.out.print("grid already entered");
            System.out.println(getAlreadyPlaced());
            System.out.printf("Shot num %d, Hit num %d, Sunk num %d \n", ocean.getShotsFired(), ocean.getHitCount(), ocean.getShipsSunk());
            if (ocean.getShipArray()[row] [column].isSunk() &&  ocean.isOccupied(row,column)){
                System.out.printf("you have sunk %s \n", ocean.getShipArray()[row] [column].getShipType());
            }
            enterGrid();
            ocean.shootAt(row,column);
            }

        if (ocean.getShipArray()[row] [column].isSunk() &&  ocean.isOccupied(row,column)){
            System.out.printf("you have sunk %s \n", ocean.getShipArray()[row] [column].getShipType());
        }

        System.out.printf("Game Over, Your Score Is %d ", ocean.getShotsFired());

    }


    /**
     * Collects user input for the row and column numbers until valid values are provided.
     * Ensures that the entered grid coordinates are within the acceptable range.
     * Stores the valid input in the 'row' and 'column' variables and adds the corresponding grid location
     * to the 'alreadyPlaced' list.
     */
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

    /**
     * Collects an integer input from the user.
     * Repeatedly prompts the user to enter an integer until a valid integer is entered.
     * @return Return the entered valid integer.
     */
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

    /**
     * Checks whether the given number is a valid point on the grid.
     * Returns true if the number is within the acceptable range, otherwise prompts the user
     * to enter an integer between 0 and 9 and returns false.
     * @param number an int which is to be tested whether it's valid or not
     * @return true if the number is within the acceptable range, otherwise prompt the user and return false.
     */
    public boolean validPoint(int number) {
        if (number > 9 || number < 0 ){
            System.out.printf("please enter an integer between 0 to 9\n");
            return false;
        }
        return true;
    }

    /**
     * Checks whether the specified coordinates (row and column) constitute a valid point on the grid.
     * Converts the row and column into a specific number and verifies if it has already been entered.
     * If the grid has already been placed, prompts the user to re-enter and returns false; otherwise, returns true.
     * @param row int to indicate the row number
     * @param column int to indicate the column number
     * @return true if the grid has not been placed yet; otherwise, false.
     */
    public boolean validGrid (int row, int column) {
        int gridNumber = row * 10 + column;
        if (alreadyPlaced.contains(gridNumber)){
            System.out.printf("the grid has already entered before, please re-enter\n");
            return false;
        }
        return true;
    }

    /**
     * Get the current value of the 'row' variable.
     *
     * @return The current value of the 'row' variable.
     */
    public int getRow(){
        return row;
    }

    /**
     * Get the current value of the 'column' variable.
     *
     * @return The current value of the 'column' variable.
     */
    public int getColumn(){
        return column;
    }


    /**
     * Get the list of grid locations which have already been entered.
     *
     * @return An ArrayList containing the grid locations that have already been placed.
     */
    public ArrayList<Integer> getAlreadyPlaced() {
        return alreadyPlaced;
    }

    /**
     * Main class for the Battleship game.
     * Responsible for setting up the game, receiving user "shots,"
     * displaying results, and printing final scores.
     * First creates an instance of the Ocean and initiates the game (human vs. computer).
     * During each round, user interaction involves taking input and invoking game functions.
     *
     * @author Jason Su and Xiaoying Zhang
     */


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
