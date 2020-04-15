package ooga.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnoTest {

    Uno uno = new Uno();

    @BeforeEach
    void init(){
        uno.start();
    }

    @Test
    void testPlayCard() {

    }

    @Test
    void drawCard() {
    }

    @Test
    void getUserHand() {
    }
}