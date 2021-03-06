package Minesweeper;

public interface CellInterface {
    void setFlag();
    boolean isFlag();
    void reveal();
    boolean isHidden();
    void unsetFlag();
    void setNeighbours(int n);
    int getNeighbours();
}
