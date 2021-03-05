package Minesweeper;

public class Cell implements CellInterface {
    private int neighbours;
    private boolean hidden;
    private boolean isFlag;

    public Cell(){
        this.hidden = true;
        this.isFlag = false;
    }

    @Override
    public void setFlag() {
        this.isFlag = true;
    }

    @Override
    public boolean isFlag() {
        return isFlag;
    }

    @Override
    public void reveal() {
        this.hidden = false;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void unsetFlag() {
        this.isFlag = false;
    }
}
