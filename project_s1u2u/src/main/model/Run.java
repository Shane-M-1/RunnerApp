package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents a run having a distance (km), time (mins), date, name,
public class Run implements Writable {
    private double distance;
    private double time;
    private String date;
    private String name;

    // REQUIRES: distance > 0, time > 0,
    // EFFECTS: creates a new run with given distance (km), time (mins), date, name
    public Run(double distance, double time, String date, String name) {
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("distance", distance);
        json.put("date", date);
        json.put("time", time);
        return json;
    }

}
