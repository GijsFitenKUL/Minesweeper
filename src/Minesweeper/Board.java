package Minesweeper;

import java.util.ArrayList;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;
    private ArrayList<Integer> currentBoard;

    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<Integer>(Height * Width);
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
    public ArrayList<Integer> getCurrentState() {
        return this.currentBoard;
    }

    @Override
    public void setCurrentState() {

    }
}
