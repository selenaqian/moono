package ooga.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EndViewTest extends DukeApplicationTest {
    private EndView endView;

    @Override
    public void start(Stage stage) {
        endView = new EndView(stage);
    }

    @Test
    void endViewElementsTest() {
        sleep(5, TimeUnit.SECONDS);
    }
}