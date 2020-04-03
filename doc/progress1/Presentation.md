# The Ninth Planet Final Project Presentation

### Implementation Plan

#### Board Games - Uno Variations

Commonalities:
- has cards and groupings of cards
- players take turns
- some determinant for playable cards

Differences:
- function of cards in play
- rules for what to do when a player cannot play a card
- win conditions - e.g. points
- card colors/images

#### Roles and Responsibilities (primary):
- Tess + Suomo: backend
    - Cards
    - Piles
    - Rules
    - Player
- Mary: interaction between frontend and backend
    - Game
- Selena: frontend
    - View

#### Extensions:
- Ascending Uno - must play cards equal or sequentially higher, color matching also still applies
- toggling rules
- toggling special cards
- choosing card theme

#### Features:
Sprint 1: Basic Uno Game
- setting up the game
- setting up the GUI
- creating player actions (1 human + up to 3 AI)
- playing through the game
- end screen that can start a new game
Sprint 2: Extensions on the Uno Game
- special cards (skip, reverse, draw 2, draw 4 wild)
- rules selection, special cards selection
- calling "Uno!"
- dynamic display editor
- save and resume game
Complete: Additional Features
- additional special cards (e.g. draw entire deck, trade hands with another player)
- different themes (Duke, space)
- dark mode
- preferences
- player profiles
- saving to web
- social center
    
#### [Wireframe](https://www.youtube.com/watch?v=EtFE2KQ19k0)

### Design Plan
Our goals for the design is to have one that gives the user the most flexibility in terms out game outlook without having to 
handle every possible user combination. 
We are thinking of a possible mvc design or an observer to coordinate between the backend and frontend.
We want to have our cards created once per game and immutable for the duration of that game and since game updates. 
For now, we have three modules, one handling individual card creation, one handling the flow of the game , one handling the player and another handling the deck.
The card module takes card properties like value and color from respective enum classes and creates the combinations needed to play the game. The deck module then puts these into a one complete deck of cards and splits them into two sub decks for drawing cards while playing the game and discarding what has been played.
Gameflow controls the game in a braod sense in that with uno, multiple games have to be finished before a winner is found so that module handles the larger overarching game and the smaller game subsets.
The player module basically contains player characteristics and game actions and feeds them into the gameflow to update the deck.
With this game, the deck changes most because each user input affects all card piles including the user's. 
 

### API: Game
- Controls flow of actions among players in the game
- It is not specific to just Uno, nor does it need to know that it is playing a card game. It only assumes that it will be used with a game (that may or may not have multiple rounds) with multiple players and a score to play up until. 
- When it tells Player objects to perform a move, it does not know about the specific implementation of what the player does
- Use Case 1: To manage which player is currently actively making a move, the TurnManager interface is able to return the Player who is currently making a play, in addition to the next Player. 
- Use Case 2: To manage behavior once a round has ended, the end() method in the GameControl interface can be called. The two classes (Round and Game) that implement it will each have their own behaviors and will reference ScoreTracker to determine the winner of the round/game.
- Alternative design: We considered deciding the behavior of playing cards (checking if they are legal plays, activating special card effects) inside this API because the cards that are played often affect how the game behaves (e.g. who has the next turn). It seemed easier to check for those behaviors directly in the Game module, but this would make it less flexible. We decided to delegate responsibility to other modules (Player and Rules) in order to maintain extensibility. 

### API: Piles

- It structures the Cards into the various groups required for a game:
    - Hand
    - DrawPile
    - DiscardPile
- DrawPile uses a CardBuilder class to fill the Stack of Cards. To customize the Deck, the makeDeck() method can take a List of Actions which specifies the different types of Cards to use, allowing the user to choose which special cards to include (i.e. with the toggles)
- There are convenient, well-named methods to add and remove cards from the various Piles, as well as informative getters like returning the size of the Pile. The specificity provided by the abstraction allows the code to do all the heavy lifting, the constructors and data structures will be geared towards the subclass’s purpose.
- Use Case 1: To create a shuffle a standard DrawPile, all we would need to do is instantiate the Deck and then call the shuffle() method. By default, the DrawPile will call CardBuilder to return the Stack of normal Cards. DrawPile extends Deck which equips it with the shuffle() method.
- Use Case 2: To deal a Hand, take the DrawPile and call the drawCards() method. This will return a List of Cards that can then be passed into the Hand constructor to create a new Hand. To deal Hands for all Players, we can simply repeat this process for every player.
- Alternative design: we considered making Deck an interface instead of an abstract class. In this case, DiscardPile and DrawPile would implement both Deck and Pile. This would have let DiscardPile and DrawPile have separate implementations of the Pile methods. However, the shuffle method implementation would have overlapped, giving us repeated code. Ultimately, we decided there was enough overlap between DiscardPile and DrawPile that the abstract class would be successful.

[Pile Hierarchy](https://coursework.cs.duke.edu/compsci307_2020spring/final_team09/-/raw/master/pile_hierarchy.png)
