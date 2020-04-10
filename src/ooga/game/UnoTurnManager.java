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
        } else if (iterator.hasNext()){
            iterator.next();
            current = iterator.next();
        } else if (!iterator.hasPrevious()){
            iterator = players.listIterator(players.size());
        } else {
            //reset iterator to beginning after looping through all players
            iterator = players.listIterator();
        }
    }

    @Override
    public Player getCurrentPlayer() {
        //TODO: add error handling for when there are no players added yet
        return current;
    }

    @Override
    public int getPlayerId(Player player){
        for (Player p : players){
            if(player == p){
                return players.indexOf(p);
            }
        }

        return 0; //TODO: throw exception
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
