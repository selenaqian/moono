### API Changes

Documents any changes to the API after the finalization of the plan.

1. (4/6/2020) Added drawCard() method in DiscardPile
2. (4/6/2020) Removed Action enum (and all its occurrences)
3. (4/6/2020) Changed the ViewInterface to GameViewInterface. This new interface now has methods updateHand and updateDiscardPile.
As we figured out more of the details of implementation and information flow, we decided to have a SetupView class that
would store a GameSettings object and call setter methods for each of the sliders instead of having the view package send
that information elsewhere. We also determined that we would need methods to allow the controller package to call updates
on the view when actions in the backend occurred.
4. (4/7/2020) Modified the updateHand(int cardsLeft) method to be updateHand(int playerNumber, int cardsLeft) because realized
the view needs to know which player to update the hand view of in that method.
5. (4/7/2020) Renamed and modified GamePlay to GameModel as it will be referenced in the View. The interface is now specific to card games and includes methods to manage drawing and playing cards.
6. (4/8/2020) Added equals() method Override in Card.
7. (4/14/2020) Created new interfaces to implement observer pattern (PlayerObserver to update the view when a player's hand changes
and WildCardObserver when a wild card is played)
8. (4/15/2020) Added updateScore(int playerNumber, int score) method in GameViewInterface. Needed to be able to change the
display of player scores - forgot about this functionality in the original plan.
9. (4/15/2020) GameSettings (formerly SettingsController) now has getters for the rule and special cards so that they can be called in the game model
10. (4/16/2020) Moved drawCard() method from DrawPile to Deck so that DiscardPile also has access to it.
11. (4/22/2020) Added getAllPlayers() method to TurnManager so I could have access to it for XMLToJavaTest.
12. (4/22/2020) Added setScores() and getScores() methods to ScoreTracker so they could be used in JavaToXMLTest.