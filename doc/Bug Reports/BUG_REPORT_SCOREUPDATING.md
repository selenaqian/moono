Note: filled this out after because didn't notice to do it before. sorry.

## Description

Score in GameView wouldn't update after a round finished.

## Expected Behavior

After a round, score displays should show non-zero values except for the player that won.

## Current Behavior

Scores all still display as 0.

## Steps to Reproduce

 1. Play the game to the end of the round.
 1. Click the next round button.
 1. Bug should now be seen.

## Failure Logs

N/A

## Hypothesis for Fixing the Bug

A test that clicks on the next round button then checks the elements in the scene should be able to tell if the scene has
advanced and if it shows the correct scores.

The code is likely not updating the scores properly, so I need to determine where that updating is happening and fix the
order that it happens in.