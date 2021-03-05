package Minesweeper;

import java.util.ArrayList;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;
    public ArrayList<Cell> currentBoard;

    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<Cell>(Height * Width);
    }

    @Override
    public int getWidth() {
        return this.Width;
    }

    @Override
    public void setWidth(int Width) {
        this.Width = Width;
    }

    @Override
    public int getHeight() {
        return this.Height;
    }

    @Override
    public void setHeight(int Height) {
        this.Height = Height;
    }

    @Override
    public int getNrOfBombs() {
        return this.nrOfBombs;
    }

    @Override
    public void setNrOfBombs(int nrOfBombs) {
        this.nrOfBombs = nrOfBombs;
    }

    @Override
    public ArrayList<Cell> getCurrentState() {
        return this.currentBoard;
    }

    @Override
    public void setCurrentState(ArrayList<Cell> newBoard) {
        this.currentBoard = newBoard;
    }

    @Override
    public int getCellPosition(Cell c) {
        int j = 0;
        for(Cell i:currentBoard){
            if(i.equals(c)){
                return j;
            }
            j++;
        }
        return -1;
    }
}
