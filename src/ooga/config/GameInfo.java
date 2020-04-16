package ooga.config;

import ooga.game.GameSettings;
import ooga.game.TurnManager;
import ooga.piles.*;

import java.util.List;

/**
 * This class will hold all the different information needed to save a game.
 * @author Tess Noonan (tcn6)
 */
public class GameInfo {

    private TurnManager turnManager;
    private GameSettings gameSettings;
    private DrawPile drawPile;
    private DiscardPile discardPile;
    private List<Integer> playerPoints;
    //TODO: save the theme when that becomes applicable

    /**
     * Stores all the given info in a new GameInfo object.
     * @param turnManager
     * @param gameSettings
     * @param drawPile
     * @param discardPile
     * @param points
     */
    public GameInfo(TurnManager turnManager, GameSettings gameSettings, DrawPile drawPile, DiscardPile discardPile,
                    List<Integer> points){
        this.turnManager = turnManager;
        this.gameSettings = gameSettings;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.playerPoints = points;

    }

    /**
     * Get turnManager.
     * @return TurnManager
     */
    public TurnManager getTurnManager() {
        return turnManager;
    }

    /**
     * Get gameSettings.
     * @return GameSettings
     */
    public GameSettings getGameSettings() {
        return gameSettings;
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
     * Get playerPoints.
     * @return List<Integer>
     */
    public List<Integer> getPlayerPoints() {
        return playerPoints;
    }
}
