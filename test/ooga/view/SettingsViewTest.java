package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SettingsViewTest extends DukeApplicationTest {
    private SettingsView mySettingsView;
    private Button speedUp;
    private Button slowDown;
    private Map<String, Pane> themeOptions;
    private Button save;
    private Button newGame;
    private MidGameSaveNew saveNew;

    @Override
    public void start(Stage stage) {
        mySettingsView = new SettingsView();
        speedUp = lookup("speed up").queryButton();
        slowDown = lookup("slow down").queryButton();
        themeOptions = mySettingsView.getThemeOptions();
        save = lookup("save current game").queryButton();
        newGame = lookup("new game").queryButton();
        saveNew = mySettingsView.getSaveNew();
    }

    /**
     * Tests that clicking speed up once works - changes setting and visual.
     */
    @Test
    void speedUpTest1() {
        javafxRun(() -> clickOn(speedUp));
        sleep(2, TimeUnit.SECONDS);
        assertNotNull(lookup("1.0 s").query());
    }

    /**
     * Tests that clicking speed up twice works - and doesn't go below 1.0 s
     */
    @Test
    void speedUpTest2() {
        javafxRun(() -> clickOn(speedUp));
        javafxRun(() -> clickOn(speedUp));

        assertNotNull(lookup("1.0 s").query());
        assertEquals(1.0, mySettingsView.getSettings().getSpeed());
    }

    /**
     * Tests that slow down once works.
     */
    @Test
    void slowDownTest1() {
        javafxRun(() -> clickOn(slowDown));
        assertNotNull(lookup("2.0 s").query());
        assertEquals(2.0, mySettingsView.getSettings().getSpeed());
    }

    /**
     * Tests that slow down twice works.
     */
    @Test
    void slowDownTest2() {
        javafxRun(() -> clickOn(slowDown));
        javafxRun(() -> clickOn(slowDown));

        assertNotNull(lookup("2.5 s").query());
        assertEquals(2.5, mySettingsView.getSettings().getSpeed());
    }

    /**
     * Tests that clicking on the duke theme option changes the styling on that node and changes the theme in the game view.
     */
    @Test
    void themeChangeDukeTest() {
        Rectangle dukeOption = (Rectangle)themeOptions.get("duke").getChildren().get(0);
        javafxRun(() -> clickOn(dukeOption));
        sleep(2, TimeUnit.SECONDS);
        assertEquals("themeOptionSelected", dukeOption.getStyleClass().get(0));
        assertEquals("stylesheets/duke.css", mySettingsView.getSettings().getTheme());
    }

    /**
     * Same as above, for space. Also checks that styling is removed from other nodes when a new one is clicked.
     */
    @Test
    void themeChangeSpaceTest() {
        Rectangle dukeOption = (Rectangle)themeOptions.get("duke").getChildren().get(0);
        javafxRun(() -> clickOn(dukeOption));
        Rectangle spaceOption = (Rectangle)themeOptions.get("space").getChildren().get(0);
        javafxRun(() -> clickOn(spaceOption));
        assertEquals("themeOptionSelected", spaceOption.getStyleClass().get(0));
        assertEquals("stylesheets/space.css", mySettingsView.getSettings().getTheme());
        assertEquals(0, dukeOption.getStyleClass().size());
    }

    /**
     * Tests that choosing dark mode with the classic theme works.
     */
    @Test
    void darkModeClassicTest() {
        CheckBox darkMode = lookup("dark mode").query();
        darkMode.setSelected(true);
        Rectangle classicOption = (Rectangle)themeOptions.get("classic").getChildren().get(0);
        javafxRun(() -> clickOn(classicOption));
        assertEquals("themeOptionSelected", classicOption.getStyleClass().get(0));
        assertEquals("stylesheets/default_darkMode.css", mySettingsView.getSettings().getTheme());
    }

    /**
     * Tests that choosing dark mode with the duke theme works.
     */
    @Test
    void darkModeDukeTest() {
        CheckBox darkMode = lookup("dark mode").query();
        darkMode.setSelected(true);
        Rectangle dukeOption = (Rectangle)themeOptions.get("duke").getChildren().get(0);
        javafxRun(() -> clickOn(dukeOption));
        assertEquals("themeOptionSelected", dukeOption.getStyleClass().get(0));
        assertEquals("stylesheets/duke_darkMode.css", mySettingsView.getSettings().getTheme());
    }

    /**
     * Tests that clicking the save button opens up the new window.
     */
    @Test
    void saveButtonTest() {
        javafxRun(() -> clickOn(save));
        sleep(2, TimeUnit.SECONDS);
        assertTrue(saveNew.saveStage.isShowing());
    }

    /**
     * Tests that clicking the new game button opens up the new window.
     */
    @Test
    void newGameOverrideTest() {
        javafxRun(() -> clickOn(newGame));
        sleep(2, TimeUnit.SECONDS);
        assertTrue(saveNew.overwriteStage.isShowing());
    }
}