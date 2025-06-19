package org.example;

import org.example.controllers.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static StudyCardsController studyCardsController = new StudyCardsController();
    static StudyPlannerController studyPlannerController = new StudyPlannerController();
    static StudyRegistryController studyRegistryController = new StudyRegistryController();
    static StudySearchController studySearchController = new StudySearchController();
    static MainController controller = new MainController(studyCardsController, studyPlannerController,
            studyRegistryController, studySearchController);

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String input = "";

        while(!input.equals("0")) {
            System.out.println(mainOptions());
            input = userInput.nextLine();
            controller.handleUserInput(input);
        }
    }

    public static String mainOptions(){
        return """
                Welcome to the Study Organizer
                Please choose from the following options:
                0 - ends program
                1 - study cards
                2 - study planner
                3 - study registry
                4 - study search
                """;
    }
}