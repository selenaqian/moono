/**
 * Helper class that creates the scene for selecting the rules. This class is package-private because only the SetupView
 * class needs to access anything in here. If the controller classes or model classes need to interact with the view, they
 * will do so through SetupView or GameView, not through this class.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ooga.view.SetupView.*;

class RulesAndSpecialCardScene {
    ResourceBundle myResources;
    List<ToggleGroup> ruleSelections;
    List<CheckBox> specialCardOptions;
    Button rulesAndSpecialCardsOkButton;

    RulesAndSpecialCardScene() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        ruleSelections = new ArrayList<>();
        specialCardOptions = new ArrayList<>();
    }

    Scene makeSelectionScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        HBox options = new HBox(DEFAULT_SPACING);
        options.getChildren().addAll(makeRulesBox(), makeSpecialCardsBox());

        rulesAndSpecialCardsOkButton = new Button(myResources.getString("okay"));
        root.getChildren().addAll(options, rulesAndSpecialCardsOkButton);
        return new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
    }

    private VBox makeRulesBox() {
        VBox rulesBox = new VBox(DEFAULT_SPACING);
        Text rulesText = new Text(myResources.getString("rulesText"));
        rulesBox.getChildren().add(rulesText);
        String[] allRules = myResources.getString("rulesOptions").split(",");
        for (String ruleType : allRules) {
            Text ruleHeader = new Text(ruleType);
            rulesBox.getChildren().add(ruleHeader);

            ToggleGroup group = new ToggleGroup();
            ruleSelections.add(group);

            String[] individualRules = myResources.getString(ruleType).split(",");
            for (String rule : individualRules) {
                RadioButton radioButton = new RadioButton(rule);
                radioButton.setToggleGroup(group);
                rulesBox.getChildren().add(radioButton);
            }
        }
        return rulesBox;
    }

    private VBox makeSpecialCardsBox() {
        VBox specialCardsBox = new VBox(DEFAULT_SPACING);
        Text specialCardsText = new Text(myResources.getString("specialCardsText"));
        specialCardsBox.getChildren().add(specialCardsText);

        String[] allSpecialCards = myResources.getString("specialCardsOptions").split(",");
        for(String specialCard : allSpecialCards) {
            CheckBox checkBox = new CheckBox(specialCard);
            checkBox.allowIndeterminateProperty();
            specialCardOptions.add(checkBox);
            specialCardsBox.getChildren().add(checkBox);
        }
        return specialCardsBox;
    }

    Button getRulesAndSpecialCardsOkButton() {
        return rulesAndSpecialCardsOkButton;
    }
}
