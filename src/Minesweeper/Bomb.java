package Minesweeper;

public class Bomb extends Cell implements BombInterface{

    @Override
    public boolean gameOver() {
        return true;
    }

    @Override
    public boolean isBomb() {
        return true;
    }
}
