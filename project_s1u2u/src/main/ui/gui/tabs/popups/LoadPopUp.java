package ui.gui.tabs.popups;

import ui.gui.tabs.exceptions.NewUserException;
import model.Runner;
import persistence.JsonReader;
import ui.gui.tabs.RunnerGUI;

import javax.swing.*;
import java.io.IOException;

// Represents a popup for a load prompt
// used some code from DialogDemo
public class LoadPopUp extends JOptionPane {
    private static final String DIALOG = "Would you like to load a previous running profile?";
    private static final String JSON_FILE = "./data/runnerProfile.json";
    private JsonReader jsonReader;
    private RunnerGUI controller;


    // EFFECTS: creates dialog with gif icon; throws NewUserException if user selects no option
    public LoadPopUp(Runner user, RunnerGUI controller) throws NewUserException {
        this.controller = controller;
        ImageIcon runningGIF = new ImageIcon("resources/ezgif.com-resize.gif");

        int choice = showConfirmDialog(controller, DIALOG, "Load?", YES_NO_OPTION, QUESTION_MESSAGE,
                runningGIF);

        if (choice == YES_OPTION) {
            jsonReader = new JsonReader(JSON_FILE);
            loadRunner(user);
        } else {
            throw new NewUserException();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads runner from file and replaces current user
    private void loadRunner(Runner user) {
        try {
            controller.loadUser(jsonReader);
            System.out.println("Welcome back " + user.getUsername() + "!");
        } catch (IOException io) {
            System.out.println("Error: can't seem to read from file: " + JSON_FILE);
        }
    }
}
