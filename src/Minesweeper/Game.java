package Minesweeper;

public class Game {
    public int width;
    public int height;
    public int nrOfBombs;

    public Game(int width, int height, int nrOfBombs){
        Board gameBoard = new Board(width, height, nrOfBombs);
    }

    public static void main(String[] args) {
    }
}
