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

#### Design Considerations