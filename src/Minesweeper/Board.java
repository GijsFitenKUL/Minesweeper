package Minesweeper;
import java.util.ArrayList;
import java.util.Random;

public class Board implements BoardInterface{
    private final int Width;
    private final int Height;
    private final int nrOfBombs;
    private boolean firstClickMade;
    public ArrayList<Cell> currentBoard;
    private boolean hasWon;
    private boolean hasLost;

    //Class constructor
    public Board(int Width, int Height, int nrOfBombs){
        this.Width = Width;
        this.Height = Height;
        this.nrOfBombs = nrOfBombs;
        this.currentBoard = new ArrayList<>(Height * Width);
        this.firstClickMade = false;
        this.hasLost = false;
        this.hasWon = false;

        initialise();
    }

    //Iets kortere en efficientere implementatie van de initialise :)
    private void initialise(){
        int i = 0;
        Random r = new Random();

        //Creates cell objects on all spots in the board
        for(int j = 0; j < this.Width * this.Height; j++){
            Cell c = new Cell();
            this.currentBoard.add(c);
        }
        System.out.println("Board construction succesful");

        //replaces certain cell objects with bombs.
        while(i < this.nrOfBombs){
            int index = r.nextInt(this.Width * this.Height);
            if(!(this.currentBoard.get(index).isBomb())){
                Bomb b = new Bomb();
                this.currentBoard.set(index, b);
                i++;
            }
        }
        //Updates cell values of nr of neighbours
        updateNeighbours();
    }

    //Update num of neighbours for all cells in the current board
    private void updateNeighbours(){
        for(Cell c : this.currentBoard){
            c.setNeighbours(getCellNeighbours(c));
        }
    }

    //handles the first click, and changes the board accordingly.
    //It chooses a new random index if the first click lands on a bomb.
    @Override
    public void firstClick(int Oldindex){
        if(this.currentBoard.get(Oldindex).isBomb()){
            Random r = new Random();
            boolean i = true;

            while(i){
                int newIndex = r.nextInt(this.Width * this.Height);
                if(!(this.currentBoard.get(newIndex).isBomb())){
                    //ye olde switcharoo
                    this.currentBoard.set(newIndex, new Bomb());
                    this.currentBoard.set(Oldindex, new Cell());
                    updateNeighbours();
                    i = false;
                }
            }
        }
        System.out.println("First move made");
    }

    @Override
    public void makeMove(int x, int y) {
        System.out.println("Making move");
        int index = y * this.Width + x;

        //check if cell is still hidden
        if(!this.currentBoard.get(index).isHidden())
            return;

        //check if this is the first move
        if(!this.firstClickMade){
            firstClick(index);
        }

        //Gameover if you hit a bomb
        if(this.firstClickMade){
            if(this.currentBoard.get(index).isBomb()){
                this.revealAllBombs();
                this.hasLost = true;
                System.out.println("Hi");
            }
        }

        //reveal all connected cells that arent bombs. then checks for win conditions
        revealBoardFromCell(x, y);
        testForWin();
        this.firstClickMade = true;
    }

    //tests all win condition:
    // - All non bomb cells are not hidden
    // - No non bomb cells are flagged
    // - All bombs are flagged.
    private void testForWin() {
        for(Cell c : this.currentBoard){
            if(c.isHidden() && !c.isBomb()){return;}
            if(c.isFlag() && !c.isBomb()){return;}
            if(!c.isFlag() && c.isBomb()){return;}
        }
        this.hasWon = true;
    }

    //fucking recursie, anders wordt dit moeilijk.
    //Ik hoop zo hard dat dit werkt lmao
    //Zou alle aangesloten cell moeten vrijgeven, stopt aan de randen van het bord en aan bommen
    //Werkt niet diagonaal nu, leek me iets te makkelijk
    private void revealBoardFromCell(int x, int y){
        if(x >= this.Width){return;}
        if(y >= this.Height){return;}
        if(x < 0){return;}
        if(y < 0){return;}

        int index = y * this.Width + x;
        if(this.currentBoard.get(index) instanceof Bomb){return;}
        if(!this.currentBoard.get(index).isHidden()){return;}

        this.currentBoard.get(index).reveal();
        revealBoardFromCell(x + 1, y);
        revealBoardFromCell(x - 1, y);
        revealBoardFromCell(x, y - 1);
        revealBoardFromCell(x, y + 1);
    }

    private void revealAllBombs(){
        for(Cell c : this.currentBoard){
            if(c instanceof Bomb){c.reveal();}
        }
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
            if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //SEthis.
        if(pos == this.currentBoard.size() - 1){
            if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //NE
        if(pos == this.Width - 1){
            if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1).isBomb()){neighbours++;}
            return neighbours;
        }
        //SW
        if(pos == this.Width * (this.Height - 1)){
            if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //edge cases
        //N
        if(pos < this.Width){
            if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //E
        if(pos % (this.Width) == this.Width - 1){
            if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //W
        if(pos % (this.Width) == 0){
            if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos + this.Width + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1).isBomb()){neighbours++;}
            return neighbours;
        }

        //S
        if(pos >= this.Width * (this.Height-1)){
            if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width + 1).isBomb()){neighbours++;}
            if(this.currentBoard.get(pos - this.Width - 1).isBomb()){neighbours++;}
            return neighbours;
        }

        if(this.currentBoard.get(pos + 1).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos - 1).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos + this.Width).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos + this.Width + 1).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos + this.Width - 1).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos - this.Width).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos - this.Width + 1).isBomb()){neighbours++;}
        if(this.currentBoard.get(pos - this.Width - 1).isBomb()){neighbours++;}

        return neighbours;
    }

    //sets a flag at the gicen coordinates. Also test for win conditions
    @Override
    public void setFlag(int x, int y) {
        int index = y * this.Width + x;
        if(this.currentBoard.get(index).isFlag()){this.currentBoard.get(index).unsetFlag();}
        else{this.currentBoard.get(index).setFlag();}
        testForWin();
    }

    //returns a character resembling the content of the cell that should be shown on screen
    @Override
    public char getCellContent(int x, int y) {
        int index = y * this.Width + x;
        char content;
        if(this.currentBoard.get(index).isFlag()){content = 'f';}
        else if(this.currentBoard.get(index).isHidden()){content = 'h';}
        else if(this.currentBoard.get(index) instanceof Bomb){content = 'b'; }
        else{content = (char)(this.currentBoard.get(index).getNeighbours()+'0');}
        return content;
    }

    //Returns true if the game is lost (bomb has been clicked) when called
    @Override
    public boolean hasLost() {
        return this.hasLost;
    }

    //return true if the game is won when called! First checks if the game has been lost already, just to make sure that no one cheats.
    @Override
    public boolean hasWon() {
        if(this.hasLost){return false;}
        return this.hasWon;
    }
}
