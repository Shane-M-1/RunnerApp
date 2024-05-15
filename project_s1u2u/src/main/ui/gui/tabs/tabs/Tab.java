package ui.gui.tabs.tabs;

import ui.gui.tabs.RunnerGUI;

import javax.swing.*;
import java.awt.*;

// Represents a tab that belongs in the sidebar of a RunnerGUI
// used some code from Tab class in LongFormProblemStarters
public abstract class Tab extends JPanel {
    private final RunnerGUI controller;

    // REQUIRES: RunnerGUI controller that holds this tab
    public Tab(RunnerGUI controller) {
        this.controller = controller;
    }

    // EFFECTS: creates and returns row with button included
    public JPanel formatButtonColumn(Button b) {
        GridLayout layout = new GridLayout(3,0);
        layout.setVgap(24);

        JPanel p = new JPanel();
        p.setLayout(layout);
        p.add(b);

        return p;
    }

    public RunnerGUI getController() {
        return controller;
    }
}
