## Project Team Null Pointers

# Project Website
https://diegomorales30.github.io/NullPointersWebsite/

Holds all our documentation for the project including problem statement, user stories, requirments, architecture and test reports.

# Project Description
A simple UI that displays important information about people who use a free pet food distribution center. The purpose is to create an easy to use UI to log and display patrons/users.

# User Navigation of GitHub Page
**.idea folder**: Used to store our module configurations for the UI, to combine our multiple frameworks into the UI.
**archive folder**: Stores saved data of all owners profiles when the archive button is selected by the user inside .csv files.
**src folder**: All files that were used as the main structure for the UI.
**meta folder**: Used to store our user settings

# Installation of UI
In order to install our UI you must have java installed (Preferably Java 16.0.0 or Newer) and javaFX installation (Preferably javaFX 18.0.0 or Newer). So far the best way to run it has been on an external IDE (such as VScode) and directly running the file "GUI.java". Since all classes point back to our GUI file that is the main file that connects everything. We are still working on creating a standalone application that is able to run our UI for better user experience.

# Structure of UI
The structure of our UI is based on the structure called Pipes and Filters (Further illustrated on our website). For the brief description, we broke down different UI functions such as read, write, etc. into smaller classes that handled specific functions. At the end we combined these different classes into the UI, which was able to fully utilize the functionality of every class.

# Build Instructions
In order to use the GUI it must be run through an IDE. Please run the GUI.java class. This is obviously suboptimal but the pom files have been a struggle to understand in a short amount of time. To run the program, you must have JavaFX jar files (found here: https://gluonhq.com/products/javafx/) added to the project.

If you generate a launch.json file, make sure to add this line to the arguments of the GUI launcher:
"vmArgs":"--module-path [your path to JavaFX lib folder] --add-modules javafx.controls,javafx.fxml",

