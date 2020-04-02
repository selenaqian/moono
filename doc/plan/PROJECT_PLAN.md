### Implementation Plan

Tess and Suomo plan to focus on the backend work, while Selena will focus on frontend. Mary will be working primarily on
the interactions between the frontend and the backend. Our responsibilities may shift as priorities change throughout the
project. Also, we will all be aware of what everyone else is working on so that we can all help each other out as needed.

#### APIs

- View
    - primary: Selena
    - handles visual user interface
- Game
    - primary: Mary
    - handles the way that the game is played - turns, drawing cards
- Cards
    - primary: Tess
    - handles the individual cards
- Piles
    - primary: Tess
    - handles the groupings of cards - e.g. draw pile, discard pile, hand
- Player
    - primary: Suomo
    - handles the player behavior - human players and AI players

#### Timeline

We have set preliminary responsibilities for features in sprints 1 and 2 but expect some of these to shift as we determine what other features
we might want or find useful. We have not yet determined who will do what for complete implementation because we'd like to evaluate our
progress as we go and will then have a better idea of what people have been working on and are most interested in and familiar with.

##### Sprint 1
- create card deck (initial setup) - Tess
- shuffle card deck - Tess
- get top card of deck - Tess
- deal a hand of cards - Suomo
- determine who goes first - Suomo
- choose a card to play - human - Suomo
- choose a card to play - AI - Suomo
- move turn to next player (simple order) - Mary
- change color of play (wild cards) - Mary
- draw a card, play if possible - Mary
- shuffle discard pile - Tess
- any player out of cards - end game - Mary
- classic card design/visuals - Selena
- welcome screen - Selena
- main gameplay visual - Selena
- click on card to play - Mary
- end of game/round screen - Selena

##### Sprint 2
- skip - Suomo
- reverse - Suomo
- draw 2 - Suomo
- wild draw 4 - Tess
- wild - Tess
- implement Deadly Uno rule - immediately lose if can't play - Tess
- multiple rounds of gameplay with score calculation - Mary
- display scores for all players - Selena
- save and resume playing a game - Mary
- multiple games at once - Mary
- call uno! (click button) penalty also involves backend - Mary
- game area editor - change a particular color/image - Selena
- rules selection - Selena
- special cards selection - Selena
- color theme selection - Selena

##### Complete
- dark mode
- Duke card skin
- space card skin
- implement Ascending Uno - must play same or higher #
- update rules selection
- draw entire deck
- trade hands with another player
- draw 4 everyone else
- discard 2 "trash"
- swap one card of your choice with random from someone else
- discard all of one color
- update special cards selection
- preferences
- preferences selection
- player profiles
- display and allow changes to player profiles
- social center
- social center UI
- save game data to web