package ooga.view;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.cards.Suit;
import ooga.game.Uno;
import ooga.game.UnoActionApplier;
import ooga.game.UnoTurnManager;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;

import static ooga.view.SetupView.DEFAULT_STYLESHEET;
import static org.junit.jupiter.api.Assertions.*;

class WildColorSelectorViewTest extends DukeApplicationTest {
    private WildColorSelectorView wildColorSelector;
    private UnoActionApplier myActionApplier;

    @Override
    public void start(Stage stage) {
        myActionApplier = new UnoActionApplier(new Uno(), new UnoTurnManager());
        wildColorSelector = new WildColorSelectorView(myActionApplier, DEFAULT_STYLESHEET);
        wildColorSelector.showColorSelector();
    }

    @Test
    void showColorSelectorTestElements() {
        List<Rectangle> colorBoxes = wildColorSelector.getColorBoxes();

        for (Rectangle r : colorBoxes) {
            assertEquals(100, r.getHeight());
            assertEquals(100, r.getWidth());
        }

        assertEquals("A", colorBoxes.get(0).getStyleClass().get(0));
        assertEquals("B", colorBoxes.get(1).getStyleClass().get(0));
        assertEquals("C", colorBoxes.get(2).getStyleClass().get(0));
        assertEquals("D", colorBoxes.get(3).getStyleClass().get(0));
    }
}