package ui.gui.tabs.popups;

import ui.gui.tabs.RunnerGUI;

import javax.swing.*;
import java.awt.*;


// Represents a general info pop up that displays info on user's run
// used some code from DialogDemo
public class PopUp extends JDialog {
    private static final String DIST = "Distance = ";
    private static final String TIME = "Time = ";
    private static final String DATE = "Date = ";

    private static final int FASTEST = 1;
    private static final int PACE = 0;
    private static final int LONGEST = -1;
    //private static final int REGULAR = -2;

    private final RunnerGUI controller;
    private String msg;
    private String title;


    // EFFECTS: creates pop up displaying the given run stats depending on given type (FASTEST, PACE, LONGEST, REGULAR)
    public PopUp(String name, double dist, double time, String date, int type, RunnerGUI controller) {
        this.controller = controller;

        if (type == FASTEST) {
            buildFastestMsg(name, time, dist, date);
            displaySelected();
        } else if (type == PACE) {
            buildPaceMsg(name, time, dist, date);
            displaySelected();
        } else if (type == LONGEST) {
            buildLongestMsg(name, time, dist, date);
            displaySelected();
        } else {
            buildRegMsg(name, time, dist, date);
            displaySelected();
        }
    }

    // MODIFIES: this
    // EFFECTS: completes title and msg strings with given run info
    private void buildFastestMsg(String name, double time, double dist, String date) {
        title = "Fastest Run: " + name;
        msg = "<html><p align=left>" + "Your fastest time for a run is " + time + " mins.<br>"
                + "You ran this time over "
                + dist + " km on " + date + "!";
    }

    // MODIFIES: this
    // EFFECTS: completes title and msg strings with given run info
    private void buildLongestMsg(String name, double time, double dist, String date) {
        title = "Longest Distance Run: " + name;
        msg = "<html><p align=left>" + "Your longest run was " + dist + " km long. <br> You ran this distance in "
                + time + " mins on " + date + ".";
    }

    // MODIFIES: this
    // EFFECTS: completes title and msg strings with given run info
    private void buildPaceMsg(String name, double time, double dist, String date) {
        title = "Fastest Pace Run: " + name;
        double pace = time / dist;
        msg = "<html><p align=left>" + "Your quickest pace for a run is " + pace + " mins per km.<br>"
                + "This was your average"
                + " pace for a " + dist + " km run <br> you completed on " + date + ".";
    }

    // MODIFIES: this
    // EFFECTS: completes title and msg strings with given run info
    private void buildRegMsg(String name, double time, double dist, String date) {
        title = "Run Stats for " + name;
        msg = "<html><p align=left>"
                + DIST + dist + "<br>"
                + TIME + time + "<br>"
                + DATE + date;
    }

    // MODIFIES: this
    // EFFECTS: creates dialog with title and msg
    private void displaySelected() {
        new JDialog(controller, title);
        addAndShowDialog(new JLabel(msg));
    }

    // MODIFIES: this
    // EFFECTS: creates panel and adds given jl to dialog
    private void addAndShowDialog(JLabel jl) {
        JPanel contentJP = new JPanel();
        contentJP.add(jl);
        jl.setFont(new Font("Sans Serif Workhorse", Font. PLAIN, 12));
        contentJP.setOpaque(true);
        setContentPane(contentJP);
        setSize(new Dimension(265, 250));
        setLocationRelativeTo(controller);
        setVisible(true);
    }
}
