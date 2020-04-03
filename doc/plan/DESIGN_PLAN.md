### Design Plan

#### Introduction

The goal of our project is to write a program that allows users to play a variety of Uno-based games. We plan to first create the classic Uno game, then extend on the rules and special cards available. The project thus will be most flexible in the rules and cards options. This flexibility will necessarily also require additions of buttons and toggles to the user interface as well as the addition of rules and cards in their respective classes. The project will also allow for different color and image themes on the cards, both static and dynamic. That is, users will be able to choose a theme at the beginning as well as change particular colors/images during gameplay. Our design will make use of interfaces to encapsulate these parts of the project so that additions to the types of cards or changes to the rules in play should not affect any of the other portions of the program, and the same goes for changes to the theme.

We hope to create a fun, functional game that gives options beyond the traditional Uno game by providing novel combinations of rules and special cards.

#### Overview

#### Design Details
The player superclass would contain two sub classes; one for the manual player player and the other for the computer generated players.
It calls on the drawcard method in drawPile to deal each player its hand depending on the number of cards the game variation requires.
Regardless of player(manual or auto), it will throw an exception which will be modeled in an exception class when a player chooses to play an invalid card(which would get passed to the viewer) 


##### Game Module
This module facilitates progression through the game. It supports multiple game rounds, with interfaces that can be implemented by both the entire game and individual rounds. A submodule (consisting of ScoreTracker and GameStatus) also keeps track of score and winners independently of the behavior for gameplay. This allows for winners to be determined both for each round and for the overall game. While we intend to create a game with multiple rounds, this module can support a single-round game – which is helpful especially when we implement single-round games for our first sprint. 

Another component of this module is the TurnManager, which keeps track of the players in the game to determine whose turn it is. When the game is actually played (in a class implementing the GamePlay interface), the TurnManager is able to call methods on the appropropriate Player object. Because Uno contains situations where the progression from one player to the next is not always linear, the TurnManager API provides methods to modify how a turn progresses (e.g. skipping a player or going back to the previous player). The class with the gameplay references the Pile module to call updates on Pile and Hand objects after a player has made their move. 

To collaborate with the view module, there is also a component (SettingsController) to support the MVC design pattern. It takes user-selected values for game options (e.g. number of players) that are used by other components in this module. 

The entire Game module is designed to be as extensible as possible. It is not specific to the game of Uno, and its submodules can be reused in other games of the genre. It references interfaces from other modules, but does not need to know about the specific implementation of methods in other modules. Additionally, the submodules within this module are loosely-coupled with each other. For example, different concrete classes can implement GamePlay to have different behavior for how a game is played, but they can still use the same components for keeping track of score and players. 


#### Example Games
The main variations of our game are related to game rules and themes. On top of that, we will have some starting choices initialized by the user, such as number of players, size of dealt hands, and choices in cards to implement in the game.

Game #1: Completely Classic
This game uses the classic rules, basic colors with the theme, has 4 players (one human, three computers), and deals 7 cards (normal).

Game #2: Special Classic
This game has all the specifications as Game #1 except it uses the complete set of cards, normal and special (0-9, skip, reverse, wild, etc).

Game #3: Hodge Podge
This game uses the ascending rules (need to match value with any suit or beat value with the same suit, wild resets the value), space-themed cards, has 1 player (human), and deals 20 cards (normal and special).

The only difference between games 1 and 2 is the card selection. When a DrawPile is instantiated, by default it calls the CardBuilder to give it a standard Card stack of 80 cards (2 of every suit/value combo where 0 <= value <= 9). However, if the user wants a different set of cards, based on user selection the View will pass a list of desired card Actions to the DrawPile (i.e. NONE, WILD, DRAW2… where none represents normal cards and the others are special cards). The DrawPile will then pass this list to the CardBuilder which will create a Deck. For example, if NONE, WILD, and REVERSE are passed, the DrawPile will have the 80 standard normal cards described above, 4 wilds, and 4 reverses (one of each suit).

There are quite a few differences between games 1 and 3. The first difference is in the rules. Our project will have a Rule interface that every ruleset will implement. The user will select the desired rules then the View will pass this back to be instantiated in GameControl. The interface allows us to standardize methods among all rules.

Another difference is the theme. The View takes the information stored in Cards and displays them as nodes in JavaFX. The different themes/styles will be stored in different data files. The View just has to choose the corresponding file to pull the images for the cards.

Play count is another choice made by the user at the start. We have a Player interface that will be implemented in two classes: User and Computer. When there is only one player, it will be a single User (human), then we can instantiate a Computer instance for every additional player. The TurnManager then rotates between the Players to have them play. With one player, the TurnManager just has the user move until the game ends.

Finally, Hands are dealt from the DrawPile. There is a drawCards() method where you can specify how many Cards to pull from the Pile. Therefore, to change the size of a Hand based on the count passed in by the User, we just have to pass that integer to the drawCards() method to pull the top x cards and put them in a new Hand.

#### Design Considerations
1. Rules- we have a feature that allows a user to mix and match rules from various variations to create a an
entirely new game. With this, we could either have a rule superclass that each game variation extends and then have another independent class which creates an instance of that  rule interface and can 
call on any method which give a variety of choices. Say we had an abstract method in the super class that each sub class overrode, possible complications will arise 
since we cannot tell which variation's rule will be implemented.

2. Connecting model and view: to use MVC or not, and where? One option we considered was just having the model and view components keep references and call methods directly on each other, without a controller to handle the logic. Writing our model and view classes like this would not adhere to the single-responsibility principle, and it would be difficult to divide the code for the sake of collaboration. Implementing an MVC pattern does add complexity, however, and we would need to create multiple controllers to handle all of our view components. We still decided to follow the MVC pattern, but will not always explicitly separate our code cleanly into separate MVC packages. The smaller view and controller components will likely communicate freely without going through the model, reducing complexity. Another pro of using the MVC model is greater flexibility in implementing different game variations. 



