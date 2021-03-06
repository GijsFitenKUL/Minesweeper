package Minesweeper;

public class Game implements GameInterface{
    public int width;
    public int height;
    public int nrOfBombs;
    private Board gameBoard;

    public Game(int width, int height, int nrOfBombs){
        this.gameBoard = new Board(width, height, nrOfBombs);
        gameBoard.consolePrintBoard();
    }


    public static void main(String[] args) {
        Game newGame = new Game(20, 5, 15);
        newGame.makeMove(4, 3);
    }

    @Override
    public void makeMove(int x, int y) {
        this.gameBoard.makeMove(x, y);
    }

    @Override
    public void setFlag(int x, int y) {
        this.gameBoard.setFlag(x, y);
    }
}
