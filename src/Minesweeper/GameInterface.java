package Minesweeper;

public interface GameInterface {
    void makeMove(int x, int y);
    void setFlag(int x, int y);
    char getCellContent(int x, int y);
    boolean hasWon();
    boolean hasLost();
}
