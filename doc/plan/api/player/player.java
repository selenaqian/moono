package player;
/**
 *This interface contains player information(manual and computer players):
 * player name
 * player's hand
 * the card a player chooses to play
 *
 */
public interface player {

    /**
     * returns a list of the player's hands
     * @return
     */
    List<Card> hands();

    /**
     *
     * @param the player's hand
     * @return the chosen card of plays
     */
    Card cardbeingplayed(List<Card> hands);

    /**
     *
     * @return the player's chosen name
     */
    String playername();

}
