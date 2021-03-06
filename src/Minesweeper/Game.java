package Minesweeper;

public class Game implements GameInterface{
    public int width;
    public int height;
    public int nrOfBombs;

    public Game(int width, int height, int nrOfBombs){
        Board gameBoard = new Board(width, height, nrOfBombs);
        gameBoard.consolePrintBoard();
    }


    public static void main(String[] args) {
        Game newGame = new Game(20, 5, 15);
    }
}
