package ui.gui.tabs;

import model.Event;
import model.EventLog;
import ui.gui.tabs.exceptions.NewUserException;
import model.Runner;
import persistence.JsonReader;
import ui.gui.tabs.popups.LoadPopUp;
import ui.gui.tabs.popups.SavePopUp;
import ui.gui.tabs.popups.StartUpPopUp;
import ui.gui.tabs.tabs.MainMenuTab;
import ui.gui.tabs.tabs.RunHistoryTab;
import ui.gui.tabs.tabs.RunTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

// used some code from SmartHomeUI class in LongFormProblemStarters
// Represents GUI for RunnerApp
public class RunnerGUI extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;
    private Runner user;

    private JPanel runHistoryTab;

    //MODIFIES: this
    //EFFECTS: creates RunnerGUI, loads all tabs, and displays sidebar of those tabs
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        new RunnerGUI();
    }

    //MODIFIES: this
    //EFFECTS: creates RunnerGUI, loads all tabs, and displays sidebar of those tabs
    private RunnerGUI() throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        super("RunnerApp");

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setBounds(360, 140, WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //start
        user = new Runner("Shane");

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.RIGHT);
        sidebar.setPreferredSize(new Dimension(30, HEIGHT));

        loadTabs();
        add(sidebar);
        sidebar.setVisible(false);

        setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: creates all tabs and adds them to sidebar
    public void loadTabs() {
        JPanel mainMenuTab = new MainMenuTab(this);
        runHistoryTab = new RunHistoryTab(this);
        JPanel addRunTab = new RunTab(this);

        sidebar.add(mainMenuTab, 0);
        sidebar.setTitleAt(0, "Main Menu");
        sidebar.add(runHistoryTab, 1);
        sidebar.setTitleAt(1, "Run History");
        sidebar.add(addRunTab, 2);
        sidebar.setTitleAt(2, "Add Run");
    }


    public Runner getUser() {
        return user;
    }

    //MODIFIES: this
    //EFFECTS: refreshes run history tab
    public void updateRunHistoryTab() {
        sidebar.remove(runHistoryTab);
        runHistoryTab = new RunHistoryTab(this);
        sidebar.add(runHistoryTab,1);
        sidebar.setTitleAt(1, "Run History");
    }

    @Override
    //MODIFIES: this
    //EFFECTS: when application starts, loads the load prompt
    // when application closes, loads the save prompt, and prints EventLog to console
    public void processWindowEvent(final WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            new SavePopUp(this);
            printLog(EventLog.getInstance());
            super.processWindowEvent(e);
        } else if (e.getID() == WindowEvent.WINDOW_OPENED) {
            loadOrStartUp();
            updateRunHistoryTab();
            super.processWindowEvent(e);
        }
        super.processWindowEvent(e);
    }

    private void printLog(EventLog eventLog) {
        StringBuilder s = new StringBuilder();
        for (Event e : eventLog) {
            s.append(e.toString()).append("\n\n");
        }
        System.out.println(s);
    }

    //MODIFIES: this
    //EFFECTS: loads load-prompt and handles if user is a new user
    public void loadOrStartUp() {
        try {
            new LoadPopUp(user, this);
        } catch (NewUserException ex) {
            new StartUpPopUp(this);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates new user profile and reloads tabs
    public void startUp(String username) {
        this.user = new Runner(username);
        reload();
        sidebar.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: reloads tabs by removing from sidebar and adding them again
    private void reload() {
        sidebar.removeAll();
        loadTabs();
    }

    // MODIFIES: this
    // EFFECTS: loads runner from file and replaces current user
    public void loadUser(JsonReader jsonReader) throws IOException {
        this.user = jsonReader.read();
        reload();
        sidebar.setVisible(true);
    }

//    private void setWindowsLookAndFeel() throws UnsupportedLookAndFeelException, ClassNotFoundException,
//            InstantiationException, IllegalAccessException {
//        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//            if ("Windows".equals(info.getName())) {
//                UIManager.setLookAndFeel(info.getClassName());
//                JFrame.setDefaultLookAndFeelDecorated(true);
//            }
//        }
//    }


    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//    List list = new ArrayList<String>();
//        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//        list.add(info.getName());
//        if ("Windows".equals(info.getName())) {
//            UIManager.setLookAndFeel(info.getClassName());
//            JFrame.setDefaultLookAndFeelDecorated(true);
//        }
//    }
//        System.out.println(list);

}
