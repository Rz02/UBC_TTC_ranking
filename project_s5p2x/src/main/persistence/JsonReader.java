package persistence;

import model.Player;
import model.PlayerList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads playerList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PlayerList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayerList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playerList from JSON object and returns it
    private PlayerList parsePlayerList(JSONObject jsonObject) {
        PlayerList pl = new PlayerList();
        addPlayers(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses players from JSON object and adds them to playerList
    private void addPlayers(PlayerList pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json: jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(pl, nextPlayer);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses player from JSON object and adds it to playerList
    private void addPlayer(PlayerList pl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean member = jsonObject.getBoolean("member");
        ArrayList<Boolean> result = new ArrayList<>();
        JSONArray results = jsonObject.getJSONArray("result");
        for (int i = 0; i < results.length(); i++) {
            result.add(results.getBoolean(i));
        }
        Player player = new Player(name, member, result);
        pl.addPlayer(player);
    }
}