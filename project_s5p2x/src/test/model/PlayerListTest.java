package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerListTest {
    private PlayerList testPlayerList0;
    private Player testPlayer_0;
    private Player testPlayer_1;

    @BeforeEach
    public void setUp() {
        testPlayerList0 = new PlayerList();
        testPlayer_0 = new Player(
                "Jack",
                true,
                Arrays.asList(true, true));
        testPlayer_1 = new Player(
                "Rocky",
                true,
                Arrays.asList(true, false));
    }

    @Test
    public void testAddPlayer() {
        testPlayerList0.addPlayer(testPlayer_0);
        assertTrue(testPlayerList0.players().contains(testPlayer_0));
    }

    @Test
    public void testGetNumbersOfPlayers() {
        testPlayerList0.addPlayer(testPlayer_0);
        assertEquals(testPlayerList0.getNumberOfPlayers(), 1);
        testPlayerList0.addPlayer(testPlayer_1);
        assertEquals(testPlayerList0.getNumberOfPlayers(), 2);
    }

    @Test
    public void testListPlayers() {
        testPlayerList0.addPlayer(testPlayer_0);
        assertEquals(testPlayerList0.players().get(0).getName(),
                "Jack");
        testPlayerList0.addPlayer(testPlayer_1);
        assertEquals(testPlayerList0.listPlayer(),
                Arrays.asList("Jack", "Rocky"));
    }
}