package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper class to apply action card effects to the game of uno
 *@see UnoTurnManager skipping turns would be done by calling turnManager
 *turnManager also has a method for keeping track of direction and changing the direction
 * @see Uno setWildColor(String color) in Uno
 *
 * @author Mary Jiang (mvj6)
 * @author Tess Noonan (tcn6)
 * @author Suomo Ammah (sna19)
 */
public class UnoActionApplier {
    Card card;
    Player chosenswap;
    Player swapper;
    Player player1;
    Player player2;

    public static final int FOUR = 4;
    public static final int TWO = 2;

    private UnoTurnManager turnManager;
    private Uno uno;

    private WildcardObserver observer;
    private SwapCardObserver oserver;

    /**
     * Create new UnoActionApplier
     * @param uno
     * @param turnManager
     */
    public UnoActionApplier(Uno uno, UnoTurnManager turnManager){
        this.turnManager = turnManager;
        this.uno = uno;

    }

    public void registerWildObserver(WildcardObserver o){
        this.observer = o;
    }

    /**
     * Notifies observers to make WildColorSelectorView show up
     */
    public void notifyWildObserver(){
        if (turnManager.isHumanTurn()){
            observer.showColorSelector();
        } else {
            //pick a random suit to set the wildcard color to for AI players that can't interact with the view
            //TODO: possibly have a method in the Suit enum to return a random suit
            int rnd = new Random().nextInt(Suit.values().length);
            uno.setWildColor(Suit.values()[rnd].toString());
        }
    }

    /**
     * Called from WildColorSelectorView
     * @param selectedColor color that was clicked on by the user in the view
     */
    public void setWildColor(String selectedColor){
        uno.setWildColor(selectedColor);
    }

    /**
     * Applies the action if it's a special Card
     * Does nothing if it's a normal card.
     * @param value
     */
    public void applyAction(Value value){

        switch(value){
            case SKIP:
                applySkip();
                break;
            case REVERSE:
                applyReverse();
                break;
            case DRAW2:
                applyDraw2();
                break;
            case WILD:
                applyWild();
                break;
            case WILD4:
                applyWild4();
                break;
            case SWAP:
                applySwap(card, chosenswap, swapper);
            case TRADE:
                applyTrade(player1, player2);
            default:
                //this is a normal card: do nothing
                return;
        }
    }

    /**
     * Skips turn of next player.
     */
    private void applySkip(){
        turnManager.nextPlayer();
    }

    /**
     * Reverses direction of the play.
     */
    private void applyReverse(){
        turnManager.changeDirection();

    }

    /**
     * Draws 2 cards to the next player and skips their turn.
     */
    private void applyDraw2(){
        Player nextPlayer = turnManager.getNextPlayer();
        for(int i=0;i<TWO;i++){
            nextPlayer.takecard(uno.getPileManager().drawCard());
        }
    }

    /**
     * Changes color of play.
     */
    private void applyWild(){
        notifyWildObserver();
        turnManager.nextPlayer();
    }

    /**
     * Changes color of play, draws 4 cards to the next player, and skips their turn.
     */
    private void applyWild4(){
        notifyWildObserver();
        Player nextPlayer = turnManager.getNextPlayer();
        for(int i = 0; i < FOUR; i++){
            uno.drawCard(nextPlayer);
        }
        turnManager.nextPlayer();
    }


    /**
     * allows the manual player to swap a card with an AI player of choice
     * */
    private void applySwap(Card card, Player chosenswap, Player swapper){
        Random random = new Random();
        chosenswap.hand().addCard(card);
        swapper.hand().removeCard(card);
        int rand_int = random.nextInt(chosenswap.hand().getCardCount());
        swapper.hand().addCard(chosenswap.hand().getcard(rand_int));
    }

    /**
     * allows the manual player to trade hands with an AI player
     */
    private void applyTrade(Player player1,Player player2){
        Random random = new Random();
        int randon = random.nextInt(uno.getaiPlayers().size());
        player2 = uno.getaiPlayers().get(randon);
        int size = player2.hand().getCardCount();
        List<Card> tempholder = new ArrayList<>();
        for(Card card:player1.hand().getAllCards()){
            player2.hand().addCard(card);
            tempholder.add(card);
        }

        for(Card card:tempholder){
            player1.hand().removeCard(card);
        }

        for(int i = 0;i<size-1;i++){
            player1.hand().addCard(player2.hand().getcard(i));
            player2.hand().removeCard(player2.hand().getcard(i));}
    }



    /**
     * Notifies observers to make SwapCardSelectorView show up
     */
    public void notifySwapObserver() {
        if (turnManager.isHumanTurn()) {
            oserver.showplayerandcardoptions();
        }
    }


    public void registerSwapObserver(SwapCardObserver o){
        this.oserver = o;
    }

    public List<Player> getplayers(){
        return uno.getaiPlayers();
    }
}
