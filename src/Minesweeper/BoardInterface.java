package Minesweeper;

import java.util.ArrayList;

public interface BoardInterface {
   void initialise();
   int getWidth();
   void setWidth();
   int getHeight();
   void setHeight();
   int getNrOfBombs();
   void setNrOfBombs();
   ArrayList<ArrayList<Integer>> getCurrentState();
   void setCurrentState();
}
