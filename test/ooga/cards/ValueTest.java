package ooga.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests the Value enum, specifically the getNumericValue() method.
 * @author Tess Noonan (tcn6)
 */
class ValueTest {

    /**
     * Tests getNumericValue() for basic Values (name is the number).
     */
    @Test
    void getNumericValueBasic() {
        assertEquals(0, Value.ZERO.getNumericValue());
        assertEquals(9, Value.NINE.getNumericValue());
    }

    /**
     * Tests getNumericValue() for special Values (name is different from the number).
     */
    @Test
    void getNumericValueSpecial() {
        assertEquals(10, Value.SKIP.getNumericValue());
        assertEquals(14, Value.WILD4.getNumericValue());
    }
}