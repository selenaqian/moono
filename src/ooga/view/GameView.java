/**
 * Class that builds the main gameplay scenes and gets user input to play and draw cards.
 *
 * @author Selena Qian
 */
package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.game.GameSettings;
import ooga.game.PlayerObserver;
import ooga.game.Uno;
import ooga.game.UnoController;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ooga.view.SetupView.*;

public class GameView implements GameViewInterface, PlayerObserver {
    public static final int SPACING_BETWEEN_CARDS = 5;
    private Stage mainStage;
    private List<Pane> playerViews;
    private Pane player1Label; // store the user label separate from the others as well
    private Pane mainPane;
    private List<Text> allPlayersCardsLeft; // stores text objects for all players in order that state how many cards that player has left
    private HBox player1Hand; // store the card nodes for the user's hand
    private CardView discardView;
    private Rectangle deckView;
    private HBox decks; // stores the view of both decks together
    private Uno myUno;
    private UnoController myController;
    private GameSettings mySettings;
    private VBox allPlayersNot1;
    private Button nextTurn;
    private ResourceBundle myResources;

    public GameView() {
        this(new Uno(), new UnoController(new Stage()), new Stage());
    }

    /**
     * Constructor for GameView. Sets the instance variables and initializes the scene with the images.
     * @param uno the Uno object passed to this class to allow for getting player hands.
     * @param controller the UnoController object passed to this class to allow for calling methods when a button or card is clicked.
     * @param stage the Stage passed to this class to keep everything loaded into one window.
     */
    public GameView(Uno uno, UnoController controller, Stage stage) {
        myUno = uno;
        myController = controller;
        mySettings = uno.getSettings();
        mainStage = stage;
        mainPane = new AnchorPane();
        allPlayersCardsLeft = new ArrayList<>();
        allPlayersNot1 = new VBox(DEFAULT_SPACING);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);

        //registering observer(s)
        myUno.registerPlayerObserver(this);

        Scene mainScene = new Scene(mainPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        mainScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(mainScene);
        mainStage.show();
        initializeGameScene();

    }

    /**
     * Helper method for creating the initial view for the game.
     */
    private void initializeGameScene() {
        int numPlayers = mySettings.getNumPlayers();
        int startCards = mySettings.getHandSize();

        playerViews = makePlayerStatuses(numPlayers, startCards);
        positionPlayer1();
        positionPlayersNot1();

        decks = new HBox(DEFAULT_SPACING);
        deckView = new Rectangle(mainPane.getWidth()/7, mainPane.getHeight()/3);
        deckView.setOnMouseClicked(e -> myController.handleDrawPileClick());
        discardView = new CardView(new Card(Suit.A, Value.ZERO), mainPane.getWidth()/7, mainPane.getHeight()/3);
        decks.getChildren().addAll(deckView, discardView);
        mainPane.getChildren().add(decks);
        AnchorPane.setTopAnchor(decks,mainPane.getHeight()/4);
        AnchorPane.setLeftAnchor(decks, mainPane.getWidth()/2 - deckView.getWidth());

        nextTurn = new Button(myResources.getString("next"));
        nextTurn.setOnMouseClicked(e -> myController.handleAIPlay());
        mainPane.getChildren().add(nextTurn);
        AnchorPane.setTopAnchor(nextTurn, 10.0);
        AnchorPane.setRightAnchor(nextTurn, 10.0);

        updateHand(myUno.getUserHand());
    }

    /**
     * Helper method to position the player1 objects in the proper place relative to the scene.
     */
    private void positionPlayer1() {
        mainPane.getChildren().add(playerViews.get(0));
        AnchorPane.setBottomAnchor(playerViews.get(0), 0.0);
    }

    /**
     * Helper method to position other player display objects in the scene.
     */
    private void positionPlayersNot1() {
        setBoxAllPlayersNot1();
        mainPane.getChildren().add(allPlayersNot1);
        AnchorPane.setRightAnchor(allPlayersNot1, 10.0);
        AnchorPane.setTopAnchor(allPlayersNot1, mainStage.getHeight()/4);
    }

    /**
     * Helper method to put all of the non-player 1 player displays into a box together that can more easily be placed in the scene.
     */
    private void setBoxAllPlayersNot1() {
        allPlayersNot1.getChildren().removeAll();
        for(int i=1; i<playerViews.size(); i++) {
            allPlayersNot1.getChildren().add(playerViews.get(i));
        }
    }

    /**
     * Helper method to create all of the player displays.
     * @param numPlayers the number of players in the game.
     * @param startCards the number of cards the player starts with.
     * @return a list with elements that are each of the player displays - player1 at element 0, etc.
     */
    private List<Pane> makePlayerStatuses(int numPlayers, int startCards) {
        List<Pane> playersList = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            HBox playerBox = makePlayerInfoDisplay(i+1, startCards);
            playersList.add(playerBox);
        }
        player1Label = playersList.get(0);
        return playersList;
    }

    /**
     * Helper method to create the player displays - actually creates the circle (for image?) and text objects displaying name and cards left.
     * @param playerNumber the player number, used to create name for now.
     * @param numberCards the number of cards the player has.
     * @return an HBox containing the display info.
     */
    private HBox makePlayerInfoDisplay(int playerNumber, int numberCards) {
        HBox playerBox = new HBox();
        VBox textBox = new VBox();

        Text playerNumberText = new Text(myResources.getString("player") + playerNumber);
        Text cardsLeft = new Text(numberCards + myResources.getString("cardsLeft"));
        if (allPlayersCardsLeft.size() >= playerNumber) allPlayersCardsLeft.remove(playerNumber-1);
        allPlayersCardsLeft.add(playerNumber-1, cardsLeft);
        textBox.getChildren().addAll(playerNumberText, cardsLeft);

        Circle playerIcon = new Circle(10);
        playerBox.getChildren().addAll(playerIcon, textBox);
        return playerBox;
    }

    /**
     * Changes coloring of the text and circle to indicate which player's turn it is.
     * Called in the game package.
     * @param playerNumber the number of the player whose turn it now is.
     */
    public void myTurnColorChange(int playerNumber) {
        player1Label.getStyleClass().removeAll();
        for(int i=1; i<playerViews.size(); i++) {
            playerViews.get(i).getStyleClass().removeAll();
        }
        if(playerNumber==1) player1Label.getStyleClass().add("myTurn");
        else playerViews.get(playerNumber-1).getStyleClass().add("myTurn");
    }

    @Override
    public void updateHand(List<Card> cards) {
        player1Hand = new HBox(SPACING_BETWEEN_CARDS);
        for (Card c : cards) {
            CardView tempCardView = new CardView(c, Math.min(mainStage.getWidth()/cards.size() - SPACING_BETWEEN_CARDS, mainStage.getWidth()/10), mainStage.getHeight()/4);
            player1Hand.getChildren().add(tempCardView);
            tempCardView.setOnMouseClicked(e -> myController.handleCardClick(tempCardView.getCard()));
        }
        VBox player1Box = new VBox();
        StackPane player1Base = new StackPane();
        Rectangle player1Mat = new Rectangle(mainStage.getWidth(), mainStage.getHeight()/4, Color.WHITE);
        player1Base.getChildren().addAll(player1Mat, player1Hand);

        player1Box.getChildren().addAll(player1Label, player1Base);
        allPlayersCardsLeft.get(0).setText(cards.size() + myResources.getString("cardsLeft"));
        player1Box.setAlignment(Pos.CENTER); //TODO: alignment doesn't seem to be working
        mainPane.getChildren().remove(playerViews.get(0));
        playerViews.remove(0);
        playerViews.add(0, player1Box);
        positionPlayer1();
    }

    @Override
    public void updateHand(int playerNumber, int cardsLeft) {
        allPlayersCardsLeft.get(playerNumber).setText(cardsLeft + myResources.getString("cardsLeft"));
    }

    @Override
    public void updateDiscardPile(Card card) {
        decks.getChildren().remove(discardView);
        discardView = new CardView(card, discardView.getWidth(), discardView.getHeight());
        decks.getChildren().add(discardView);
    }

    // Methods below primarily used for testing - to get objects and check their displayed values.

    /**
     * Used for testing. Allows test to access the list of text objects that state how many cards each player has left.
     * @return the list of text objects where the text object at a certain index corresponds to the text display to player number index+1.
     */
    public List<Text> getAllPlayersCardsLeft() {
        return allPlayersCardsLeft;
    }

    /**
     * Used for testing. Allows test to access the box containing CardView objects for comparison to expected values.
     * @return the HBox with children CardView nodes representing player 1's hand.
     */
    public HBox getPlayer1Hand() {
        return player1Hand;
    }

    /**
     * Used for testing. Allows test to access the CardView of the discardPile for comparison to expected values.
     * @return the CardView object representing the discard pile.
     */
    public CardView getDiscardView() {
        return discardView;
    }


    //Testing code for observers
    @Override
    public void updatePlayerHand(int playerId, int cardsLeft) {
        allPlayersCardsLeft.get(playerId).setText(cardsLeft + myResources.getString("cardsLeft"));

    }
}
