package Minesweeper;

import java.util.ArrayList;

public interface BoardInterface {
   int getWidth();
   void setWidth(int Width);
   int getHeight();
   void setHeight(int Height);
   int getNrOfBombs();
   void setNrOfBombs(int nrOfBombs);
   ArrayList<ArrayList<Integer>> getCurrentState();
   void setCurrentState();
}
