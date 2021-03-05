package Minesweeper;

//hey
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

    @Override
    public int getCellNeighbours(Cell c) {
        int neighbours = 0;
        int pos = getCellPosition(c);

        if(currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getWidth()) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getWidth() + 1) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getWidth() - 1) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getHeight()) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getHeight() + 1) instanceof Bomb){neighbours++;}
        if(currentBoard.get(pos + this.getHeight() - 1) instanceof Bomb){neighbours++;}

        return neighbours;
    }
}
