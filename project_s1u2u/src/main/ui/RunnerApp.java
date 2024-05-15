package ui;


import model.Club;
import model.Run;
import model.Runner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Run Tracker application
// used some code from the TellerApp class in AccountNotRobust
// used some code from the WorkRoomApp class in JsonSerializationDemo
public class RunnerApp {
    private static final String JSON_FILE = "./data/runnerProfile.json";
    private Runner user;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the run tracker application
    public RunnerApp() throws FileNotFoundException {
        runRunner();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRunner() {
        boolean keepGoing = true;
        String command = null;
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);

        input = new Scanner(System.in);
        startUp();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("See you later! Get runnin!");
    }

    // EFFECTS: displays the main menu of options to user
    public void displayMainMenu() {
        System.out.println("What would you like to do next? "
                + "Press the indicated key to do any of the following actions");
        System.out.println("1 -> log a run");
        System.out.println("2 -> create a new club");
        System.out.println("3 -> view run history");
        System.out.println("4 -> view run statistics");
        System.out.println("5 -> view affiliated clubs");
        System.out.println("s -> save a previous running profile");
        System.out.println("l -> load a previous running profile");
        System.out.println("q -> quit");
    }

    // EFFECTS: displays the club menu of options to user
    public void createAClub() {
        System.out.println("To create a club please follow the directions below");

        System.out.println("Please enter a name:");
        String name = input.next();

        System.out.println("Nice choice!");

        System.out.println("Please enter the maximum number of members for this club");
        int maxNum = input.nextInt();

        Club newClub = new Club(name, maxNum, user.getUsername());
        user.getClubs().add(newClub);
        System.out.println("Great, you have successfully created a club!");

    }

    // MODIFIES: user
    // EFFECTS: displays the add run menu and adds run to user run history
    public void addARunMenu() {
        System.out.println("Please enter the distance of the run (in km):");
        double distance = input.nextDouble();

        System.out.println("Please enter the duration of the run (in mins):");
        double time = input.nextDouble();

        String name = namePrompt();

        String date = datePrompt();

        Run newRun = new Run(distance, time, date, name);

        user.getRunningHistory().addRun(newRun);
        int numRuns = user.getRunningHistory().getNumRuns();

        System.out.println("Nice! You have just logged run #" + numRuns + "! Congrats!");
    }

    // EFFECTS: prompts user for name
    private String namePrompt() {
        System.out.println("Please enter a name for the run (no spaces):");
        String name = "";
        name += input.next();
        return name;
    }

    // EFFECTS: prompts user for date
    private String datePrompt() {
        System.out.println("Please enter the date of the run (DD/MM/YY)");
        String date = input.next();
        return date;
    }

    // EFFECTS: displays the run history menu of options to user
    public void displayHistoryMenu() {
        System.out.println("\nHere are your previous runs:");
        System.out.println(user.getRunningHistory().getNames());
        System.out.println("To learn more about a specific run, enter the name of the desired run.");
        System.out.println("To return to the main menu, enter b");

    }

    // EFFECTS: displays run stats menu of options to user
    public void displayRunStats() {
        System.out.println("What would you like to see?");
        System.out.println("f -> fastest run");
        System.out.println("p -> fastest pace run");
        System.out.println("l -> longest run");
        System.out.println("b -> back to main menu");

    }

    // EFFECTS: displays instructions to user just entering the app
    public void startUp() {
        System.out.println("Hello, welcome to RunnerApp! Would you like to load a previous running file? (yes/no)");
        String command = input.next();
        if (command.equals("yes")) {
            loadRunner();
        } else {
            System.out.println("Please enter a username (no spaces) to begin:");
            String username = input.next();
            user = new Runner(username);
            welcome(username);
        }
    }

    // EFFECTS: welcomes user
    private void welcome(String username) {
        System.out.println("Welcome " + username);
    }

    // EFFECTS: reads user input to navigate main menu
    private void processCommand(String command) {
        if (command.equals("1")) {
            addARunMenu();
        } else if (command.equals("2")) {
            createAClub();
        } else if (command.equals("3")) {
            runHistorySubMenu();
        } else if (command.equals("4")) {
            runStatsSubMenu();
        } else if (command.equals("5")) {
            runClubsMenu();
        } else if (command.equals("s")) {
            saveRunner();
        } else if (command.equals("l")) {
            loadRunner();
        } else {
            System.out.println("Sorry I didn't get that, please pick from one of the options above.");
        }
    }


    // EFFECTS: reads user input to navigate stats menu
    private void processStatsCommand(String command) {
        if (command.equals("f")) {
            Run fast = user.getRunningHistory().getFastestRun();
            double fastestTime = fast.getTime();
            double fastestDistance = fast.getDistance();
            String fastestDate = fast.getDate();
            System.out.println("Your fastest time for a run is " + fastestTime + " mins. You ran this time over "
                    + fastestDistance + " km on " + fastestDate + ".");
        } else if (command.equals("p")) {
            Run paceRun = user.getRunningHistory().getFastestPaceRun();
            double pace = paceRun.getTime() / paceRun.getDistance();
            double paceDistance = paceRun.getDistance();
            String paceDate = paceRun.getDate();
            System.out.println("Your quickest pace for a run is " + pace + " mins per km. This was your average"
                    + " pace for a " + paceDistance + " km run you completed on " + paceDate + ".");
        } else if (command.equals("l")) {
            Run longest = user.getRunningHistory().getLongestRun();
            double longestTime = longest.getTime();
            double longestDistance = longest.getDistance();
            String longestDate = longest.getDate();
            System.out.println("Your longest run was " + longestDistance + " km long. You ran this distance in "
                    + longestTime + " mins on " + longestDate + ".");
        }
    }

    // EFFECTS: runs the run history sub menu
    private void runHistorySubMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayHistoryMenu();
            command = input.next();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                Run foundRun = user.getRunningHistory().findRun(command);
                double dist = foundRun.getDistance();
                double time = foundRun.getTime();
                String date = foundRun.getDate();
                System.out.println("Loading stats for " + command + "...");
                System.out.println("\nDistance = " + dist);
                System.out.println("Time = " + time);
                System.out.println("Date = " + date);
            }
        }
    }

    // EFFECTS: run the run stats sub menu
    private void runStatsSubMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayRunStats();
            command = input.next();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processStatsCommand(command);
            }
        }
    }

    // EFFECTS: saves runner profile (user) to file
    private void saveRunner() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("The running profile for " + user.getUsername() + " has been saved!");
        } catch (FileNotFoundException f) {
            System.out.println("Unable to save running profile to file: " + JSON_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads runner from file and replaces current user
    private void loadRunner() {
        try {
            user = jsonReader.read();
            System.out.println("Welcome back " + user.getUsername() + "!");
        } catch (IOException io) {
            System.out.println("Error: can't seem to read from file: " + JSON_FILE);
        }
    }

    // EFFECTS: runs the clubs menu of options
    private void runClubsMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayClubs();
            System.out.println("To return to the main menu, enter b");
            command = input.next();

            if (command.equals("b")) {
                keepGoing = false;
            }
//            else {
//                leaveClub(command);
//            }
        }
    }

    // EFFECTS: displays clubs that user has joined
    private void displayClubs() {
        System.out.println("Here are the clubs you have joined.");
        List<Club> clubs = user.getClubs();
        List<String> clubNames = new ArrayList<>();
        for (Club c : clubs) {
            clubNames.add(c.getName());
        }
        System.out.println(clubNames);
    }

//    // MODIFIES: this
//    // EFFECTS: removes user from club
//    private void leaveClub(String clubName) {
//        user.getClubs().remove(clubName);
//    }





    // EFFECTS: displays member names menu
   // public void viewMemberMenu() {
   //     boolean keepGoing = true;
   //     String command = input.nextLine();

   //     while (keepGoing) {
   //         System.out.println("To view the run history of a particular member, please enter their name. Otherwise"
   //                 + " enter b to exit.");
   //         if (command.equals("b")) {
   //             keepGoing = false;
   //         } else {
   //             Runner foundRunner = user.(command);
   //         }
   //     }

    //}

    //public void processMemberCommand(String command) {

    //}


}
