package Minesweeper;

import java.util.ArrayList;
import java.util.Random;
//import java.util.Arrays;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;
    public ArrayList<Cell> currentBoard;
    // private int[] randomXPostition;
    // private int[] randomYPosition;

    //Class constructor, will have Initialise added later!
    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<Cell>(Height * Width);
        // randomYPosition = new int[Height];
        // randomXPostition = new int[Width];

        initialise();
    }

    //initialize and shuffleArray() moeten ng getest worden
/*    public void initialize(){
        for(int i = 0; i < getNrOfBombs(); i++){
            randomXPostition[i] = i;
            randomYPosition[i] = i;
        }
        shuffleArray(randomXPostition);
        shuffleArray(randomYPosition);
    }

    public void shuffleArray(int[] array){
        Random r= new Random();
        for(int i = 0; i < array.length; i++){
            int randomIndex = r.nextInt(array.length);
            int tempValue = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = tempValue;
        }
    }*/

    //Iets kortere en efficientere implementatie van de initialise :)
    private void initialise(){
        int i = 0;
        Random r = new Random();

        while(i < this.nrOfBombs){
            int index = r.nextInt(this.Width * this.Height);
            if(!(currentBoard.get(index) instanceof Bomb)){
                Bomb b = new Bomb();
                currentBoard.set(index, b);
                i++;
            }
        }

        for(int j = 0; j < this.Width * this.Height; j++){
            if(!(currentBoard.get(j) instanceof Bomb)){
                Cell c = new Cell();
                currentBoard.set(j, c);
            }
        }

        updateBoard();
    }

    //Update num of neighbours for all cells in the current board
    private void updateBoard(){
        for(Cell c : currentBoard){
            c.setNeighbours(getCellNeighbours(c));
        }
    }

    //handles the first click, and changes the board accordingly.
    //It chooses a new random index if the first click lands on a bomb.
    @Override
    public void firstClick(int index){
        if(currentBoard.get(index) instanceof Bomb){
            Random r = new Random();
            boolean i = true;

            while(i){
                int newIndex = r.nextInt(this.Width * this.Height);
                if(!(currentBoard.get(newIndex) instanceof Bomb)){
                    //ye olde switcharoo
                    Cell c = currentBoard.get(newIndex);
                    Cell b = currentBoard.get(index);

                    currentBoard.set(newIndex, b);
                    currentBoard.set(index, c);
                    updateBoard();
                    i = false;
                }
            }
        }
    }


    //Just getters and setters
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

    //Returns the position in the board array of the given cell, returns -1 if cell is not found.
    @Override
    public int getCellPosition(Cell c) {
        if(currentBoard.contains(c)){
            return currentBoard.indexOf(c);
        }
        return -1;
    }

    //Returns the amount of neighbours a cell has which are bombs, returns -1 if the cell is not found.
    @Override
    public int getCellNeighbours(Cell c) {
        int neighbours = 0;
        int pos = getCellPosition(c);
        if(pos == -1){return -1;}

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
