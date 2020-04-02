/**
 * Use case of moving turn between players once a player has made a play
 * UseCase2 class represents the Game class, which is respsonsible for playing Uno
 */

public class UseCase2 implements GamePlay {

    //players will be initialized in another method to add players based on number selected by user
    private List<Player> players;
    private TurnManager turnManager;

    private currentPlayer();

    public UseCase2(){
        // //The "API" demonstrated by this use case is TurnManager, which determines which player has the turn
        turnManager =  = new TurnManager(Players);

        //Constructor for the game class sets who is the first player, using the TurnManager API
        currentPlayer = turnManager.getFirstPlayer();
    }

    public void play(){
        currentPlayer.playCard();
        currentPlayer = turnManager.getNextPlayer();
        updateDecks();
    }

    private void updateDecks(){
        //contains calls to Pile and Hand APIs to update the cards played
    }
}