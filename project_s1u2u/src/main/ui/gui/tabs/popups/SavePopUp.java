package ui.gui.tabs.popups;

import model.Runner;
import persistence.JsonWriter;
import ui.gui.tabs.RunnerGUI;

import javax.swing.*;
import java.io.FileNotFoundException;

// Represents a popup that displays a prompt for whether the user wants to save profile
public class SavePopUp extends JOptionPane {
    private static final String DIALOG = "Would you like to save before exiting?";
    private static final String JSON_FILE = "./data/runnerProfile.json";
    private JsonWriter jsonWriter;
    //private JsonReader jsonReader;

    // EFFECTS: creates save popup and saves runner profile if user selects yes option
    public SavePopUp(RunnerGUI controller) {

        ImageIcon runningGIF = new ImageIcon("resources/ezgif.com-resize.gif");
        int choice = showConfirmDialog(controller, DIALOG, "Save?", YES_NO_OPTION, QUESTION_MESSAGE, runningGIF);

        if (choice == YES_OPTION) {
            jsonWriter = new JsonWriter(JSON_FILE);
            //jsonReader = new JsonReader(JSON_FILE);
            saveRunner(controller.getUser());
        }
    }

    // EFFECTS: saves runner profile (user) to file
    private void saveRunner(Runner user) {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            //System.out.println("The running profile for " + user.getUsername() + " has been saved!");
        } catch (FileNotFoundException f) {
            System.out.println("Unable to save running profile to file: " + JSON_FILE);
        }
    }

}
