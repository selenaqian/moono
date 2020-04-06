package ooga.game;

import java.util.ListIterator;

public class UnoTurnManager implements TurnManager {

    private List<Player> players;
    private ListIterator<Player> iterator;

    // Direction of "rotation" to the next player
    private int direction;
    final int CW = 1;
    final int CCW = -1;

    private Player current; //the player making the turn

    public UnoPlayerManager(players){
        this.players = players;
        iterator = players.listIterator();
        current = getFirstPlayer();
        direction = CW;
    }

    @Override
    public Player getFirstPlayer() {
        //TODO: randomize this
        return players.get(0);
    }

    @Override
    public Player getNextPlayer() {
        if (direction == CCW){
            return iterator.previous();
        }
        return iterator.next();
    }

    @Override
    public Player getCurrentPlayer() {
        return current;
    }

    /**
     * Changes the direction, affecting which player has the next turn
     * Typically called when an uno reverse card is played
     * @see getNextPlayer()
     */
    public void changeDirection(){
        if (direction == CW) {
            direction = CCW;
        } else {
            direction = CW;
        }
    }
}
