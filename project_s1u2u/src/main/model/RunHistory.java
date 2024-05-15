package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a runner's collection of past runs, organized by when they were added in
public class RunHistory implements Writable {
    private final List<Run> runHistory;


    // EFFECTS: creates empty list of runs and an empty list of run names
    public RunHistory() {
        runHistory = new ArrayList<>();
    }

    // EFFECTS: returns the run in runHistory at given index
    public Run getNthRun(int index) {
        return runHistory.get(index);
    }

    // EFFECTS: returns number of runs inside runHistory
    public int getNumRuns() {
        return runHistory.size();
    }

    // EFFECTS: returns runHistory as a list of run names (strings)
    public List<String> getNames() {
        List<String> runNames = new ArrayList<>();
        for (Run r: runHistory) {
            runNames.add(r.getName());
        }
        return runNames;
    }

    // REQUIRES: given name must correspond to a run that exists inside runHistory
    // EFFECTS: finds and returns the matching run given the name of the run
    public Run findRun(String findRun) {
        boolean isFound = false;
        Run foundRun = null;
        int index = 0;

        while (!isFound) {
            Run currentRun = runHistory.get(index);
            if (findRun.equals(currentRun.getName())) {
                foundRun = currentRun;
                isFound = true;
            } else {
                index++;
            }
        }
        return foundRun;
    }


    // REQUIRES: runHistory.size() > 0
    // MODIFIES: EventLog.getInstance()
    // EFFECTS: returns the run with the shortest time in running history
    // if fastest run is a tie, returns most recently added fastest run;
    // also adds event to EventLog
    public Run getFastestRun() {
        Run fastest = runHistory.get(0);
        for (Run nextRun : runHistory) {
            if (nextRun.getTime() < fastest.getTime()) {
                fastest = nextRun;
            }
        }
        EventLog.getInstance().logEvent(new Event("Got the fastest run in the run history."));
        return fastest;
    }

    // REQUIRES: runHistory.size() > 0
    // MODIFIES: EventLog.getInstance()
    // EFFECTS: returns run with the fastest pace in run history
    // if fastest pace run is a tie, returns most recently added fastest run;
    // also adds event to EventLog
    public Run getFastestPaceRun() {
        Run fastest = runHistory.get(0);
        double fastestPace = fastest.getTime() / fastest.getDistance();

        for (Run nextRun : runHistory) {
            double pace = nextRun.getTime() / nextRun.getDistance();
            if (pace < fastestPace) {
                fastest = nextRun;
                fastestPace = fastest.getTime() / fastest.getDistance();
            }
        }
        EventLog.getInstance().logEvent(new Event("Got the fastest pace run in the run history."));
        return fastest;
    }

    // REQUIRES: runHistory.size() > 0
    // MODIFIES: EventLog.getInstance()
    // EFFECTS: returns the longest distance run in running history
    // if longest run is a tie, returns most recently added longest run;
    // also adds event to EventLog
    public Run getLongestRun() {
        Run longest = runHistory.get(0);

        for (Run nextRun : runHistory) {
            if (nextRun.getDistance() > longest.getDistance()) {
                longest = nextRun;
            }
        }
        EventLog.getInstance().logEvent(new Event("Got the longest distance run in the run history."));
        return longest;
    }

    // MODIFIES: this, EventLog.getInstance()
    // EFFECTS: adds given run to running history and adds an event to EventLog
    public void addRun(Run run) {
        runHistory.add(run);
        EventLog.getInstance().logEvent(new Event("Added Run #" + runHistory.size()
                + " since application has started!"));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("runHistory", runHistoryToJson());

        return json;
    }

    // Method was modelled after workroom.thingiesToJson() method in JsonSerializationDemo
    // EFFECTS: returns runs in runHistory as a JSONArray
    private JSONArray runHistoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Run r : runHistory) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }
}
