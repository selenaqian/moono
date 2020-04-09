package ooga.game;

import ooga.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UnoTurnManager implements TurnManager {

    private List<Player> players;
    private ListIterator<Player> iterator;

    // Direction of "rotation" to the next player
    private int direction;
    final int CW = 1;
    final int CCW = -1;

    private Player current; //the player making the turn

    public UnoTurnManager(List<Player> players){
        this.players = players;
        iterator = players.listIterator();
        current = getFirstPlayer(); // TODO: this doesn't work here because players has nothing in it rn
        direction = CW;
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public Player getFirstPlayer() {
        //TODO: randomize this
        return players.get(0);
    }

    @Override
    public void nextPlayer() {
        if (direction == CCW){
            current = iterator.previous();
        } else {
            iterator.next();
            current = iterator.next();
        }

    }

    @Override
    public Player getCurrentPlayer() {
        //TODO: add error handling for when there are no players added yet
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
