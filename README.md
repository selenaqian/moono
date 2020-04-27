final
====

This project implements multiple versions of Uno, with flexibility allowing players to choose rules and design.

Names:
* Tess Noonan (tcn6)
* Mary Jiang (mvj6)
* Selena Qian (sq22)
* Suomo Ammah (sna19)


### Timeline

Start Date: 3/30/2020

Finish Date: 4/27/2020

Hours Spent Together: 27 hours

Individual:
- Tess Hours: 23 hours
- Mary Hours: 21.5 hours
- Selena Hours: 25 hours
- Suomo Hours:20 hours


### Primary Roles

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

### Resources Used
- [Uno Rules](https://service.mattel.com/instruction_sheets/42001pr.pdf)
- [Uno Variations - invented by players](https://www.pagat.com/invented/uno_vars.html)
- [Uno Variations - special cards in themed decks](http://unovariations.blogspot.com/p/special-wild-cards.html)
- [XML into Java Objects](https://www.javatpoint.com/jaxb-unmarshalling-example)
- [JavaFX Slider Example](https://docs.oracle.com/javafx/2/ui_controls/slider.htm)
- [JavaFX Layouts](https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm)
- [Java Documentation](https://docs.oracle.com/javase/8/docs/)
- [XML De/Encoding](https://howtodoinjava.com/java/serialization/xmlencoder-and-xmldecoder-example/)
- [Encoder Stack Exchange Help](https://stackoverflow.com/questions/24725368/java-lang-instantiation-exception-while-using-xmlencoder)
- [JavaFX CSS Styling](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#node)
- [File choosers](http://tutorials.jenkov.com/javafx/filechooser.html)

### Running the Program

Main class:
* Main.java

Data files needed:
* deck_size.properties
* default.properties
* errors.properties
* scoring.properties
* xml_strings.properties
* *Audio*
    * Uno1.mp3
    * Uno2.mp3
    * Uno3.mp3
    * Uno4.mp3
    * uno_audios.properties
* *stylesheets*
    * default.css
    * default_darkMode.css
    * duke.css
    * duke_darkMode.css
    * space.css
    * space_darkMode.css
    * *images*
        * duke_card.png
        * space_card.jpg
        * uno_card.png

Features implemented:
* Classic Uno game
* Deadly Uno - immediately lose if you cannot play a card
* Ascending Uno - must play a card with an equal or higher value as well as the same color
* Multiple color themes
* Dark mode for each color theme
* Save games
* Load games
* Multiple games played at once
* Preferences for speed and theme can be changed mid-game

### Notes/Assumptions

Assumptions or Simplifications:
* This plays single-person game where there is 1 human player playing against up to 3 computers
* Unlike in real-life games of Uno, users can only call uno for themselves and cannot "call out" other players
    * Computer players calling uno is set to chance, and when they do call there will be a sound played ("Moono!")
* Lists are not serializable so when saving a game it defaults to 4 players in TurnManager
* Real Uno score-tracking adds the scores of other players to the winner of each round, but our version only adds
the scores of each player to themselves (i.e. there is no advantage to "winning" a round by shedding all cards). This was
a human error interpreting the rules of Uno!

Interesting data files:

Known Bugs:
* Loading a game currently is not functioning as expected.
* If the human player keeps hoarding cards and does not make plays, they will eventually exhaust all the cards in the piles. 
There is currently no behavior to handle this error or force a user to play a card.
* Behavior of the WILD and WILD4 cards is not completely working as expected 

Extra credit:


### Impressions

This project was a lot more involved than our previous ones. Our design plan was useful at a high level to help us think
about where to start, but as we began to add in more features, we quickly saw how easy it was to allow dependencies to arise.
Furthermore, there were a lot of implementation details that made us re-think our initial design plan. Many features were
initially implemented in once place because it did make sense to put it there, but we then realized that the function also
needed to be called from another part of the project as well.

We also spent a fair chunk of time discussing the idea of data-driven design. While we weren't able to make everything data-driven,
this was certainly something that we thought about along the process. Looking at the project as it is, we can definitely see
some areas where data could help a lot in making the project more flexible, such as using a properties file for the sizes of
objects in the UI.

(Selena)
I did find it rewarding to see the finished product, especially with some of the more interesting visuals. The UI component
definitely added a sense of satisfaction for me — seeing the turns advance and the game being played is pretty cool. I think
that we overall were able to create something pretty cool.

I appreciate dthe time we spent just discussing design and putting together a comprehensive plan with the APIs and use cases. It's already been mentioned
but we couldn't implement all the features we wanted but I think we did our best and focused most on design.