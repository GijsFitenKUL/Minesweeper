package Minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;
    public ArrayList<Cell> currentBoard;
    public boolean[] allCells; //array van lengte van alle cellen false = geen bom en true = bom

    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<Cell>(Height * Width);
        this.allCells = new boolean[Height*Width];
    }

    public void setBombs(){  //plaats willekeurig true in de array aantal true = nrOfBombs
        Random r = new Random();
        Random r1 = new Random();
        for(int i = 0; i < nrOfBombs; i++) {
            int rand = r.nextInt(allCells.length);  // random number to choose the location of the bomb
            int rand1 = r.nextInt(allCells.length); //second random number
            if (allCells[rand] == false) { //indien er al een bom op een bepaalde index staat plaats hij een bom adhvh tweede willekeurig getal
                allCells[rand] = true;
            } else {
                allCells[rand1] = true;
            }
        }
        System.out.println("bombs: ");
        int nrOfBombsInArray = 0;
        for(boolean bom: allCells){
            System.out.println(bom);
            if(bom == true){
                nrOfBombsInArray++;
            }
        }
        System.out.println("nr of bombs: " + nrOfBombs + "nr of bombs in array: " + nrOfBombsInArray); //controle of er evenveel true is als er nrOfBombs zijn
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
