## Description

When a draw pile has run out of cards, the empty pile is passed to the view and throws errors when JavaFX tries to render
the empty pile.

## Expected Behavior

Draw pile resets with cards from the discard pile, and the view never has to deal with an empty pile.

## Current Behavior

PileManager is correctly updating the draw pile when it is empty but the order of calls with notifyObservers() in Uno
means an empty draw pile is still getting passed to the view.

## Steps to Reproduce

 1. Set number of cards per player to 15
 2. As the human player, keep drawing cards on your turn until the deck is finished

## Failure Logs

Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
	at ooga.view.CardRender.updateCardRender(CardRender.java:54)
	at ooga.view.GameView.updateDiscardPile(GameView.java:309)
	at ooga.game.Uno.notifyPlayerObservers(Uno.java:174)
	at ooga.game.Uno.endTurn(Uno.java:144)
	at ooga.game.Uno.playCard(Uno.java:119)
	at ooga.game.UnoController.handleAIPlay(UnoController.java:148)
	at ooga.game.UnoController.step(UnoController.java:91)
	at ooga.game.UnoController.lambda$setupTimeline$0(UnoController.java:107)


## Hypothesis for Fixing the Bug

Modify the drawCard() method in PileManager so that if a draw is performed and the pile is empty, then it will reset the pile.
Currently it always draws cards and only checks that the pile is empty the next time the method is called.