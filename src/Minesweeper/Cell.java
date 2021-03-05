package Minesweeper;

public class Cell implements CellInterface {
    private int neighbours;
    private boolean hidden;
    private boolean isFlag;


    @Override
    public int getNeighbours() {

        return 0;
    }

    @Override
    public int getPosition() {

        return -1;
    }
}
