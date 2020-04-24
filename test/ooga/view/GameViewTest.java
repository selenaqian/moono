package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.game.Uno;
import ooga.game.UnoController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ooga.view.SetupView.DEFAULT_STYLESHEET;
import static org.junit.jupiter.api.Assertions.*;

class GameViewTest extends DukeApplicationTest {
    private GameView gameView;
    private List<Card> cardList;
    private UnoController controller;

    @Override
    public void start(Stage stage) {
        controller = new UnoController(stage);
        cardList = new ArrayList<>();
        for(int i=0; i<7; i++) {
            cardList.add(new Card(Suit.A, Value.ZERO));
        }
        gameView = new GameView(new Uno(), controller, stage, DEFAULT_STYLESHEET);
    }

    /**
     * Tests updating the visual of AI players' hands where cards aren't visible but the number of cards left is.
     */
    @Test
    void updateHandCardsLeftTest() {
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> gameView.updateHand(3, 10));
        sleep(5, TimeUnit.SECONDS);

        assertEquals("10 cards left", gameView.getAllPlayersCardsLeft().get(3-1).getText());
    }

    /**
     * Tests updating the visual of player 1's hand where cards are actually visible.
     */
    @Test
    void UpdateHandOneNewCardTest() {
        sleep(2, TimeUnit.SECONDS);
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(Suit.B, Value.FIVE));
        javafxRun(() -> gameView.updateHand(newCards));
        sleep(2, TimeUnit.SECONDS);

        HBox hand = gameView.getPlayer1Hand();
        List<CardRender> cards = new ArrayList<>();
        for(int i=0; i< hand.getChildren().size(); i++) {
            cards.add((CardRender)hand.getChildren().get(i));
        }

        assertEquals(0, new Card(Suit.B, Value.FIVE).compareTo(cards.get(0).getCard()));
        assertEquals("5", ((Text)cards.get(0).getChildren().get(1)).getText());
        // will check rectangle appearance in CardViewTest
    }

    /**
     * Tests the creation of the visual of a hand for human player with multiple cards in it.
     */
    @Test
    void updateHandMultipleNewCardsTest() {
        sleep(2, TimeUnit.SECONDS);
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(Suit.B, Value.FIVE));
        for(int i=0; i<5; i++) newCards.add(new Card(Suit.C, Value.NINE));
        javafxRun(() -> gameView.updateHand(newCards));
        sleep(2, TimeUnit.SECONDS);

        HBox hand = gameView.getPlayer1Hand();
        List<CardRender> cards = new ArrayList<>();
        for(int i=0; i< hand.getChildren().size(); i++) {
            cards.add((CardRender)hand.getChildren().get(i));
        }

        assertEquals(0, new Card(Suit.B, Value.FIVE).compareTo(cards.get(0).getCard()));
        assertEquals("5", ((Text)cards.get(0).getChildren().get(1)).getText());

        for(int i=0; i< hand.getChildren().size()-1; i++) {
            assertEquals(0, new Card(Suit.C, Value.NINE).compareTo(cards.get(i+1).getCard()));
            assertEquals("9", ((Text)cards.get(i+1).getChildren().get(1)).getText());
        }
    }

    /**
     * Tests updating the visual of the discard pile which gets called when any player plays a card.
     */
    @Test
    void updateDiscardPileTest() {
        javafxRun(() -> gameView.updateDiscardPile(new Card(Suit.D, Value.SEVEN)));
        sleep(2, TimeUnit.SECONDS);

        assertEquals(0, new Card(Suit.D, Value.SEVEN).compareTo(gameView.getDiscardRender().getCard()));
    }

    /**
     * Tests updating the visual display of the score.
     */
    @Test
    void updateScoreTest() {
        assertEquals("score: 0", gameView.getAllPlayersScore().get(0).getText());
        javafxRun(() -> gameView.updateScore(1, 10));

        assertEquals("score: 10", gameView.getAllPlayersScore().get(0).getText());
    }

    /**
     * Tests that properly change colors for player 1's turn.
     */
    @Test
    void player1ColorChangeOnTurnTest() {
        gameView.myTurnColorChange(1);
        Pane player1Pane = gameView.getPlayerViews().get(0);

        assertEquals("myTurn", player1Pane.getChildren().get(0).getStyleClass().get(1));
        VBox player1AllText = (VBox)player1Pane.getChildren().get(1);
        for(Node current : player1AllText.getChildren()) {
            assertEquals("myTurn", current.getStyleClass().get(1));
        }

        for(int i=1; i <= gameView.getPlayerViews().size(); i++) {
            Pane currentPane = gameView.getPlayerViews().get(i-1);
            assertEquals("playerText", currentPane.getChildren().get(0).getStyleClass().get(0));
            VBox currentAllText = (VBox)currentPane.getChildren().get(1);
            for(Node current : currentAllText.getChildren()) {
                assertEquals("playerText", current.getStyleClass().get(0));
            }
        }
    }

    /**
     * Tests that properly change colors for players other than player 1 on their turn.
     */
    @Test
    void otherPlayerColorChangeOnTurnTest() {
        gameView.myTurnColorChange(2);
        Pane player2Pane = gameView.getPlayerViews().get(1);

        assertEquals("myTurn", player2Pane.getChildren().get(0).getStyleClass().get(1));
        VBox player2AllText = (VBox)player2Pane.getChildren().get(1);
        for(Node current : player2AllText.getChildren()) {
            assertEquals("myTurn", current.getStyleClass().get(1));
        }

        for(int i=1; i <= gameView.getPlayerViews().size(); i++) {
            Pane currentPane = gameView.getPlayerViews().get(i-1);
            assertEquals("playerText", currentPane.getChildren().get(0).getStyleClass().get(0));
            VBox currentAllText = (VBox)currentPane.getChildren().get(1);
            for(Node current : currentAllText.getChildren()) {
                assertEquals("playerText", current.getStyleClass().get(0));
            }
        }
    }
}