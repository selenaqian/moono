package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.config.GameInfo;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import ooga.player.Player;
import ooga.rules.ClassicRules;
import ooga.rules.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Model class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno implements GameModel, GameModelView {

    public static final int UNO_PENALTY = 2;
    public static final double AI_UNO_PROB = 0.5;

    private ArrayList<PlayerObserver> playerObservers;

    private GameSettings mySettings;
    private PileManager piles;
    private UnoTurnManager turnManager;
    private UnoActionApplier actionApplier; //contains methods for action cards]
    private Rule rule;
    private List<Card> specialCards;

    private Boolean didCallUno = false;

    public Uno(){
        this(new GameSettings());

    }

    public Uno(GameSettings settings){
        mySettings = settings;
        piles = new PileManager(mySettings.getSpecialCards());
        rule = mySettings.getRule();
        turnManager = new UnoTurnManager();
        turnManager.addPlayers(mySettings.getNumPlayers());
        specialCards = mySettings.getSpecialCards();
        dealCards();
        actionApplier = new UnoActionApplier(this, turnManager);
        playerObservers = new ArrayList();
    }

    //TODO: clean up constructors
    public Uno(GameSettings settings, PileManager piles, UnoTurnManager turnManager){
        this.mySettings = settings;
        this.piles = piles;
        this.turnManager = turnManager;
        rule = mySettings.getRule();
    }


    public GameSettings getSettings(){
        return mySettings;
    }

    public PileManager getPileManager(){
        return piles;
    }

    public UnoTurnManager getTurnManager(){
        return turnManager;
    }


    @Override
    public void start() {
        notifyPlayerObservers();
    }

    @Override
    public void restart() {
        piles.init();
        turnManager.clearPlayerHands();
        dealCards();
    }

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     * @param selectedCard the card selected by the player in the view or selected by the AI player
     */
    @Override
    public boolean playCard(Card selectedCard, Player player){
        //check if played card can be played on top of the discard pile top card
        if (rule.isValid(piles.showTopCard(), selectedCard)) {

            //make sure player updates their hand to remove the card
            player.removecard(selectedCard);

            //update the discard pile to add the card
            piles.discardCard(selectedCard);

            //apply associated action
            actionApplier.applyAction(selectedCard.getValue());
            endTurn();
        }
        return false;
    }

    /**
     * Method used for AI players to play a card
     * Temporary use for Sprint 1
     */
    public boolean playCard(Player player) {
        //go through each of the cards in the hand and try playing each card
        turnManager.getCurrentPlayer().hand().sortHand(); //sna19-order card so highest possible is played always
        for (Card card : turnManager.getCurrentPlayer().hand().getAllCards()) {
            if (rule.isValid(piles.showTopCard(), card)) {
                return playCard(card, player);
            }
        }

        //when no playable card is found
        drawCard(player);
        endTurn();
        return false;
    }

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view for human players
     * For AI players, must be called programmatically
     */
    @Override
    public void drawCard(Player player){
        //take the top card from the discard pile, make sure it is removed from the discard pile
        Card card = piles.drawCard();

        //get player to accept the drawn card into their own hand of cards
        player.takecard(card);
     }

     public void endTurn(){
         notifyPlayerObservers();
         turnManager.nextPlayer();
     }

    @Override
    public List<Card> getUserHand() {
        return turnManager.getHumanPlayer().hand().getAllCards();
    }

    @Override
    public Card getTopDiscardCard() {
        return piles.showTopCard();
    }

    @Override
    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    @Override
    public void registerPlayerObserver(PlayerObserver o) {
        playerObservers.add(o);
    }

    @Override
    public void removePlayerObserver(PlayerObserver o) {
        playerObservers.remove(playerObservers.indexOf(o));
    }

    @Override
    public void notifyPlayerObservers() {
        for (PlayerObserver o : playerObservers){
            for (Player player : turnManager.getAllPlayers()){
                o.updatePlayerHand(player.getID(), player.hand().getAllCards());
            }
            o.updateDiscardPile(piles.showTopCard());
        }
    }


    /**
     * Deal cards to all players in the beginning of a game
     */
    private void dealCards(){
        for(int i = 0; i < mySettings.getNumPlayers(); i ++){
            Player player = turnManager.getAllPlayers().get(i);
            for (int j = 0; j < mySettings.getHandSize(); j++){
                Card card = piles.drawCard();
                player.takecard(card);
            }
        }
    }

    /**
     * Returns the action applier object for use in the WildcardObserver
     * Called from GameView to initialize a WildColorSelectorView
     * @return
     */
    public UnoActionApplier getActionApplier(){
        return actionApplier;
    }


    /**
     * Sets the new color of the discard pile when a wild card is drawn
     * May be used by controller and view to handle user selection of color
     * //Hi Tess here I removed the color parameter based on how I think it's used by the ActionApplier
     * //@param color represents suit of selected wild card "color"
     */
    public void setWildColor(String color){
        Suit cardColor = Suit.valueOf(color);
        //remove wild card that was just placed and get the value (whether it was WILD or WILD4)
        Value wildValue = piles.drawCard().getValue();
        piles.discardCard(new Card(cardColor, wildValue));
    }

//    /**
//     * Handle effect of an action card when it played
//     * This method should have access to TurnManager, especially for Reverse cards
//     * TODO: put action methods in a resource file and use this method to call them
//     */
//    public void handleAction(){
//
//    }

    /**
     * Allows the human player to declare uno
     * Called from view class when the "uno" button is clicked
     */
    public void callUno(){
        didCallUno = true;
    }

    /**
     * Check if user has declared uno
     * If user has not declared, then they must pick up more cards
     */
    public boolean checkUno(){
        if (hasUno() && didCallUno == false){
            //System.out.println("UNO penalty to player " + turnManager.getCurrentPlayer().getID());
            for (int i = 0; i < UNO_PENALTY; i++){
                turnManager.getCurrentPlayer().takecard(piles.drawCard());
            }
            return true;
        }
        didCallUno = false;
        return false;
    }


    public boolean AIDeclareUno(){
        if ((hasUno() && Math.random() < AI_UNO_PROB)){
            callUno();
            return true;
        }

        return false;
    }

    private boolean hasUno(){
        Player current = turnManager.getCurrentPlayer();
        if(current.hand().getCardCount() == 1){
            if (rule.isValid(piles.showTopCard(), current.hand().getAllCards().get(0))){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOver(){
        return rule.isOver(getTopDiscardCard(), turnManager.getCurrentPlayer().hand());

    }


}
