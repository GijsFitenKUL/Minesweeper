package Minesweeper;

public class Bomb extends Cell implements BombInterface{
    @Override
    public void gameOver() {
        System.out.println("Game Over!");
    }
}
