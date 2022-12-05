package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a player having a name, a membership, and the results.
public class Player implements Comparable<Player>, Writable {

    private final String name;               // the player's name
    private final boolean membership;        // the player's membership
    private final List<Boolean> result;      // the player's results

    public static final int REWARD_SCORE = 1;
    public static final int WINNER_SCORE = 3;

    @Override
    public int compareTo(Player p) {
        return Integer.compare(this.getScore(), p.getScore());
    }

    // EFFECTS: name is set to playerName; membership is valid or not;
    //          result is a list of the results of the games.
    public Player(String playerName,
                  boolean member,
                  List<Boolean> results) {
        this.name = playerName;
        this.membership = member;
        this.result = results;
    }

    // EFFECT: get the name of the player.
    public String getName() {
        return this.name;
    }

    // EFFECT: get the score of the player.
    public int getScore() {
        return this.acc();
    }

    // EFFECT: get the membership of the player.
    public boolean getMembership() {
        return this.membership;
    }

    // EFFECT: get the results of the player.
    public List<Boolean> getResult() {
        return this.result;
    }

    // MODIFIES: score (a local variable), for a player's score
    // EFFECTS: if a player win one round, 3 points will be added;
    //          if a player doesn't win, 1 point will still be added.
    public int acc() {
        int score = 0;
        if (membership) {
            for (Boolean win : result) {
                if (win) {
                    score = score + WINNER_SCORE;
                } else {
                    score = score + REWARD_SCORE;
                }
            }
            return score;
        }
        return -1;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("member", membership);
        json.put("result", result);
        return json;
    }
}