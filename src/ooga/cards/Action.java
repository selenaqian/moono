package ooga.cards;

/**
 * This class defines the different Actions Cards can posses:
 * NONE - do nothing (0 - 9)
 *
 * The following will be added in Sprint 2:
 * SKIP - next Player = current Player + 2 (just this once)
 * REVERSE = next Player = current Player - 1 (continues in the new direction)
 * DRAW2 = next Player draws 2 cards and skips turn
 * WILD = choose new color
 * WILD4 = choose new color, next Player draws 4 cards and skips turn
 *
 */
public enum Action {
    NONE;
}
