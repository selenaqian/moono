package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ooga.view.SetupView.DEFAULT_CARDS;
import static ooga.view.SetupView.DEFAULT_PLAYERS;
import static org.junit.jupiter.api.Assertions.*;

class GameViewTest extends DukeApplicationTest {
    private GameView gameView;
    private List<Card> cardList;
    private Card discardStart;

    @Override
    public void start(Stage stage) {
        cardList = new ArrayList<>();
        for(int i=0; i<7; i++) {
            cardList.add(new Card(Suit.A, Value.ZERO));
        }
        discardStart = new Card(Suit.A, Value.THREE);
        gameView = new GameView(DEFAULT_PLAYERS, DEFAULT_CARDS, cardList, discardStart, stage);
    }

    @Test
    void testUpdateHandCardsLeft() {
        sleep(5, TimeUnit.SECONDS);
        gameView.updateHand(3, 10);

        assertEquals("10 left", gameView.getAllPlayersCardsLeft().get(3-1).getText());
    }

    /**
     * Tests updating the visual of player 1's hand where cards are actually visible.
     */
    @Test
    void testUpdateHandOneNew() {
        sleep(5, TimeUnit.SECONDS);
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(Suit.B, Value.FIVE));
        javafxRun(() -> gameView.updateHand(newCards));
        sleep(5, TimeUnit.SECONDS);

        HBox hand = gameView.getPlayer1Hand();
        List<CardView> cards = new ArrayList<>();
        for(int i=0; i< hand.getChildren().size(); i++) {
            cards.add((CardView)hand.getChildren().get(i));
        }

        assertEquals(0, new Card(Suit.B, Value.FIVE).compareTo(cards.get(0).getCard()));
        assertTrue(new Rectangle(100, 600/4, Color.BLUE).equals(cards.get(0).getChildren().get(0)));
        assertEquals("5", cards.get(0).getChildren().get(1).toString());
    }

    @Test
    void testUpdateHandMultipleNew() {
        sleep(5, TimeUnit.SECONDS);
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(Suit.B, Value.FIVE));
        for(int i=1; i<6; i++) newCards.add(new Card(Suit.C, Value.NINE));
        javafxRun(() -> gameView.updateHand(newCards));
        sleep(5, TimeUnit.SECONDS);

        HBox hand = gameView.getPlayer1Hand();
        List<CardView> cards = new ArrayList<>();
        for(int i=0; i< hand.getChildren().size(); i++) {
            cards.add((CardView)hand.getChildren().get(i));
        }

        assertEquals(0, new Card(Suit.B, Value.FIVE).compareTo(cards.get(0).getCard()));
        assertTrue(new Rectangle(1000/6-5, 600/4, Color.BLUE).equals(cards.get(0).getChildren().get(0)));
        assertEquals("5", cards.get(0).getChildren().get(1).toString());

        for(int i=0; i< hand.getChildren().size(); i++) {
            assertEquals(0, new Card(Suit.C, Value.NINE).compareTo(cards.get(i).getCard()));
            assertTrue(new Rectangle(1000/6-5, 600/4, Color.GREEN).equals(cards.get(i).getChildren().get(0)));
            assertEquals("9", cards.get(i).getChildren().get(1).toString());
        }
    }

    @Test
    void testUpdateDiscardPile() {

    }


}