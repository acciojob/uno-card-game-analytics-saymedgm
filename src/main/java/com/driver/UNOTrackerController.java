package com.driver;

import java.util.Scanner;

public class UNOTrackerController {
    private UNOTrackerService unoTrackerService;

    public UNOTrackerController(UNOTrackerService unoTrackerService) {
        this.unoTrackerService = unoTrackerService;
    }

    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    storeScore(scanner);
                    break;
                case 2:
                    calculateAverageScore(scanner);
                    break;
                case 3:
                    identifyTopPlayer();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Store UNO Game Score");
        System.out.println("2. Calculate Average Score");
        System.out.println("3. Identify Top Player");
        System.out.println("4. Exit");
    }

    private void storeScore(Scanner scanner) {
        System.out.println("Enter Player Name:");
        scanner.nextLine(); // Consume the newline character
        String playerName = scanner.nextLine();

        System.out.println("Enter Score:");
        int score = scanner.nextInt();

        ScoreDTO scoreDTO = new ScoreDTO(playerName, score);
        unoTrackerService.storeScoreData(scoreDTO);
        System.out.println("Score Data stored successfully.");
    }

    private void calculateAverageScore(Scanner scanner) {
        System.out.println("Enter Player Name:");
        scanner.nextLine(); // Consume the newline character
        String playerName = scanner.nextLine();

        double averageScore = unoTrackerService.calculateAverageScore(playerName);
        System.out.println("Average Score for " + playerName + ": " + averageScore);
    }

    private void identifyTopPlayer() {
        String topPlayer = unoTrackerService.identifyTopPlayer();
        System.out.println("Top Player: " + topPlayer);
    }

    public static void main(String[] args) {
        // Create necessary objects and start the application
        ScoreDataRepository repository = new ScoreDataRepository();
        UNOTrackerService unoTrackerService = new UNOTrackerService(repository);
        UNOTrackerController unoTrackerController = new UNOTrackerController(unoTrackerService);

        // Start processing user input
        unoTrackerController.processUserInput();
    }
}