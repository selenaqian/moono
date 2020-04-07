//package ooga.game;
//
//import ooga.cards.Card;
//import ooga.piles.DiscardPile;
//import ooga.piles.DrawPile;
//import ooga.piles.Hand;
//import ooga.player.Player;
//import ooga.rules.Rule;
//
///**
// * Controller class for handling play of Uno
// * Equivalent to GamePlay interface from planning
// */
//public class Uno {
//
//    private View view;
//
//    private UnoTurnManager turnManager;
//    private Player currentPlayer;
//
//    private DiscardPile discPile;
//    private DrawPile drawPile;
//
//    private Rule rule;
//
//    public Uno(){
//        //initialize connection to view
//        this.view = new View();
//        turnManager = new UnoTurnManager();
//        currentPlayer = turnManager.getCurrentPlayer();
//
//        discPile = new DiscardPile();
//        drawPile = new DrawPile();
//    }
//
//    public void setRules(Rule rule){
//        this.rule = rule;
//    }
//
//    /**
//     * Handle behavior when a user selects a card to play it
//     * Called from the view
//     * @param selectedCard the card selected by the player in the view or selected by the AI player
//     */
//    public void playCard(Card selectedCard){
//        //check if played card can be played on top of the discard pile top card
//        if (rule.isValid(discPile.showTopCard(), selectedCard)) {
//
//            //make sure player updates their hand to remove the card
//            currentPlayer.playCard(selectedCard);
//
//            //update the discard pile to add the card
//            discPile.addCard(selectedCard); //TODO: make sure addCard adds to the top of the pile?
//        }
//
//        //TODO: make call to handleAction() once special cards are implemented
//
//        //end current player's turn and go to next player
//        turnManager.nextPlayer();
//    }
//
//    /**
//     * When a user isn't able to play a card and they take a card from the draw pile
//     * Called from the view for human players
//     * For AI players, must be called programmatically
//     */
//    public void drawCard(){
//        //take the top card from the discard pile, make sure it is removed from the discard pile
//        Card card = discPile.popTopCard();
//
//        //get player to accept the drawn card into their own hand of cards
//        currentPlayer.takeCard(card);
//
//        //end current player's turn and go to next player
//        turnManager.nextPlayer();
//    }
//
//    /**
//     * Handle effect of an action card when it played
//     * This method should have access to TurnManager, especially for Reverse cards
//     * TODO: put action methods in a resource file and use this method to call them
//     */
//    public void handleAction(){
//
//    }
//
//    /**
//     * Allows the human player to declare uno
//     * Called from view class when the "uno" button is clicked
//     */
//    public void callUno(){
//
//    }
//
//    /**
//     * Once a player has no cards left, check if they have declared uno
//     * If a player has not declared, then they must pick up more cards
//     */
//    public void checkUno(){
//
//    }
//
//}
