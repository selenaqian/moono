package ooga.view;

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
    void testUpdateHand() {

    }

    @Test
    void testUpdateDiscardPile() {

    }


}