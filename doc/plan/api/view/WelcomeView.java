package ooga.view;

import ooga.cards.Card;

import java.util.List;

/**
 * A WelcomeView creates the user interface for the initial game option setup.
 * These initial options include slider values for the number of players (2-4), number of cards per player, the point
 * total that one player needs to reach before determining the overall winner, any custom rules selections, any special cards
 * selections, and the deck theme.
 */
public interface WelcomeView {
    /**
     * Gets information from the number of players slider.
     * @return the desired number of players determined by the user.
     * Default is set to 4.
     */
    public int getNumberPlayers();

    /**
     * Gets information from the number of cards slider.
     * @return the desired number of initial cards per player determined by the user.
     * Default is set to 7.
     */
    public int getNumberCards();

    /**
     * Gets information from the point total slider. A higher point total will result in a longer game.
     * @return the desired point total to reach before determining an overall winner.
     * Default is set to 500.
     */
    public int getPointTotal();

    /**
     * Gets which rules the user toggles on. Has defaults for certain rules if none in a category are selected.
     * For example, for the rule that when a player has no playable cards, drawing 1 vs. drawing until playable vs.
     * immediately losing - if none are selected, then defaults to drawing 1 and playing if that card is playable.
     * @return a list of the abbreviated Strings associated with the user-selected rules.
     */
    public List<String> getSelectedRules();

    /**
     * Gets which special cards the user decides to allow in gameplay. Includes standard skip, reverse, etc. and allows
     * for expansion beyond those.
     * @return a list of the user-selected cards.
     */
    public List<Card> getSelectedCards();

    /**
     * Gets which color theme the user wants to play in, i.e. Classic, Duke, Space.
     * Changes the appearance of the cards but does not affect gameplay.
     * @return a String associated with the user-selected theme.
     */
    public String getSelectedTheme();
}
