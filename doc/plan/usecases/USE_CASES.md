### Use Cases

1. For both human and AI player:
    1. Draw a card from the deck
        - involves update of backend information about what cards the player holds
        - requires update to frontend displaying number of cards in the hand and adding the new card to the human player's visual
    hand display
    2. Play a standard card (must match suit/number)
        - human player check for click on desired card, AI player randomly choose a valid card
        - also needs to check for card validity and throw exception if invalid
        - update the discard pile with the new top card - both in front and backend
        - update hands and view of them accordingly
    3. Play a special card - skip, reverse, draw 2, draw 4 wild cards, etc.
        - similar to playing a standard card but affects gameplay differently by changing which player goes next
    4. Play a wild card - change suit of play
      - similar to playing a standard card but also changes suit with no option for number matching
2. Choose card design theme - e.g. Duke theme, space theme
    - create view of cards with the particular theme
3. Choose rules - e.g. when unable to play, draw 1 card and play if possible vs. draw until can play a card vs. immediately lose
    - toggle options on and off in initial screens
    - take the information inputted through the frontend and affect gameplay in the backend
4. Choose which special cards to include in the deck
    - toggle options on and off in initial screens
    - take this information and use it to create the initial deck of cards
5. Set point total:
    - in Uno, when someone wins, the other players total up the value of the cards in their hand. Players will play until
    one person reaches the set point total (or the default of 500). At that point, the person with the lowest point total
    is the winner.
    - keep track of this information in the backend
    - update view with scores accordingly
    - update "high" score (actually lowest) and save to a file
6. Set number of players - planning to limit 1-4
    - user input in frontend, affects gameplay in backend
7. Set initial number of cards per player
    - user input in frontend
    - affects dealing of cards, number of cards initially in hand and deck
8. Show and clear high score
    - display on frontend, user input to clear
    - saves to a data file
9. Save and resume a game
    - user input on frontend when decide to save
    - get all necessary information about hands, deck, whose turn it is, etc.
    - save to a data file/files
10. Play multiple games at once - in separate windows
    - user input to start game in separate window
    - calls backend and frontend to do new game setup
11. Start new game after a game ends without quitting
    - user input for button to play again
    - calls backend and frontend to do new game setup
12. Start a new game in the middle of a game - via settings menu
    - user input for button to start a new game
    - calls backend and frontend to do new game setup overwriting current
13. Game area editor - toggle specific suit/image
    - frontend searching for and changing the visual of cards with that suit/image
    - interacts with backend classes involving cards to figure out which cards' displays need to change
14. Deal a hand of cards
    - backend allocates the appropriate number to a player
    - view updates the count (AI)/hand view (human player)
15. Create and shuffle a card deck
    - backend builds the correct set of cards, specified by the user via frontend input
    - randomizes the order of the cards and stores that order
16. Move turn to next player
    - backend moves to next players' turn and calls turn mechanics
    - frontend updates accordingly based on what happens in that turn