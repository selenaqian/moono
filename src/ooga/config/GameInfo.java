package ooga.config;

import ooga.piles.*;
import ooga.rules.Rule;

import java.util.List;

/**
 * This class will hold all the different information needed to save a game.
 * @author Tess Noonan (tcn6)
 */
public class GameInfo {

    private List<Hand> playerHands;
    private DrawPile drawPile;
    private DiscardPile discardPile;
    private int currentTurnPlayerID;
    private int direction;
    private List<Integer> playerPoints;
    private Rule rules;
    //TODO: save the theme when that becomes applicable

    /**
     * Stores all the given info in a new GameInfo object.
     * @param playerHands
     * @param drawPile
     * @param discardPile
     * @param currentTurn
     * @param direction
     * @param points
     * @param rule
     */
    public GameInfo(List<Hand> playerHands, DrawPile drawPile, DiscardPile discardPile, int currentTurn, int direction,
                    List<Integer> points, Rule rule){
        this.playerHands = playerHands;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.currentTurnPlayerID = currentTurn;
        this.direction = direction;
        this.playerPoints = points;
        this.rules = rule;
    }

    /**
     * Get playerHands.
     * @return List<Hand>
     */
    public List<Hand> getPlayerHands() {
        return playerHands;
    }

    /**
     * Get drawPile.
     * @return DrawPile.
     */
    public DrawPile getDrawPile() {
        return drawPile;
    }

    /**
     * Get discardPile.
     * @return DiscardPile.
     */
    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    /**
     * Get currentTurnPlayerID.
     * @return int
     */
    public int getCurrentTurnPlayerID() {
        return currentTurnPlayerID;
    }

    /**
     * Get direction.
     * @return int
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Get playerPoints.
     * @return List<Integer>
     */
    public List<Integer> getPlayerPoints() {
        return playerPoints;
    }

    /**
     * Get rules.
     * @return Rule
     */
    public Rule getRules() {
        return rules;
    }
}
