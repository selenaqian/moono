package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;

import java.util.ArrayList;
import java.util.List;

import static ooga.view.SetupView.*;

public class GameView implements GameViewInterface {
    public static final int SPACING_BETWEEN_CARDS = 5;
    private Stage mainStage;
    private List<Pane> playerViews;
    private Pane mainPane;
    private List<Text> allPlayersCardsLeft; // stores text objects for all players in order that state how many cards that player has left
    private HBox player1Hand;
    private CardView discardView;
    private Rectangle deckView;

    public GameView() {
        this(DEFAULT_PLAYERS, DEFAULT_CARDS, new ArrayList<>(), new Card(Suit.A, Value.ZERO), new Stage());
    }

    public GameView(int numPlayers, int startCards, List<Card> player1Cards, Card discardFirst, Stage stage) {
        mainStage = stage;
        mainPane = new AnchorPane();
        allPlayersCardsLeft = new ArrayList<>();
        Scene mainScene = new Scene(mainPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        mainScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(mainScene);
        mainStage.show();

        playerViews = makePlayerStatuses(numPlayers, startCards);
        positionPlayerViews();
        updateHand(player1Cards);

        HBox decks = new HBox(DEFAULT_SPACING);
        deckView = new Rectangle(mainPane.getWidth()/7, mainPane.getHeight()/3);
        discardView = new CardView(discardFirst, mainPane.getWidth()/7, mainPane.getHeight()/3);
        decks.getChildren().addAll(deckView, discardView);
        mainPane.getChildren().add(decks);
        AnchorPane.setBottomAnchor(decks,mainPane.getHeight()/2);
        AnchorPane.setLeftAnchor(decks, mainPane.getWidth()/2 - deckView.getWidth());
        // TODO: still need render the deck and discard piles in center of pane

        // TODO: initialize and use properties file for text
    }

    private void positionPlayerViews() {
        positionPlayer1();

        VBox allPlayersNot1 = new VBox(10);
        for(int i=1; i<playerViews.size(); i++) {
            allPlayersNot1.getChildren().add(playerViews.get(i));
        }
        mainPane.getChildren().add(allPlayersNot1);
        AnchorPane.setRightAnchor(allPlayersNot1, 10.0);
        AnchorPane.setBottomAnchor(allPlayersNot1, mainStage.getHeight()/2);
    }

    private void positionPlayer1() {
        mainPane.getChildren().add(playerViews.get(0));
        AnchorPane.setBottomAnchor(playerViews.get(0), 0.0);
    }

    private List<Pane> makePlayerStatuses(int numPlayers, int startCards) {
        List<Pane> playersList = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            HBox playerBox = new HBox();
            VBox textBox = new VBox();

            Text playerNumber = new Text("Player " + (i+1));
            Text cardsLeft = new Text(startCards + " left");
            allPlayersCardsLeft.add(cardsLeft);
            textBox.getChildren().addAll(playerNumber, cardsLeft);

            Circle playerIcon = new Circle(10);
            playerBox.getChildren().addAll(playerIcon, textBox);

            playersList.add(playerBox);
        }

        return playersList;
    }

    @Override
    public void updateHand(List<Card> cards) {
        player1Hand = new HBox(SPACING_BETWEEN_CARDS);
        for (Card c : cards) {
            player1Hand.getChildren().add(new CardView(c, mainStage.getWidth()/cards.size() - SPACING_BETWEEN_CARDS, mainStage.getHeight()/4));
        }
        VBox player1Box = new VBox();
        StackPane player1Base = new StackPane();
        Rectangle player1Mat = new Rectangle(mainStage.getWidth(), mainStage.getHeight()/4, Color.WHITE);
        player1Base.getChildren().addAll(player1Mat, player1Hand);

        player1Box.getChildren().addAll(playerViews.get(0), player1Base);
        player1Box.setAlignment(Pos.CENTER); //TODO: alignment doesn't seem to be working
        playerViews.remove(0);
        playerViews.add(0, player1Box);
        positionPlayer1();
    }

    @Override
    public void updateHand(int playerNumber, int cardsLeft) {
        allPlayersCardsLeft.remove(allPlayersCardsLeft.get(playerNumber - 1));
        allPlayersCardsLeft.add(playerNumber - 1, new Text(cardsLeft + " left"));
    }

    @Override
    public void updateDiscardPile(Card card) {
        // replace the rendering of the current discard with a new one
    }

    // Methods below primarily used for testing - to get objects and check their displayed values.

    /**
     * Used for testing. Allows test to access the list of text objects that state how many cards each player has left.
     * @return the list of text objects where the text object at a certain index corresponds to the text display to player number index+1.
     */
    public List<Text> getAllPlayersCardsLeft() {
        return allPlayersCardsLeft;
    }
}
