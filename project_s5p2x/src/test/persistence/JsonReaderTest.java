package persistence;

import model.Player;
import model.PlayerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PlayerList pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlayerList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlayerList.json");
        try {
            PlayerList pl = reader.read();
            assertEquals(0, pl.getNumberOfPlayers());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlayerList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlayerList.json");
        try {
            PlayerList pl = reader.read();
            List<Player> players = pl.players();
            assertEquals(2, pl.getNumberOfPlayers());
            checkPlayer("Rocky", true, Arrays.asList(false, true), players.get(0));
            checkPlayer("Wally", false, Arrays.asList(true, true), players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
