/**
 * Class that builds the main gameplay scenes and gets user input to play and draw cards.
 *
 * @author Selena Qian
 */
package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private Scene mainScene;
    private List<Pane> playerViews;
    private Pane player1Label; // store the user label separate from the others as well
    private Pane mainPane;
    private List<Text> allPlayersCardsLeft; // stores text objects for all players in order that state how many cards that player has left, used to easily update
    private List<Text> allPlayersScore; // similar function to allPlayersCardsLeft but for the score
    private HBox player1Hand; // store the card nodes for the user's hand
    private CardRender discardRender;
    private Region deckView;
    private HBox decks; // stores the view of both decks together
    private Uno myUno;
    private UnoController myController;
    private GameSettings mySettings;
    private VBox allPlayersNot1;
    private Button settingsButton;
    private ResourceBundle myResources;
    private WildColorSelectorView wildColorSelector;
    private String myStylesheet;
    private Button callUno;

    public GameView() {
        this(new Uno(), new UnoController(new Stage()), new Stage(), DEFAULT_STYLESHEET);
        myUno.start();
    }

    /**
     * Constructor for GameView. Sets the instance variables and initializes the scene with the images.
     * @param uno the Uno object passed to this class to allow for getting player hands.
     * @param controller the UnoController object passed to this class to allow for calling methods when a button or card is clicked.
     * @param stage the Stage passed to this class to keep everything loaded into one window.
     */
    public GameView(Uno uno, UnoController controller, Stage stage, String stylesheet) {
        myUno = uno;
        myController = controller;
        mySettings = uno.getSettings();
        mainStage = stage;
        mainPane = new AnchorPane();
        allPlayersCardsLeft = new ArrayList<>();
        allPlayersScore = new ArrayList<>();
        allPlayersNot1 = new VBox(DEFAULT_SPACING);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        myStylesheet = stylesheet;
        wildColorSelector = new WildColorSelectorView(myUno.getActionApplier(), myStylesheet);

        //registering observer(s)
        myUno.registerPlayerObserver(this);

        mainScene = new Scene(mainPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        mainScene.getStylesheets().add(myStylesheet);
        initializeGameScene();
    }

    public void showGameScene() {
        mainStage.setScene(mainScene);
        mainStage.show();
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

        makeDecks();
        makeUnoButton();
        setLocationDecksAndCallUno();

        settingsButton = new Button(myResources.getString("settingsButton"));
        settingsButton = new Button(myResources.getString("settingsButton"));
        settingsButton.setOnMouseClicked(e -> {
            myController.pause();
            new SettingsView(myStylesheet, this, mySettings);
        });
        mainPane.getChildren().add(settingsButton);
        AnchorPane.setTopAnchor(settingsButton, 10.0);
        AnchorPane.setRightAnchor(settingsButton, 10.0);

        updateHand(myUno.getUserHand());
    }

    /**
     * Helper method that groups the decks and callUno button together and places them in the center of the scene.
     */
    private void setLocationDecksAndCallUno() {
        VBox decksAndCallUno = new VBox(DEFAULT_SPACING);
        decksAndCallUno.setAlignment(Pos.CENTER);
        decksAndCallUno.getChildren().addAll(decks, callUno);
        mainPane.getChildren().add(decksAndCallUno);
        AnchorPane.setTopAnchor(decksAndCallUno,mainPane.getHeight()/4);
        AnchorPane.setRightAnchor(decksAndCallUno, mainPane.getWidth()/3);
    }

    /**
     * Helper method to create the button that allows the user to call uno.
     */
    private void makeUnoButton() {
        callUno = new Button(myResources.getString("callUno"));
        callUno.setAlignment(Pos.CENTER);
        callUno.setOnMouseClicked(e -> myController.callUno());
    }

    /**
     * Helper method to create the visuals for the discard pile and the draw pile.
     */
    private void makeDecks() {
        decks = new HBox(DEFAULT_SPACING);
        deckView = new Region();
        deckView.setMaxHeight(mainPane.getHeight()/3);
        deckView.setMinHeight(mainPane.getHeight()/3);
        deckView.setMaxWidth(mainPane.getWidth()/7);
        deckView.setMinWidth(mainPane.getWidth()/7);
        deckView.getStyleClass().add("deck");
        deckView.setOnMouseClicked(e -> myController.handleDrawPileClick());
        discardRender = new CardRender(new Card(Suit.A, Value.ZERO), mainPane.getWidth()/7, mainPane.getHeight()/3);
        decks.getChildren().addAll(deckView, discardRender);
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
        Text score = new Text(myResources.getString("score") + 0);
        playerNumberText.getStyleClass().add("playerText");
        cardsLeft.getStyleClass().add("playerText");
        score.getStyleClass().add("playerText");

        if (allPlayersCardsLeft.size() >= playerNumber) {
            allPlayersCardsLeft.remove(playerNumber-1);
            allPlayersScore.remove(playerNumber-1);
        }
        allPlayersCardsLeft.add(playerNumber-1, cardsLeft);
        allPlayersScore.add(playerNumber-1, score);
        textBox.getChildren().addAll(playerNumberText, cardsLeft, score);

        Circle playerIcon = new Circle(10);
        playerIcon.getStyleClass().add("playerText");
        playerBox.getChildren().addAll(playerIcon, textBox);
        return playerBox;
    }

    /**
     * Changes coloring of the text and circle to indicate which player's turn it is.
     * Called in the game package.
     * @param playerNumber the number of the player whose turn it now is.
     */
    public void myTurnColorChange(int playerNumber) {
        Node player1Circle = player1Label.getChildren().get(0); // the circle
        resetStyle(player1Circle, "playerText");
        VBox player1AllText = (VBox)player1Label.getChildren().get(1);
        for(Node n : player1AllText.getChildren()) {
            resetStyle(n, "playerText");
        }
        for(int i=1; i<playerViews.size(); i++) {
            Node playerCircle = playerViews.get(i).getChildren().get(0);
            resetStyle(playerCircle, "playerText");
            VBox playerAllText = (VBox)playerViews.get(i).getChildren().get(1);
            for(Node n : playerAllText.getChildren()) {
                resetStyle(n, "playerText");
            }
        }
        if(playerNumber == 1) {
            player1Circle.getStyleClass().add("myTurn");
            for(Node n : player1AllText.getChildren()) {
                n.getStyleClass().add("myTurn");
            }
            return;
        }
        Node playerNumberCircle = playerViews.get(playerNumber-1).getChildren().get(0);
        playerNumberCircle.getStyleClass().add("myTurn");
        VBox playerNumberAllText = (VBox)playerViews.get(playerNumber-1).getChildren().get(1);
        for(Node n : playerNumberAllText.getChildren()) {
            n.getStyleClass().add("myTurn");
        }
    }

    private void resetStyle(Node currentNode, String newStyle) {
        currentNode.getStyleClass().removeAll(currentNode.getStyleClass());
        currentNode.getStyleClass().add(newStyle);
    }

    @Override
    public void updateHand(List<Card> cards) {
        player1Hand = new HBox(SPACING_BETWEEN_CARDS);
        for (Card c : cards) {
            CardRender tempCardRender = new CardRender(c, Math.min(mainStage.getWidth()/cards.size() - SPACING_BETWEEN_CARDS, mainStage.getWidth()/10), mainStage.getHeight()/4);
            player1Hand.getChildren().add(tempCardRender);
            tempCardRender.setOnMouseClicked(e -> {
                //if wild card then need call wildcolor.show
                if(tempCardRender.getCard().getValue()==Value.WILD) wildColorSelector.showColorSelector();
                else myController.handleCardClick(tempCardRender.getCard());
            });
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
        allPlayersCardsLeft.get(playerNumber-1).setText(cardsLeft + myResources.getString("cardsLeft"));
    }


    //this is now overriding a method in PlayerObserver
    @Override
    public void updateDiscardPile(Card card) {
        discardRender.updateCardRender(card);
    }

    @Override
    public void updateScore(int playerNumber, int score) {
        Text scoreDisplay = allPlayersScore.get(playerNumber-1);
        scoreDisplay.setText(myResources.getString("score") + score);
    }

    /**
     * Method to change the game theme mid-game. Only called within the view package, so method is package-private.
     * @param theme the name of the desired theme.
     */
    void setTheme(String theme) {
        mainScene.getStylesheets().removeAll(mainScene.getStylesheets());
        mainScene.getStylesheets().add(theme);
        mySettings.setTheme(theme);
        wildColorSelector.setTheme(theme);
    }

    /**
     * Allows other view classes to access the controller stored by the GameView object. Used by SettingsView to create the LoadView.
     * @return the controller stored by this class.
     */
    UnoController getController() {
        return myController;
    }

    /**
     * Allows other view classes to access the stage this GameView is using. Used by SettingsView to send info to MidGameSaveNew
     * about where to overwrite the game if desired.
     * @return the stage stored by this class.
     */
    Stage getStage() {
        return mainStage;
    }

    // Methods below primarily used for testing - to get objects and check their displayed values.

    /**
     * Used for testing. Allows test to access the list of text objects that state how many cards each player has left.
     * @return the list of text objects where the text object at a certain index corresponds to the text display of player number index+1.
     */
    public List<Text> getAllPlayersCardsLeft() {
        return allPlayersCardsLeft;
    }

    /**
     * Used for testing. Allows test to access the list of text objects that state each players' score.
     * @return the list of text objects where the text object at a certain index corresponds to the text display of player number index+1.
     */
    public List<Text> getAllPlayersScore() {
        return allPlayersScore;
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
    public CardRender getDiscardRender() {
        return discardRender;
    }

    public List<Pane> getPlayerViews() {
        List<Pane> allViews = playerViews;
        allViews.remove(0);
        allViews.add(0, player1Label);
        return allViews;
    }

    //Testing code for observers
    @Override
    public void updatePlayerHand(int playerId, List<Card> cardsLeft) {
        allPlayersCardsLeft.get(playerId-1).setText(cardsLeft.size() + myResources.getString("cardsLeft"));
        if(playerId == 1) updateHand(cardsLeft);
        //TODO: check playerID and call appropriate methods to handle view updates for the user and other players
    }

}
