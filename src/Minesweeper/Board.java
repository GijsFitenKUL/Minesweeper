package Minesweeper;

import java.util.ArrayList;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;

    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public void setWidth(int Width) {
        this.Width = Width;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setHeight(int Height) {
        this.Height = Height;
    }

    @Override
    public int getNrOfBombs() {
        return 0;
    }

    @Override
    public void setNrOfBombs(int nrOfBombs) {
        this.nrOfBombs = nrOfBombs;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCurrentState() {
        return null;
    }

    @Override
    public void setCurrentState() {

    }
}
