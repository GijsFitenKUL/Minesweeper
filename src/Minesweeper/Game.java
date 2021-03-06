package Minesweeper;

public class Game implements GameInterface{
    private final Board gameBoard;

    public Game(int width, int height, int nrOfBombs){
        this.gameBoard = new Board(width, height, nrOfBombs);
    }

    @Override
    public void makeMove(int x, int y) {
        this.gameBoard.makeMove(x, y);
    }

    @Override
    public void setFlag(int x, int y) {
        this.gameBoard.setFlag(x, y);
    }

    @Override
    public char getCellContent(int x, int y) {
        return this.gameBoard.getCellContent(x, y);
    }

    @Override
    public boolean hasWon() {
        return this.gameBoard.hasWon();
    }

    @Override
    public boolean hasLost() {
        return this.gameBoard.hasLost();
    }
}
