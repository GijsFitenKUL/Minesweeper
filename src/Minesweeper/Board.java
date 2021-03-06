package Minesweeper;
//als dit kan lezen werkt het pushen :)
import java.util.ArrayList;
import java.util.Random;

public class Board implements BoardInterface{
    private int Width;
    private int Height;
    private int nrOfBombs;
    private boolean firstClickMade;
    public ArrayList<Cell> currentBoard;

    //Class constructor
    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<>(Height * Width);
        this.firstClickMade = false;

        initialise();
    }

    //Iets kortere en efficientere implementatie van de initialise :)
    private void initialise(){
        int i = 0;
        Random r = new Random();

        //Creates cell objects on all spots in the board
        for(int j = 0; j < this.Width * this.Height; j++){
            Cell c = new Cell();
            currentBoard.add(c);
        }
        System.out.println("Board construction succesful");

        //replaces certain cell objects with bombs.
        while(i < this.nrOfBombs){
            int index = r.nextInt(this.Width * this.Height);
            if(!(currentBoard.get(index) instanceof Bomb)){
                Bomb b = new Bomb();
                currentBoard.set(index, b);
                i++;
            }
        }

        System.out.println("Bombs succesfully placed");

        updateNeighbours();
        System.out.println("Cell neighbours succesfully updated");
    }

    //Update num of neighbours for all cells in the current board
    private void updateNeighbours(){
        for(Cell c : currentBoard){
            c.setNeighbours(getCellNeighbours(c));
        }
    }

    //handles the first click, and changes the board accordingly.
    //It chooses a new random index if the first click lands on a bomb.
    @Override
    public void firstClick(int index){
        if(this.currentBoard.get(index) instanceof Bomb){
            Random r = new Random();
            boolean i = true;

            while(i){
                int newIndex = r.nextInt(this.Width * this.Height);
                if(!(this.currentBoard.get(newIndex) instanceof Bomb)){
                    //ye olde switcharoo
                    Cell c = this.currentBoard.get(newIndex);
                    Cell b = this.currentBoard.get(index);

                    this.currentBoard.set(newIndex, b);
                    this.currentBoard.set(index, c);
                    updateNeighbours();
                    i = false;
                }
            }
        }
        this.firstClickMade = true;
        System.out.println("First move made");
    }

    @Override
    public boolean makeMove(int x, int y) {
        System.out.println("Making move");
        //get index from x and y coords
        int index = y * this.Width + x;

        //check if cell is still hidden
        if(!this.currentBoard.get(index).isHidden())
            return false;

        //check if this is the first move
        if(!this.firstClickMade){
            firstClick(index);
        }

        //Gameover if you hit a bomb
        if(this.currentBoard.get(index) instanceof Bomb){
            ((Bomb) this.currentBoard.get(index)).gameOver();
        }

        //reveal all connected cells that arent bombs.
        revealBoardFromCell(x, y);
        System.out.println("Move Made");

        this.consolePrintBoard();
        return true;
    }

    //fucking recursie, anders wordt dit moeilijk.
    //Ik hoop zo hard dat dit werkt lmao
    private boolean revealBoardFromCell(int x, int y){
        if(x >= this.Width){return true;}
        if(y >= this.Height){return true;}
        if(x < 0){return true;}
        if(y < 0){return true;}

        int index = y * this.Width + x;
        if(this.currentBoard.get(index) instanceof Bomb){return true;}
        if(!this.currentBoard.get(index).isHidden()){return true;}

        this.currentBoard.get(index).reveal();
        revealBoardFromCell(x + 1, y);
        revealBoardFromCell(x - 1, y);
        revealBoardFromCell(x, y - 1);
        revealBoardFromCell(x, y + 1);
        return false;
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
        if(this.currentBoard.contains(c)){
            return this.currentBoard.indexOf(c);
        }
        return -1;
    }

    //Returns the amount of neighbours a cell has which are bombs, returns -1 if the cell is not found.
    @Override
    public int getCellNeighbours(Cell c) {
        int neighbours = 0;
        int pos = getCellPosition(c);
        if(pos == -1){return -1;}

        //exceptions for edge and corner cells
        //corner cases
        //NW
        if(pos == 0){
            if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //SEthis.
        if(pos == currentBoard.size() - 1){
            if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //NE
        if(pos == this.Width - 1){
            if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }
        //SW
        if(pos == this.Width * (this.Height - 1)){
            if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //edge cases
        //N
        if(pos < this.Width){
            if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //E
        if(pos % (this.Width) == this.Width - 1){
            if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //W
        if(pos % (this.Width) == 0){
            if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        //Sthis.
        if(pos >= this.Width * (this.Height-1)){
            if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1) instanceof Bomb){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1) instanceof Bomb){neighbours++;}
            return neighbours;
        }

        if(this.currentBoard.get(pos + 1) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos - 1) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos + this.Width) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos + this.Width + 1) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos + this.Width - 1) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos - this.Width) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos - this.Width + 1) instanceof Bomb){neighbours++;}
        if(this.currentBoard.get(pos - this.Width - 1) instanceof Bomb){neighbours++;}

        return neighbours;
    }

    //Prints a single Row of the board to the console sorry for the typecasting lmao
    private void consolePrintBoardLine(int row){
        char character;
        char[] RowArray = new char[this.Width * 3];

        for(int i = row * Width; i < row * this.Width + this.Width; i++){
            if(this.currentBoard.get(i) instanceof Bomb){
                character = 'B';
            }else{character = (char)(this.currentBoard.get(i).getNeighbours()+'0');}
            RowArray[(i - row*this.Width) * 3] = character;
            RowArray[(i - row*this.Width) * 3 + 1] = ' ';
            RowArray[(i - row*this.Width) * 3 + 2] = ' ';
        }

        //String out = Arrays.toString(RowArray);this.
        System.out.println(RowArray);
    }

    //prints the board to console.
    @Override
    public void consolePrintBoard(){
        for(int i = 0; i < this.Height; i++){
            consolePrintBoardLine(i);
            System.out.println();
        }
    }

    @Override
    public void setFlag(int x, int y) {
        int index = y * this.Width + x;
        this.currentBoard.get(index).setFlag();
    }
}
