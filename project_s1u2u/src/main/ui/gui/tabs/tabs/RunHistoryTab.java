package ui.gui.tabs.tabs;

import model.Run;
import ui.gui.tabs.RunnerGUI;
import ui.gui.tabs.popups.PopUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


// Represents a tab that displays the user's run history
// used some code from ReportTab class and HomeTab class in LongFormProblemStarters, and ListDialogDemo
public class RunHistoryTab extends Tab {
    private static final String HEADING = "'s Run History";
    private JScrollPane scroller;
    private JList<String> stringList;
    private Button fastest;
    private Button pace;
    private Button longest;

    private static final int FASTEST = 1;
    private static final int PACE = 0;
    private static final int LONGEST = -1;
    private static final int REGULAR = -2;


    // MODIFIES: this
    // EFFECTS: constructs a user's run history tab with buttons
    public RunHistoryTab(RunnerGUI controller) {
        super(controller);
        setLayout(null);

        stringList = displayList((ArrayList<String>) controller.getUser().getRunningHistory().getNames());
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    doubleClickAction(e, controller);
                }
            }
        };
        stringList.addMouseListener(mouseListener);

        scroller = new JScrollPane(stringList);
        scroller.setBounds(82, 90, 150, 100);
        add(scroller);

        placeHeading();
        placeButtons();
    }

    // MODIFIES: this
    // EFFECTS: formats and adds all buttons
    private void placeButtons() {
        fastestButton();
        paceButton();
        longestButton();

        JPanel panel = formatButtonColumn(fastest);
        panel.add(pace);
        panel.add(longest);
        panel.setBounds(255, 91, 110, 100);
        add(panel);
    }

    // EFFECTS: listens to a double click on stringList and brings up an info popup
    private void doubleClickAction(MouseEvent e, RunnerGUI controller) {
        int index = stringList.locationToIndex(e.getPoint());
        String runName = controller.getUser().getRunningHistory().getNames().get(index);
        Run foundRun = controller.getUser().getRunningHistory().findRun(runName);
        String name = foundRun.getName();
        double dist = foundRun.getDistance();
        double time = foundRun.getTime();
        String date = foundRun.getDate();
        new PopUp(name, dist, time, date, REGULAR, controller);
        stringList.clearSelection();
    }

    // EFFECTS: creates a get longest run button that displays the longest distance run in run history
    private void longestButton() {
        longest = new Button("Get Longest Run");
        longest.addActionListener(e -> {
            Run longestRun = getController().getUser().getRunningHistory().getLongestRun();
            String name = longestRun.getName();
            double time = longestRun.getTime();
            double dist = longestRun.getDistance();
            String date = longestRun.getDate();
            new PopUp(name, dist, time, date, LONGEST, getController());
        });
    }

    // EFFECTS: creates a heading (label) to be displayed at top of screen
    private void placeHeading() {
        JLabel header = new JLabel(getController().getUser().getUsername() + HEADING);
        header.setFont(new Font("Sans Serif Workhorse", Font. BOLD, 30));
        header.setBounds(10, 5, 1000, 50);
        this.add(header);
    }

    private JList<String> displayList(ArrayList<String> runNames) {
        return new JList<>(runNames.toArray(new String[0]));
    }

    // EFFECTS: creates a get fastest run button that displays a popup of fastest run in run history
    private void fastestButton() {
        fastest = new Button("Get Fastest Run");

        fastest.addActionListener(e -> {
            Run fastestRun = getController().getUser().getRunningHistory().getFastestRun();
            String name = fastestRun.getName();
            double time = fastestRun.getTime();
            double dist = fastestRun.getDistance();
            String date = fastestRun.getDate();
            new PopUp(name, dist, time, date, FASTEST, getController());
        });
    }

    // EFFECTS: creates a get fastest pace run button that displays a popup of fastest pace run in run history
    private void paceButton() {
        pace = new Button("Get Fastest Pace Run");
        pace.addActionListener(e -> {
            Run paceRun = getController().getUser().getRunningHistory().getFastestPaceRun();
            String name = paceRun.getName();
            double time = paceRun.getTime();
            double dist = paceRun.getDistance();
            String date = paceRun.getDate();
            new PopUp(name, dist, time, date, PACE, getController());
        });
    }
}
