package ooga.game;

import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.piles.Pile;
import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import ooga.player.Player;
import ooga.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.Stack;

import static ooga.view.SetupView.DEFAULT_STYLESHEET;
import static org.junit.jupiter.api.Assertions.*;

class UnoTest extends DukeApplicationTest {

    UnoController controller;
    Uno uno;
    private UnoTurnManager turnManager;
    private PileManager pileManager;
    private GameSettings gameSettings;
    private ArrayList<Player> players;

    @Override
    public void start(Stage stage) {
        turnManager = new UnoTurnManager();
        turnManager.addPlayers(4);
        turnManager.setCurrentPlayer(turnManager.getAllPlayers().get(1));
        gameSettings = new GameSettings();
        pileManager = new PileManager();
        uno = new Uno(gameSettings, pileManager, turnManager);
        controller = new UnoController(stage, uno);
        setUpPiles();
    }


    void setUpPiles(){
        Stack<Card> cards = new Stack<>();
        cards.add(new Card(Suit.A, Value.ONE));
        cards.add(new Card(Suit.A, Value.ONE));
        cards.add(new Card(Suit.A, Value.ONE));
        DrawPile drawPile = new DrawPile(cards);
        uno.getPileManager().setDrawPile(drawPile);
    }

    void setUpPlayerHands(){
        for (Player p : turnManager.getAllPlayers()){
            p.reset();
            p.hand().addCard(new Card(Suit.B, Value.THREE));
        }
    }

    @Test
    void testEmptyDrawPile(){
        setUpPlayerHands();
        DiscardPile discPile = new DiscardPile();
        uno.getPileManager().setDiscPile(discPile);
        uno.getPileManager().getDiscPile().addCard(uno.getPileManager().drawCard());
        assertEquals(2, pileManager.getDrawPile().getCardCount());

        controller.handleAIPlay();
        uno.notifyPlayerObservers();
        assertEquals(1, pileManager.getDrawPile().getCardCount());

        controller.handleAIPlay();
        uno.notifyPlayerObservers();
        assertEquals(0, pileManager.getDrawPile().getCardCount());

    }
}