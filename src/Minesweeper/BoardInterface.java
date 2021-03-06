package Minesweeper;

public interface BoardInterface {
   int getCellPosition(Cell c);
   int getCellNeighbours(Cell c);
   void firstClick(int index);
   void makeMove (int x, int y);
   void setFlag(int x, int y);
   char getCellContent(int x, int y);
   boolean hasLost();
   boolean hasWon();
}
