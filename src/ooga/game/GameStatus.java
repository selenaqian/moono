package ooga.game;

public class GameStatus {

    private boolean isOver;

    public GameStatus(){
        isOver = false;
    }

    public void endGame(){
        isOver = true;
    }


}
