package ooga.game;

import ooga.player.Player;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
public class UnoTurnManager implements TurnManager {

    private List<Player> players;
    private ListIterator<Player> iterator;

    // Direction of "rotation" to the next player
    private int direction;
    public static final int CW = 1;
    public static final int CCW = -1;

    private Player current; //the player making the turn
    private int currentId = 1;

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
        //TODO: randomize this(sna19)
        int playersize = players.size();
        Random rand = new Random();
        int randstarter = rand.nextInt(playersize);
        return players.get(randstarter);
    }


    /**
     * Player ids begin at 1 instead of 0
     * @param direction
     * @return
     */
    @Override
    public Player getNextPlayer() {

        //FIXME: use iterator or clean this up
        int currID = current.getID();
        if(currID == 1 && direction == CCW){
            current =  players.get(players.size()-1);
        } else if (currID < players.size() && direction == CW){
            current = players.get(currID);
        } else if (currID == players.size() && direction == CW){
            current =  players.get(0);
        } else if (currID <= players.size() && direction == CCW){
            current =  players.get(currID - 2);
        }
        return current;

//        if (iterator.hasNext()){
//            iterator.next();
//            current = iterator.next();
//        } else if (!iterator.hasPrevious()){
//            iterator = players.listIterator(players.size());
//        } else {
//            //reset iterator to beginning after looping through all players
//            iterator = players.listIterator();
//        }
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
     */
    public void changeDirection(){
        if (direction == CW) {
            direction = CCW;
        } else {
            direction = CW;
        }
    }

    /**
     * Returns all players a TurnManager is keeping track of
     * Used in UnoController to calculate scores for all players
     * @return
     */
    public List<Player> getAllPlayers() {
        return players;
    }

     /** Gives current direction.
     * THIS IS USED JUST FOR TESTING PURPOSES
      * refactored uno to use this method(sna19) since it makes it easier to work with the direction
     */
    public int getDirection(){
        return direction;
    }
}
