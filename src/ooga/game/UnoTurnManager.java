package ooga.game;

import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import ooga.player.Player;

import java.util.*;

public class UnoTurnManager implements TurnManager {

    private List<Player> players;
    private ListIterator<Player> iterator;
    private Player humanPlayer;

    // Direction of "rotation" to the next player
    private int direction;
    public static final int CW = 1;
    public static final int CCW = -1;

    private Player current; //the player making the turn

    public UnoTurnManager(List<Player> players){
        setPlayers(players);
        direction = CW;
    }

    //NOTE FROM TESS: for the XMLEncoder I needed to add a default constructor plus setters/getters

    /**
     * Creates new default UnoTurnManager required for XML.
     * @author Tess Noonan (tcn6)
     * NOTE: I have set the default players list as 1 human, 3 bots because xml can't save the players because List is
     * not serializable.
     */
    public UnoTurnManager() {
        setPlayers(new ArrayList<>(Arrays.asList(new ManualPlayer(), new AI_Player(), new AI_Player(), new AI_Player())));
        direction = CW;
    }

    /**
     * Sets initial list of players.
     * @param players
     * @author Tess Noonan (tcn6)
     */
    public void setPlayers(List<Player> players){
        this.players = players;
        iterator = players.listIterator();
        current = getFirstPlayer(); // TODO: this doesn't work here because players has nothing in it rn
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
     * @return
     */
    @Override
    public Player getNextPlayer() {
        //FIXME: use iterator or clean this up
        int currID = current.getID();
        if(currID == 1 && direction == CCW){
            return players.get(players.size()-1);
        } else if (currID < players.size() && direction == CW){
            return players.get(currID);
        } else if (currID == players.size() && direction == CW){
            return players.get(0);
        } else if (currID <= players.size() && direction == CCW){
            return players.get(currID - 2);
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
    public void nextPlayer() {
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
    @Override
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

    public void setHumanPlayer(Player player){
        humanPlayer = player;
    }

    public Player getHumanPlayer(){
        return humanPlayer;
    }

    public boolean isHumanTurn(){
        if (current instanceof ManualPlayer) {
            return true;
        }

        return false;
    }

    //XMLEncoder requires matching setters and getters, so here all the ones we still need:
    //@author Tess Noonan (tcn6)

    /**
     * Set direction.
     * @param dir
     */
    public void setDirection(int dir) {
        direction = dir;
    }

    /**
     * Set current.
     * @param curr
     */
    public void setCurrentPlayer(Player curr) {
        current = curr;
    }

}
