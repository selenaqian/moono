package ooga.config;

import ooga.game.*;
import ooga.piles.*;

/**
 * This class will hold all the different information needed to save a game.
 * @author Tess Noonan (tcn6)
 *
 * I had to add the default constructor and getters in order to adhere to JavaBeans conventions (required by XMLEncoder)
 */
public class GameInfo {

    private TurnManager turnManager;
    private GameSettings gameSettings;
    private DrawPile drawPile;
    private DiscardPile discardPile;
    private ScoreTracker scoreTracker;

    /**
     * Creates new GameInfo object. Default constructor required for JavaBeans conventions.
     */
    public GameInfo() {

    }

    /**
     * Stores all the given info in a new GameInfo object.
     * @param turnManager
     * @param gameSettings
     * @param drawPile
     * @param discardPile
     * @param scoreTracker
     */
    public GameInfo(TurnManager turnManager, GameSettings gameSettings, DrawPile drawPile, DiscardPile discardPile,
                    ScoreTracker scoreTracker){
        this.turnManager = turnManager;
        this.gameSettings = gameSettings;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.scoreTracker = scoreTracker;

    }

    /**
     * Set TurnManager.
     * @param turnManager
     */
    public void setTurnManager(TurnManager turnManager) {
        this.turnManager = turnManager;
    }

    /**
     * Set GameSettings.
     * @param gameSettings
     */
    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    /**
     * Set DrawPile.
     * @param drawPile
     */
    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    /**
     * Set DiscardPile.
     * @param discardPile
     */
    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }

    /**
     * Set ScoreTracker.
     * @param scoreTracker
     */
    public void setScoreTracker(ScoreTracker scoreTracker) {
        this.scoreTracker = scoreTracker;
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
     * Get scoreTracker.
     * @return ScoreTracker
     */
    public ScoreTracker getScoreTracker() {
        return scoreTracker;
    }
}
