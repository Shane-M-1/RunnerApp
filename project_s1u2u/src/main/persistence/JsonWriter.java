package persistence;



import model.Event;
import model.EventLog;
import model.Runner;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Class is modelled after JsonWriter class in JsonSpecializationDemo
// Represents a writer that writes JSON representation of runner to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destinationFile;

    // EFFECTS: creates a new JsonWriter to write to given destination file
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens writer (creates a new writer to use with this.destinationFile to write in); if
    // this.destinationFile cannot be opened for writing, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of runner to destinationFile
    public void write(Runner r) {
        JSONObject json = r.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
        EventLog.getInstance().logEvent(new Event("Saved running profile!"));
    }
}
