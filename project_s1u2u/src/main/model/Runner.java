package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a user's profile having a username, running history, a list of running clubs they belong to
public class Runner implements Writable {
    private final String username;
    private final RunHistory runHistory;
    private final List<Club> clubs;


    // EFFECTS: creates a Runner with given username, empty runHistory, and empty clubs
    public Runner(String username) {
        this.username =  username;
        runHistory = new RunHistory();
        clubs = new ArrayList<>();

    }

    public String getUsername() {
        return username;
    }

    public RunHistory getRunningHistory() {
        return runHistory;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    // MODIFIES: this
    // EFFECTS: adds given club to a runners joined clubs
    public void joinClub(Club club) {
        clubs.add(club);
    }

    // MODIFIES: this
    // EFFECTS: removes given club from runners joined clubs
    public void leaveClub(Club club) {
        clubs.remove(club);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("clubs", clubsToJson());
        json.put("runHistory", runHistory.toJson());

        return json;
    }

    // Method was modelled after workroom.thingiesToJson() method in JsonSerializationDemo
    // EFFECTS: returns this.clubs as a JSONArray
    private JSONArray clubsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Club c : clubs) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}


