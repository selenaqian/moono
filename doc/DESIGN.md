### Moono: Design

#### Team Members

* Tess Noonan (tcn6)
* Mary Jiang (mvj6)
* Selena Qian (sq22)
* Suomo Ammah (sna19)

#### Roles

Mary Jiang (mvj6):
* Implementing classes in the Game package:
    * GameModel (Uno) and GameController (UnoController) for managing and calling game-play events
    * Setting up TurnManager, PileManager, GameSettings, and UnoActionApplier
    * ScoreTracker
* Connecting view with controller and model components
    * Creating observers for game updates
    * Handling calls to view for wildcards

Selena Qian (sq22):
* Implementing classes in the View package:
    * CardRender
    * EndView
        * EndGameView
        * EndRound
    * GameViewInterface
        * GameView
    * MidGameSaveNew
    * RulesAndSpecialCardScene
    * SettingsView
    * SetupView
    * ThemeSelectionScene
    * WildColorSelectorView
* Required setting up the UI and setting the buttons and clicks on certain nodes to actions.

Suomo Ammah (sna19):
* Implementing classes in the player package
  * Manual player
  * Ai player
* Setting up the exception class and implementing special cards
  * draw2
  * skip
  * reverse
  * swap(later removed)
  * trade(later removed)

Tess Noonan (tcn6):
* Implementing classes in the cards package
    * Card
    * Suit enum
    * Value enum
* Implementing classes in the piles package to group together cards:
    * Pile interface
    * Deck abstract class
    * DiscardPile
    * DrawPile
    * CardBuilder
    * Hand
* Implementing classes in the config package in order to give backend save/load functionality:
    * GameInfo
    * JavaToXML
    * XMLToJava
* Contributed to the UnoActionApplier class

#### Design Goals

The core of our project is to make a functioning Uno game. Then, our goal is to be able to add variations on top of
that. Functionally, we want to be able to use a variety of rule sets, different combinations of cards (either classic
or ones you invent yourself), and different combinations of human and bot players. We also want the flexibility to
change aspects of the game play such as hand size and winning score. Aesthetically, we want to be able to mix up the
theme and be able to change the language/text.

#### Project Design

Config:
   * The config package provides functionality for saving and loading in a game.The JavaToXML class encodes a game to an
   XML file and the XMLToJava class decodes an XML file back to an instance of GameInfo.
  
Game (Model and Controller):
   * The GameModel (in planning known as "GameInfo") interface allows the GameController to call actions that play a game, such as drawCard() and playCard().
        * The classes that define a unique game are PileManager TurnManager, GameSettings, and ActionApplier,
        so the GameModel class holds all these as instance variables to organize all the necessary information in one place. 
            * PileManager contains Piles that the GameModel can call to initialize and access the DrawPile and DiscardPile
            * TurnManager initializes and contains all players in a game, and is used by GameModel to get the current player
            and move the turn to someone else. 
            * GameSettings holds information to initialize a game, such as the number of players or the cards per player. 
            * ActionApplier holds helper methods to call changes to the GameModel when a special card is played
   * GameModelView is an interface implemented by the same class as GameModel. It provides read-only methods that give
   the View information about the game and contains methods to add/remove and update observers.
   * The GameController ("GameFlow" during planning) interface initializes an instance of a GameModel. It contains a JavaFX timeline that calls
   actions on the GameModel at each step. The GameView can call methods in the Controller to start, pause, or end a game.
   View instances of SetupView and GameView are also initialized by the Controller.
   In the UnoController implementation, it also handles clicks from the user in the view to play or draw a card.
        * Holds an instance of ScoreTracker, which is used to calculate and track player scores   
        * Calls to SoundPlayer to play an audio when there is an event (i.e. Uno is called)
        * 

Cards (Model): 
   * The most basic structure we have in the game are the cards. They are instances of the Card class and each contain
   two properties: a Value and Suit (which we have implemented as enums).
   
Piles (Model):
   * The next structure are the Piles which are groups of Cards. The Pile interface has two types of subclasses: Decks
   and Hands. A Deck, which is an abstract class, is an ordered Pile (implemented as a Stack) that can be shuffled. We
   have two types of Decks: DrawPiles and DiscardPiles. On the other "hand", a Hand is an unordered grouping of Cards.
   Also note, DrawPile uses the CardBuilder class to instantiate its initial Stack of Cards.
   * Then we have a Rule which specifies what moves are valid and when a game is over. The Rule abstract class defines
   the default end of a game as when a Player has only one Card left in their hand which is valid. The subclasses
   ClassicRules and AscendingRules use this implementation while DeadlyRules overrides the method.

   * The config package provides functionality for saving and loading in a game. The classes that define a unique game
   are DrawPile, DiscardPile, TurnManager, ScoreTracker, and GameSettings, so the GameInfo class holds all these as
   instance variables to organize all the necessary information in one place. The JavaToXML class encodes a game to an
   XML file and the XMLToJava class decodes an XML file back to an instance of GameInfo.
   
Player(Model)
   * The player class is an abstract class with two subclasses: Manualplayer and AI player. The class enables setting IDs for the players and 
      manipulation of the players' hands.

View (View):
* This package handles the creation of the visual scenes and showing them on the JavaFX stage. It also handles the user
inputs, such as user selections of total number of players, what rules to use, and which special cards to use.
* SetupView, GameView, and EndView are the main classes, each with helper or child classes that aid in the creation of the
visual material.
    * SetupView: Asks for user input about game elements - rules, special cards, theme, number of players, number of cards
    to start with per player, and score to play to. Sends that information to the GameSettings. Uses RulesAndSpecialCardsScene
    and ThemeSelectionScene to build those views.
    * GameView: Implements GameViewInterface and PlayerObserver. Is created by the controller and sets up the main visuals
    of the gameplay. Sends information to the controller about user clicks on the cards. Receives information about whose
    turn it is and when to update the player displays or the decks.
    * EndView: Abstract class, with EndGameView and EndRoundView as the concrete classes. Creates and shows a screen at
    some endpoint in the game.
* The PlayerObserver interface is implemented by GameView. It updates the cards in the user's hand, and updates the
number of cards that all players have. 

#### Assumptions/Simplifications

* This plays single-person game where there is 1 human player playing against up to 3 computers
* Unlike in real-life games of Uno, users can only call uno for themselves and cannot "call out" other players
    * Computer players calling uno is set to chance, and when they do call there will be a sound played ("Moono!")
* Lists are not serializable so when saving a game it defaults to 4 players in TurnManager
* Real Uno score-tracking adds the scores of other players to the winner of each round, but our version only adds
the scores of each player to themselves (i.e. there is no advantage to "winning" a round by shedding all cards). This was
a human error interpreting the rules of Uno!

#### Adding new features

* Adding new special cards:
    * Add new value to the Value enum, update in CardBuilder/Rules where appropriate
    * Add enum to the switch statement in UnoActionApplier, create method that applies action
    * If user interaction is required to perform the action of the card (e.g. cards that perform complex actions), the
    WildcardObserver and WildCardSelectorView can be modified. A new update method should be included in the observer
    interface to accommodate the action, then implemented in UnoActionApplier. WildCardSelectorView should have an
    update method that creates the JavaFX elements necessary to get user input. 
    * In properties file:
        * Add an option to specialCards
* Adding different rule sets:
    * Create a new class in the rules package that extends Rule. Must override the isValid() method, can override isOver() method if applicable
    * If Java reflection has not been implemented in GameSettings getRule(), then getRule() must be modified so that it will
    return the new rule.
    * In properties file:
        * If mutually exclusive from other rule sets, add to an element of rulesOptions and update the corresponding key
        * Else add another element to rulesOptions (semicolon-delineated list) and create its corresponding key
        * Create a key that links the option to the corresponding rules class (e.g. classic\ rules=ClassicRules)
* Adding a different theme:
    * Make a new .css file and a corresponding dark mode file ([name].css and [name]_darkMode.css, respectively)
    * In the properties file:
        * Add the display text for the theme to themeOptions
        * Add the colors to show for the theme selection in a comma-delineated list with the display text as a key
        * Add the .css file path at themeFileNames, using the same index as the display text under themeOptions
* Allowing for different languages/display text:
    * Create new properties files for display text (default, deck_size, scoring, errors)
    * Add a method to allow setting of the resources and updating of the visuals
    * Add a button or some GUI to trigger the change