/**
 * Contains methods called from the view when the user first selects options for a new game
 */

public interface SettingsController{

    void setNumPlayers(int players);

    void setCardsPerPlayer(int numCards);

    void setWinningScore(int targetScore);

    void updateRules();

    void updateSpecialCards();

    void updateTheme();
}