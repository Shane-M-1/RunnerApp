package ui.gui.tabs.popups;

import ui.gui.tabs.RunnerGUI;

import javax.swing.*;

// Represents a popup on startup for a new user
public class StartUpPopUp extends JOptionPane {
    private static final String DIALOG1 = "Hello, welcome to RunnerApp!";
    private static final String DIALOG2 = "Please enter a username to begin!";

    // EFFECTS: creates a startup popup
    public StartUpPopUp(RunnerGUI controller) {
        String input = showInputDialog(controller, DIALOG1 + " " + DIALOG2);

        if (input != null) {
            controller.startUp(input);
        } else {
            controller.loadOrStartUp();
        }

    }


}
