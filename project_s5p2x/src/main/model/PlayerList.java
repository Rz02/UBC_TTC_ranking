package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represent a list of players.
public class PlayerList implements Writable {
    private final List<Player> playerList;

    // Create a new Arraylist in the constructor.
    public PlayerList() {
        playerList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds a player in the list.
    public void addPlayer(Player player) {
        EventLog.getInstance().logEvent(new Event("Added Player: " + player.getName()));
        playerList.add(player);
    }

    // EFFECT: return the size of the list.
    public int getNumberOfPlayers() {
        return playerList.size();
    }

    // EFFECT: return the names of the players.
    public List<String> listPlayer() {
        int i;
        List<String> nameList = new ArrayList<>();
        for (i = 0; i < playerList.size(); i++) {
            nameList.add(playerList.get(i).getName());
        }
        return nameList;
    }

    // EFFECT: return the list of players.
    public List<Player> players() {
        return playerList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns things in this playerList as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Player p: playerList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}