# monster-fighter

This project is been created and maintained by Daniel Bishop and Jake Wilson. 
This game is being created as a project for our Software Engineering class: SENG201.

The game is a single person game where the user collects Monsters and uses them to fight other monsters during the day.


To Enter the program from the command line.

Navigate to the project root directory. The Directory contains this README, monster-fighter, bin etc

Run the following command to compile the source code and place it into the bin directory

	javac -d bin -cp monster-fighter monster-fighter/src/*/*.java
	
Run the following command To launch the application as a GUI (Graphical User Interface)

	java -cp bin gui.Main

(GUI is not yet Implemented)

Run the following command To launch the application as a CLA (Command Line Application)

	java -cp bin gui.Main cmd