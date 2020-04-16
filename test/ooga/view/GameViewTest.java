package ooga.view;

import javafx.scene.layout.HBox;
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
        gameView = new GameView(new Uno(), controller, stage);
    }

    @Test
    void testUpdateHandCardsLeft() {
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> gameView.updateHand(3, 10));
        sleep(5, TimeUnit.SECONDS);

        assertEquals("10 left", gameView.getAllPlayersCardsLeft().get(3-1).getText());
    }

    /**
     * Tests updating the visual of player 1's hand where cards are actually visible.
     */
    @Test
    void testUpdateHandOneNew() {
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

    @Test
    void testUpdateHandMultipleNew() {
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

    @Test
    void testUpdateDiscardPile() {
        javafxRun(() -> gameView.updateDiscardPile(new Card(Suit.D, Value.SEVEN)));
        sleep(2, TimeUnit.SECONDS);

        assertEquals(0, new Card(Suit.D, Value.SEVEN).compareTo(gameView.getDiscardRender().getCard()));
    }


}