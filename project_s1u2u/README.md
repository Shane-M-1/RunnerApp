# My Personal Project Proposal 

### What will the application do?
The application I would like to design is a run tracker. More specifically, this app would allow a user to 
enter a run they have been on with a time and distance. A unique aspect to this app is that users can create "clubs"
and can invite friends to their club where both users can view each others 
running statistics like how many runs someone has been on, their average speed/pace of these runs, and
any improvements made in speed, endurance, or weekly runs. 
The purpose of clubs is to provide a more competitive but friendly nature to running which could increase someone's 
motivation
to running; if a user sees that their friend is making better progress in their speed, then they might be more inclined
to run more often to catch up to that friend. The intentions of this app is to increase interest in cardio fitness by
making running into almost a game-like endeavour, since cardio can be very undesirable to many.

### Who will use it?
This application can be used by anyone who is trying to get into running, or by groups of 
advanced runners looking to add a fun perspective to running. This app is also a good option for those trying to
improve their fitness but having motivational issues.

### Why is this project of interest to you?
I am someone who would like to get into running but find it hard to stay motivated anymore. I used to be a frequent
runner to stay in shape when I used to play sports, however, since I stopped playing I struggled to give myself reasons
to engage in any cardio at all. I feel that if running had some friendly competition to it, I would be a much more avid 
runner 
since I would be motivated beat my friends.

### User Stories:
- As a user, I want to be able to add my run to my run history and specify the time and distance of the run and
optionally, a name for the run (perhaps I have a usual run I go on).
- As a user, I want to be able to create a club with an arbitrary maximum number of members.
- As a user, I want to be able to view my run history.
- As a user, I want to be able to view my run statistics (such as my fastest run or my longest run).
- As a user, I want to have an option to save my current running profile (the state of my current Runner).
- As a user, I want to be prompted if I want to load a previous running profile(a previous Runner) on the startup of 
- the app and still have an option later on to load a previous running profile.


# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the 
"Add Run" tab, filling out the required text fields (instructions for them are on the page) and then clicking the 
enter button once all fields are completed (do not press any sooner).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the
"Run History" tab and clicking any of the buttons that are displayed next to the Run History panel. These buttons will
bring up another tab that displays the info requested by the user (the fastest/fastest pace/longest run in 
run history). Additionally, when there is at least one run in a user's run history, you can navigate to the 
"Run History" tab and double-click on any run in the scroll panel and a popup of that run's information will be 
brought up.
- You can locate my visual component on the main menu. The component is a gif (ImageIcon). Additionally, the icon in my
load and save prompts use the same gif.
- You can save the state of my application by closing the application. You will be prompted to save if you want.
- You can reload the state of my application by opening the application. You will be prompted to load a previous profile
if you want or create a new user.

# Phase 4: Task 2
A sample of events that can occur whilst the application is run:

Sun Nov 26 19:49:30 PST 2023
Added Run #1 since application has started!

Sun Nov 26 19:49:30 PST 2023
Added Run #2 since application has started!

Sun Nov 26 19:49:34 PST 2023
Got the fastest run in the run history.

Sun Nov 26 19:49:38 PST 2023
Got the fastest pace run in the run history.

Sun Nov 26 19:49:46 PST 2023
Got the longest distance run in the run history.

# Phase 4: Task 3
One change I would make to reduce coupling in project structure is to remove the association between MainMenuTab and 
Runner. Currently, MainMenuTab has a single field of a Runner and this was design feature was simply for ease of access
for accessing the user (which is of type Runner). However, this unnecessarily increases the coupling between my classes.
Instead, since MainMenuTab extends Tab, and Tab has a field of RunnerGUI, and RunnerGUI has a field of Runner, the 
MainMenuTab's association with Runner can be erased and the Runner can be accessed through the use of getter methods 
rather than having a field.

Another change I could make is to introduce a new class that has an association with Runner. Currently, both of my UI
classes -- RunnerGUI and RunnerApp -- each have a field of Runner. A potential refactoring strategy to reduce coupling 
and incorporate a single point of control for that Runner field would be to introduce an abstract RunnerUI class that
has a field of Runner. The Runner field in RunnerGUI and in RunnerApp would be removed, and instead they can extend
the new abstract RunnerUI class which means they would each inherit that field. Of course, the new RunnerUI class would 
also incorporate any shared behaviour between RunnerGUI and RunnerApp to remove any duplicated code between the two
subclasses.
#   R u n n e r A p p  
 