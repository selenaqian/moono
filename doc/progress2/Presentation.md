# The Ninth Planet Final Project: Sprint 1 Progress

### Presentation "Game Plan":
* Run a demo
* Walk through data files
* Walk through some testing
    * JUnit
    * TestFX
* Everyone describes their contributions
    * Selena
    * Mary
    * Suomo
    * Tess
* What we've learned/going forward discussion (bullets below)

### What we learned during this Sprint and the implementation plan for the next Sprint:

##### Completed planned features:
* create card deck (initial)
* shuffle card deck
* get top card of deck
* deal a hand of cards
* determine who goes first
* choose a card to play - human
* choose a card to play - AI
* move turn to next player (simple order)
* draw a card, play if possible
* shuffle discard pile
* any player out of cards - end game
* welcome screen
* main gameplay visual
* classic card design/visuals
* click on card to play
* end of game/round screen
* write classic rules

##### What helped or impeded progress:
* Stand-up meetings every two days :)
* Splitting up work on a package-by-package basis :)
* Different time zones :(  
* Needing functionality from other packages when there wasn’t a method to do so yet :(

##### What worked, what did not, and something specific that is planned for improvement next Sprint:
* Our regular Zoom sessions have been great to stay up to date, know what’s going on, and stay accountable. We also have been asking questions in our group chat as they come up. Our code is well-commented (especially since we wrote the APIs) which helps us read and understand our teammates’ code.
* We haven’t been sending updates in the group chat when we complete new features/classes.
* Next sprint we will write updates of what we get done in our chat as they happen.

##### A significant event we learned from:
* Meetings - learning how to discuss code remotely in a productive way, needing to try to screen share or describe in the way that things should work in a lot more detail than needed in person

##### Features planned to be completed in Sprint 2 (with who will work on them)
* [Task Tracking Spreadsheet](https://docs.google.com/spreadsheets/d/11FaTKxmpqZN-wYOsoiqJvDP3LmAJSurGGHqMblpMS40/edit?usp=sharing)
* skip card (Suomo)
* reverse card (Suomo)
* draw 2 card (Suomo)
* wild draw 4 (Tess)
* change color of play - wild cards (Mary)
* wild card (Tess)
* implement Deadly Uno rule - immediately lose if can't play (Tess)
* implement Names for Players (Suomo)
* multiple rounds of gameplay with score calculation (Mary)
* display scores for all players (Selena)
* save and resume playing a game (Mary)
* multiple games at once (Mary)
* call uno! (click button) penalty also involves backend (Mary)
* game area editor - change a particular color/image (Selena)
* rules selection screen (Selena)
* special cards selection (Selena)
* color theme selection (Selena)

##### Any concerns that may complicate the Sprint 2 plan
* How to implement data-driven design to implement the different action cards
* Finding an efficient way to implement action cards
* Making sure every class has a single, specific purpose - some are starting to get big
* Implementing AI players (calling them automatically in the game - should we create a JavaFX timeline?)