### Design Plan

#### Introduction

#### Overview

#### Design Details

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