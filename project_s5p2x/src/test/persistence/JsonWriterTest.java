package persistence;

import model.Player;
import model.PlayerList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            PlayerList pl = new PlayerList();
            JsonWriter writer = new JsonWriter("./data/my/\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlayerList() {
        try {
            PlayerList pl = new PlayerList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlayerList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlayerList.json");
            pl = reader.read();
            assertEquals(0, pl.getNumberOfPlayers());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlayerList() {
        try {
            PlayerList pl = new PlayerList();
            pl.addPlayer(new Player(
                    "Rocky",
                    true,
                    Arrays.asList(false, true)
            ));
            pl.addPlayer(new Player(
                    "Wally",
                    false,
                    Arrays.asList(true, true)
            ));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayerList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayerList.json");
            pl = reader.read();
            List<Player> players = pl.players();
            assertEquals(2, pl.getNumberOfPlayers());
            checkPlayer("Rocky", true, Arrays.asList(false, true), players.get(0));
            checkPlayer("Wally", false, Arrays.asList(true, true), players.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
