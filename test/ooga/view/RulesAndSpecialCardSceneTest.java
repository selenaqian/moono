package ooga.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class RulesAndSpecialCardSceneTest extends DukeApplicationTest {
    private SetupView startView;

    @Override
    public void start(Stage stage) {
        startView = new SetupView(stage);
        clickOn(startView.getWelcomeOkButton()); // advances to the rules and special cards selection scene
    }

    @Test
    void defaultRulesSelectionsTest() {

    }

    @Test
    void deadlyRulesTest() {

    }

    @Test
    void ascendingRulesTest() {

    }

    @Test
    void noSpecialCardsTest() {

    }

    @Test
    void oneSpecialCardTest() {

    }

    @Test
    void multipleSpecialCardsTest() {

    }

    @Test
    void allSpecialCardsTest() {

    }
}