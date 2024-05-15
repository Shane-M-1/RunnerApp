package ui.gui.tabs.tabs;

import model.Runner;
import ui.gui.tabs.RunnerGUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;

// Represents a tab for the main menu of the application
public class MainMenuTab extends Tab {
    private static final String MORNING_MSG = "Good Morning ";
    private static final String AFTERNOON_MSG = "Good Afternoon ";
    private static final String EVENING_MSG = "Good Evening ";
    private static String subMsg;
    private Runner user;
    private int hourOfDay;

    // MODIFIES: this
    // EFFECTS: constructs a main menu with a greeting and a gif
    public MainMenuTab(RunnerGUI controller) {
        super(controller);

        this.user = getController().getUser();
        setLayout(null);
        Date currentTime = Calendar.getInstance().getTime();
        hourOfDay = currentTime.getHours();

        placeWelcome();
        placeMessage();
        placeGIF();

    }

    // EFFECTS: creates welcome message at top of tab
    private void placeWelcome() {
        JLabel greeting = new JLabel(getMessageForTime(hourOfDay) + user.getUsername() + ",");
        greeting.setFont(new Font("Sans Serif Workhorse", Font. BOLD, 30));
        greeting.setBounds(45, 10, 600, 50);

        this.add(greeting);
    }


    // EFFECTS: creates a short message below gif (at bottom of tab)
    private void placeMessage() {
        JLabel message = new JLabel(subMsg);
        message.setFont(new Font("Sans Serif Workhorse", Font. BOLD, 20));
        message.setBounds(55, 425, 600, 100);
        add(message);
    }

    // EFFECTS: returns the appropriate string depending on the hour of the local time
    private String getMessageForTime(int hourOfDay) {
        if (hourOfDay < 12) {
            subMsg = "Went for a morning jog?";
            return MORNING_MSG;
        } else if (hourOfDay > 17) {
            subMsg = "Did you complete any runs today?";
            return EVENING_MSG;
        } else {
            subMsg = "Back to log some more runs?";
            return AFTERNOON_MSG;
        }
    }

    // EFFECTS: places gif between greeting and message
    private void placeGIF() {
        JLabel image = new JLabel();
        ImageIcon runningGIF = new ImageIcon("resources/ezgif.com-resize-BIG.gif");
        image.setIcon(runningGIF);
        image.setBounds(50, 85, 400, 300);
        add(image, CENTER_ALIGNMENT);
    }

}
