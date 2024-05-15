package persistence;

import org.json.JSONObject;

// Specification and method was taken from JsonSerializationDemo
// Represents an interface that writes objects to JSON strings/objects
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
