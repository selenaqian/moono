## Description

When the human user plays a wild card, the wild card is not removed from their hand and they must play another card.

## Expected Behavior

The wild card is added to the discard pile, and the next player has their turn.

## Current Behavior

The wild card color selection appears, but once the user selects a color the view is not updating correctly to add
the card to the pile.

## Steps to Reproduce

 1. Start a new game with WIlD or WILD4 cards selected
 2. Play a wild card and select a color

## Failure Logs

N/A

## Hypothesis for Fixing the Bug

In UnoActionApplier, check to make sure the turnManager is getting updated correctly and that calls to notifyPlayerObservers
are being made.
