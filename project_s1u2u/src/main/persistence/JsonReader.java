package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Class is modelled after JsonReader class in JsonSpecializationDemo
// Represents a reader that reads runner from JSON data stored in file
public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: reads runner from file and returns it;
    // throws IOException if error occurs from reading file
    public Runner read() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded previous running profile."));
        return parseRunner(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    public String readFile(String sourceFile) throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(sourceFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> jsonStringBuilder.append(s));
        }
        return jsonStringBuilder.toString();
    }

    // EFFECTS: parses runner from JSON object and returns it
    public Runner parseRunner(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        Runner r = new Runner(username);
        parseRunHistory(r, jsonObject);
        addClubs(r, jsonObject);
        return r;
    }

    // EFFECTS: parses runHistory from JSON object
    public void parseRunHistory(Runner r, JSONObject jsonObject) {
        JSONObject runHistory = jsonObject.getJSONObject("runHistory");
        JSONArray runHistoryField = runHistory.getJSONArray("runHistory");
        for (Object json : runHistoryField) {
            JSONObject nextRun = (JSONObject) json;
            addRun(r, nextRun);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses run from JSON object and adds to r.getRunningHistory()
    public void addRun(Runner r, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double distance = jsonObject.getDouble("distance");
        double time = jsonObject.getDouble("time");
        String date = jsonObject.getString("date");
        Run run = new Run(distance, time, date, name);
        r.getRunningHistory().addRun(run);
    }

    // MODIFIES: r
    // EFFECTS: parses clubs from JSON object and adds them to runner
    public void addClubs(Runner r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clubs");
        for (Object json : jsonArray) {
            JSONObject nextClub = (JSONObject) json;
            addClub(r, nextClub);
        }

    }

    // MODIFIES: r
    // EFFECTS: parses club from JSON object and adds to runner
    public void addClub(Runner r, JSONObject jsonObject) {
        String clubName = jsonObject.getString("clubName");
        int maxNumMembers = jsonObject.getInt("maxNumMembers");
        String leader = jsonObject.getString("leader");
        Club club = new Club(clubName, maxNumMembers, leader);
        JSONArray jsonArray = jsonObject.getJSONArray("members");
        jsonArray.remove(0);
        for (Object string : jsonArray) {
            String nextMember = (String) string;
            club.addMember(nextMember);
        }
        r.getClubs().add(club);
    }
}