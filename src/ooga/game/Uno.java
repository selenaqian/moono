package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import ooga.player.Player;
import ooga.rules.ClassicRules;
import ooga.rules.Rule;
import ooga.view.GameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno implements GameModel {

    public static final int UNO_PENALTY = 4;

    private ArrayList<PlayerObserver> playerObservers;

    private GameSettings mySettings;
    private UnoTurnManager turnManager;
    private List<Player> players = new ArrayList<Player>();
    private Player currentPlayer;
    private Player user; //the human player

    private UnoActionApplier actionApplier; //contains methods for action cards

    private DiscardPile discPile;
    private DrawPile drawPile;

    private Rule rule;
    private List<Card> specialCards;

    private Boolean didCallUno = false;

    public Uno(){
        this(new GameSettings());
    }

    public GameSettings getSettings(){
        return mySettings;
    }

    public Uno(GameSettings settings){
        playerObservers = new ArrayList();
        mySettings = settings;
        //rule = mySettings.getRule();
        rule = new ClassicRules();
        specialCards = mySettings.getSpecialCards();

        addPlayers();
        turnManager = new UnoTurnManager(players);
        currentPlayer = players.get(0);
        user = currentPlayer; //TODO: change this so that the human doesn't always start first
        discPile = new DiscardPile();
        drawPile = new DrawPile();
        dealCards();
        //flip over the first card
        discPile.addCard(drawPile.drawCard());

        //update everything in view when game is first started
        notifyPlayerObservers();

        actionApplier = new UnoActionApplier(this, turnManager);
    }

    @Override
    public void start() {
    }

    @Override
    public void restart() {
        //TODO: add method here to clear cards from player hands
        discPile = new DiscardPile();
        drawPile = new DrawPile();
        dealCards();
        //flip over the first card
        discPile.addCard(drawPile.drawCard());
    }

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     * @param selectedCard the card selected by the player in the view or selected by the AI player
     */
    @Override
    public boolean playCard(Card selectedCard){
        //check if played card can be played on top of the discard pile top card
        if (rule.isValid(discPile.showTopCard(), selectedCard)) {
            //make sure a player with one valid card left has called uno
            checkUno();

            //make sure player updates their hand to remove the card
            currentPlayer.playCard(selectedCard);

            //update the discard pile to add the card
            discPile.addCard(selectedCard);

            //apply associated action
            actionApplier.applyAction(selectedCard.getValue());

            //go to next player only when a valid card is played
            endTurn(); //contains call to observers to update the view
        }
        return false;
    }

    /**
     * Method used for AI players to play a card
     * Temporary use for Sprint 1
     */
    public boolean playCard(GameView gameView) {
        //go through each of the cards in the hand and try playing each card
        for (Card card : currentPlayer.hand().getAllCards()) {
            if (rule.isValid(discPile.showTopCard(), card)) {
                return playCard(card);
            }
        }

        //when no playable card is found
        drawCard();
        endTurn();
        return false;
    }

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view for human players
     * For AI players, must be called programmatically
     */
    @Override
    public void drawCard(){
        //when draw pile is empty, put discard pile cards into it
        if (drawPile.getCardCount() == 0){
            drawPile = new DrawPile(discPile.getAllCards());
            discPile = new DiscardPile();
        }

        //take the top card from the discard pile, make sure it is removed from the discard pile
        Card card = drawPile.drawCard();

        //get player to accept the drawn card into their own hand of cards
        currentPlayer.takeCard(card);
        //TODO: get the visual updating to work here too:
        // gameView.updateHand(getTurnManager().getPlayerId(getTurnManager().getCurrentPlayer()), getNumCardsInPlayerHand());
        endTurn();
    }

    @Override
    public List<Card> getUserHand() {
        return user.hand().getAllCards();
    }

    @Override
    public Card getTopDiscardCard() {
        return discPile.showTopCard();
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
            //FIXME: possibly pass in a hand instead of a list of cards?
            o.updatePlayerHand(currentPlayer.getID(), currentPlayer.hand().getAllCards());
            o.updateDiscardPile(discPile.showTopCard());
        }
    }

    private void endTurn(){
        notifyPlayerObservers(); //tells observers about update to player hand
        turnManager.nextPlayer();
        currentPlayer = turnManager.getCurrentPlayer();
        didCallUno = false;
    }

    /**
     * Deal cards to all players in the beginning of a game
     */
    private void dealCards(){
        for(int i = 0; i < mySettings.getNumPlayers(); i ++){
            Player player = players.get(i);
            for (int j = 0; j < mySettings.getHandSize(); j++){
                Card card = drawPile.drawCard();
                player.takeCard(card);
            }
        }
    }

    /**
     * Adds players to the game based on number of players selected in SetupView
     * TODO: Refactor this - have another class to manage/initialize players?
     */
    private void addPlayers(){
        players.add(new ManualPlayer());
        for (int i = 1; i < mySettings.getNumPlayers(); i++){
            players.add(new AI_Player());
        }
    }

    public UnoTurnManager getTurnManager(){
        return turnManager;
    }

    /**
     * Returns the action applier object for use in the WildcardObserver
     * Called from GameView to initialize a WildColorSelectorView
     * @return
     */
    public UnoActionApplier getActionApplier(){
        return actionApplier;
    }

    public boolean isUserTurn(){
        if(currentPlayer == user){
            return true;
        }

        return false;
    }

    /**
     * Sets the new color of the discard pile when a wild card is drawn
     * May be used by controller and view to handle user selection of color
     * //Hi Tess here I removed the color parameter based on how I think it's used by the ActionApplier
     * //@param color represents suit of selected wild card "color"
     */
    public void setWildColor(String color){
        Suit cardColor = Suit.valueOf(color);
        //TODO: change color of the discard pile's top card
        //TODO: add setSuit() method to call something like discPile.showTopCard().setSuit(color);
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
    public void checkUno(){
        if (currentPlayer.hand().getCardCount() == 1 && didCallUno == false){
            for (int i = 0; i < UNO_PENALTY; i++){
                currentPlayer.takeCard(drawPile.drawCard());
            }
        }
    }

    public boolean isOver(){
        return rule.isOver(getTopDiscardCard(), currentPlayer.hand());

    }

}
