package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {
    private Player testPlayer_0;
    private Player testPlayer_1;
    private Player testPlayer_2;

    @BeforeEach
    public void setUp() {
        testPlayer_0 = new Player(
                "Jack",
                true,
                Arrays.asList(true, true));
        testPlayer_1 = new Player(
                "Rocky",
                true,
                Arrays.asList(true, false));
        testPlayer_2 = new Player(
                "Harry",
                false,
                Arrays.asList(true, true));
    }

    @Test
    public void testConstructor() {
        assertEquals(testPlayer_1.getName(), "Rocky");
        assertEquals(testPlayer_1.getScore(), 4);
        assertTrue(testPlayer_1.getMembership());
        assertEquals(testPlayer_1.getResult(), Arrays.asList(true,false));
    }

    @Test
    public void testAcc() {
        assertEquals(testPlayer_0.acc(), 6);
        assertEquals(testPlayer_1.acc(), 4);
        assertEquals(testPlayer_2.acc(), -1);
    }

    @Test
    public void testCompareTo() {
        assertTrue(testPlayer_0.compareTo(testPlayer_1) > 0);
        assertTrue(testPlayer_1.compareTo(testPlayer_2) > 0);
        assertFalse(testPlayer_0.compareTo(testPlayer_2) < 0);
        assertFalse(testPlayer_1.compareTo(testPlayer_2) < 0);
    }
}