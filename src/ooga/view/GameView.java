package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.cards.Card;

import java.util.ArrayList;
import java.util.List;

import static ooga.view.SetupView.*;

public class GameView implements GameViewInterface {
    private Stage mainStage;
    private List<Pane> playerViews;
    private BorderPane mainPane;

    public GameView() {
        this(DEFAULT_PLAYERS, DEFAULT_CARDS, new ArrayList<>(), new Stage());
    }

    public GameView(int numPlayers, int startCards, List<Card> player1Cards, Stage stage) {
        mainStage = stage;
        playerViews = makePlayerStatuses(numPlayers, startCards);
        updateHand(player1Cards);
        mainPane = new BorderPane();
        anchorPlayerViews();
        // TODO: figure out why alignment isn't working
        for (Pane p : playerViews) {
            mainPane.setAlignment(p, Pos.CENTER);
        }

        // TODO: still need render the deck and discard piles, uno button

        Scene mainScene = new Scene(mainPane, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        mainStage.setScene(mainScene);
        mainStage.show();

        // TODO: initialize and use properties file for text
    }

    private void anchorPlayerViews() {
        mainPane.setBottom(playerViews.get(0));
        mainPane.setLeft(playerViews.get(1));
        if (playerViews.size()>2) mainPane.setTop(playerViews.get(2));
        if (playerViews.size()>3) mainPane.setRight(playerViews.get(3));
    }

    private List<Pane> makePlayerStatuses(int numPlayers, int startCards) {
        List<Pane> playersList = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            HBox playerBox = new HBox();
            VBox textBox = new VBox();

            Text playerNumber = new Text("Player " + (i+1));
            Text cardsLeft = new Text(startCards + " left");
            cardsLeft.setId("player" + (i+1) + "cardsleft");
            textBox.getChildren().addAll(playerNumber, cardsLeft);

            Circle playerIcon = new Circle(10);
            playerBox.getChildren().addAll(playerIcon, textBox);

            playersList.add(playerBox);
        }
        Rectangle player1Base = new Rectangle(mainStage.getWidth(), mainStage.getHeight()/4, Color.WHITE);
        VBox player1Box = new VBox();
        player1Box.getChildren().addAll(playersList.get(0), player1Base);
        player1Box.setAlignment(Pos.CENTER); //TODO: alignment still a problem here too
        playersList.remove(0);
        playersList.add(0, player1Box);
        return playersList;
    }

    @Override
    public void updateHand(List<Card> cards) {
        // generate viewable nodes for the cards - should be scalable based on window
        // put the cards in an hbox
        // put that hbox on top of a white rectangle - inside a stackpane
        // add this stackpane into the playerViews list
    }

    @Override
    public void updateHand(int playerNumber, int cardsLeft) {
        // just needs to update the text inside the vbox - use a lookup probably
    }

    @Override
    public void updateDiscardPile(Card card) {
        // replace the rendering of the current discard with a new one
    }
}
