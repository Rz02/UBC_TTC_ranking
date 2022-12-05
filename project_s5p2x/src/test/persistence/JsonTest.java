package persistence;

import model.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(String name,
                               Boolean member,
                               List<Boolean> result,
                               Player player) {
        assertEquals(name, player.getName());
        assertEquals(member, player.getMembership());
        assertEquals(result, player.getResult());
    }
}
