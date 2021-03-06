/**
 * Helper class that creates the scene for selecting the rules. This class is package-private because only the SetupView
 * class needs to access any information from here. The goal of this class is to separate the creation of the scene, leaving
 * SetupView to be something of an intermediate controller between the fully view classes and the game package.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
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
    List<RadioButton> ruleSelections;
    List<CheckBox> specialCardOptions;
    Button rulesAndSpecialCardsOkButton;

    RulesAndSpecialCardScene() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        ruleSelections = new ArrayList<>();
        specialCardOptions = new ArrayList<>();
    }

    /**
     * Called from SetupView when need to switch to this scene.
     * @return a scene with GUI for selecting which rules to apply and special cards to include in the game.
     */
    Scene makeSelectionScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        HBox options = new HBox(DEFAULT_SPACING);
        options.setAlignment(Pos.CENTER);
        options.getChildren().addAll(makeRulesBox(), makeSpecialCardsBox());

        rulesAndSpecialCardsOkButton = new Button(myResources.getString("okay"));
        root.getChildren().addAll(options, rulesAndSpecialCardsOkButton);
        Scene ruleSelectionScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        ruleSelectionScene.getStylesheets().add(DEFAULT_STYLESHEET);
        return ruleSelectionScene;
    }

    /**
     * Helper method to create the rules selections part of the scene.
     * @return a box with the rules listed in groups based on which rules are mutually exclusive.
     * e.g. draw 1 card when you can't play a valid card vs. draw until you have a valid card.
     */
    private VBox makeRulesBox() {
        VBox rulesBox = new VBox(DEFAULT_SPACING);
        Text rulesText = new Text(myResources.getString("rulesText"));
        rulesText.getStyleClass().add("subtitle2");
        rulesBox.getChildren().add(rulesText);
        String[] allRules = myResources.getString("rulesOptions").split(";");
        for (String ruleType : allRules) {
            Text ruleHeader = new Text(ruleType);
            rulesBox.getChildren().add(ruleHeader);

            ToggleGroup group = new ToggleGroup();

            String[] individualRules = myResources.getString(ruleType).split(",");
            for (int i=0; i < individualRules.length; i++) {
                RadioButton radioButton = new RadioButton(individualRules[i]);
                if(i==0) radioButton.setSelected(true);
                ruleSelections.add(radioButton);
                radioButton.setToggleGroup(group);
                rulesBox.getChildren().add(radioButton);
            }
        }
        return rulesBox;
    }

    /**
     * Helper method to create the special cards selection part of the scene.
     * @return a box with the special cards all listed. Functionality for telling the backend which cards to include
     * will be in SetupView, not here.
     */
    private VBox makeSpecialCardsBox() {
        VBox specialCardsBox = new VBox(DEFAULT_SPACING);
        Text specialCardsText = new Text(myResources.getString("specialCardsText"));
        specialCardsText.getStyleClass().add("subtitle2");
        specialCardsBox.getChildren().add(specialCardsText);

        String[] allSpecialCards = myResources.getString("specialCardsOptions").split(",");
        for(String specialCard : allSpecialCards) {
            CheckBox checkBox = new CheckBox(specialCard);
            checkBox.allowIndeterminateProperty();
            specialCardOptions.add(checkBox);
            specialCardsBox.getChildren().add(checkBox);
        }
        //TODO (if time): create a checkbox to select all the others - requires additional work with observers
        /**CheckBox selectAll = new CheckBox("select all");
        if(selectAll.isSelected()) {
            for(CheckBox option : specialCardOptions) {
                option.setSelected(true);
            }
        }*/
        return specialCardsBox;
    }

    /**
     * Called in SetupView. Allows SetupView to access the button and know if it has been clicked, then call the correct
     * interactions from there.
     * @return the okay! button in the selection scene.
     */
    Button getRulesAndSpecialCardsOkButton() {
        return rulesAndSpecialCardsOkButton;
    }

    /**
     * Called in the SetupView. Allows SetupView to access which rules the user wants to use in the game.
     * @return the list of Strings that indicate which rules to use.
     */
    List<String> getRuleSelections() {
        List<String> selectedRules = new ArrayList<>();
        for(RadioButton r : ruleSelections) {
            if(r.isSelected()) selectedRules.add(myResources.getString(r.getText()));
        }
        return selectedRules;
    }

    /**
     * Called in SetupView. Allows SetupView to access which special cards the user wants to include in the game.
     * @return the list of Strings that tell the model which special cards to use.
     */
    List<String> getSpecialCardSelections() {
        List<String> selectedSpecialCards = new ArrayList<>();
        for(CheckBox b : specialCardOptions) {
            if(b.isSelected()) selectedSpecialCards.add(myResources.getString(b.getText()));
        }
        return selectedSpecialCards;
    }
}
