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
    * Setting up TurnManager, PileManager, GameSettings
    * Score-tracking 
* Connecting view with controller and model components
    * Setting up observers for game updates and handling wildcard view requirements

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

what are the project's design goals, specifically what kinds of new features did you want to make easy to add

#### Project Design

describe the high-level design of your project, focusing on the purpose and interaction of the core classes

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
    * TODO: backend and controller here
    * In properties file:
        * Add an option to specialCards
* Adding different rule sets:
    * TODO: backend and controller here
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