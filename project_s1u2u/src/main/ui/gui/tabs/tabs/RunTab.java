package ui.gui.tabs.tabs;

import model.Run;
import ui.gui.tabs.RunnerGUI;

import javax.swing.*;

// Represents a tab for user to add runs
// used some code from ReportTab class and HomeTab class in LongFormProblemStarters
public class RunTab extends Tab {
    private static final String DIST = "Please enter the distance of the run (in km):";
    private static final String TIME = "Please enter the duration of the run (in mins):";
    private static final String NAME = "Please enter a name for the run:";
    private static final String DATE = "Please enter the date of the run (DD/MM/YY):";

    private static final int TEXT_FIELD_COL = 80;
    private static final int TEXT_HEIGHT = 30;
    private static final int TEXT_WIDTH = 300;
    private static final int TEXT_X = 150;
    private static final int TEXT_SPACING = 40;

    private JTextField distField;
    private JTextField timeField;
    private JTextField nameField;
    private JTextField dateField;

    private JLabel congrats;


    // MODIFIES: this
    // EFFECTS: creates run tab with labels,text fields, and a button
    public RunTab(RunnerGUI controller) {
        super(controller);
        setLayout(null);


        placeLabels();
        placeTextFields();
        placeButton();
        placeCongrats();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds congrats message underneath button
    private void placeCongrats() {
        congrats = new JLabel("");
        add(congrats);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds labels
    private void placeLabels() {
        JLabel dist = new JLabel(DIST);
        dist.setBounds(10, 20, 500, 30);
        add(dist);

        JLabel time = new JLabel(TIME);
        time.setBounds(10, 120, 500, 30);
        add(time);

        JLabel name = new JLabel(NAME);
        name.setBounds(10, 240, 500, 30);
        add(name);

        JLabel date = new JLabel(DATE);
        date.setBounds(10, 400, 500, 30);
        add(date);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds text fields below each corresponding label
    private void placeTextFields() {
        distField = new JTextField(TEXT_FIELD_COL);
        distField.setBounds(TEXT_X, 20 + TEXT_SPACING, TEXT_WIDTH, TEXT_HEIGHT);

        timeField = new JTextField(TEXT_FIELD_COL);
        timeField.setBounds(TEXT_X, 140 + TEXT_SPACING, TEXT_WIDTH, TEXT_HEIGHT);

        nameField = new JTextField(TEXT_FIELD_COL);
        nameField.setBounds(TEXT_X, 280 + TEXT_SPACING, TEXT_WIDTH, TEXT_HEIGHT);

        dateField = new JTextField(TEXT_FIELD_COL);
        dateField.setBounds(TEXT_X, 420 + TEXT_SPACING, TEXT_WIDTH, TEXT_HEIGHT);

        add(distField);
        add(timeField);
        add(nameField);
        add(dateField);
    }

    // MODIFIES: this
    // EFFECTS: sets a congratulatory message of the number of user runs and shows it
    private void setCongrats() {
        int numRuns = getController().getUser().getRunningHistory().getNumRuns();
        congrats.setText("Nice! You have just logged run #" + numRuns + "! Congrats!");
        congrats.setBounds(175, 530, 500, 25);
        congrats.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds an enter button with a listener that makes a new run for the given info in text fields
    private void placeButton() {
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(250, 500, 100, 25);
        add(enterButton);
        enterButton.addActionListener(e -> {
            double dist = Double.parseDouble(distField.getText());
            double time = Double.parseDouble(timeField.getText());
            String name = nameField.getText();
            String date = dateField.getText();
            Run newRun = new Run(dist, time, date, name);
            getController().getUser().getRunningHistory().addRun(newRun);
            setCongrats();
            getController().updateRunHistoryTab();
            wipeFields();
        });
    }

    // MODIFIES: this
    // EFFECTS: clears all user input
    private void wipeFields() {
        distField.setText("");
        nameField.setText("");
        timeField.setText("");
        dateField.setText("");
    }

}
