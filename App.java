import java.util.Scanner;

public class App {
    private static Plateau plateau;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Validate Plateau Coordinates
        int width = 0;
        int height = 0;
        while (true) {
            System.out.print("Enter plateau upper-right coordinates (width height): ");
            if (scanner.hasNextInt()) {
                width = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    height = scanner.nextInt();
                    if (width > 0 && height > 0) {
                        plateau = new Plateau(height, width);
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Error: Plateau dimensions must be positive integers.");
                    }
                } else {
                    System.out.println("Error: Invalid height. Please enter a valid integer.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Error: Invalid width. Please enter valid integers.");
                scanner.nextLine();
            }
        }

        // Validate Number of Rovers
        int numberOfRovers = 0;
        while (true) {
            System.out.print("Enter number of rovers: ");
            if (scanner.hasNextInt()) {
                numberOfRovers = scanner.nextInt();
                if (numberOfRovers > 0) {
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Error: Number of rovers must be a positive integer.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }

        // Process each rover
        for (int i = 0; i < numberOfRovers; i++) {
            int xPos = -1;
            int yPos = -1;
            char direction = ' ';

            // Validate Rover's Starting Position and Direction
            while (true) {
                System.out.print("Enter rover " + (i + 1) + " starting position and direction (x y direction): ");
                String position = scanner.nextLine();
                String[] positionData = position.split(" ");

                if (positionData.length == 3) {
                    try {
                        xPos = Integer.parseInt(positionData[0]);
                        yPos = Integer.parseInt(positionData[1]);
                        direction = positionData[2].charAt(0);

                        // Check position
                        if (xPos < 0 || xPos > width || yPos < 0 || yPos > height) {
                            System.out.println("Error: Position is out of bounds. Please try again.");
                            continue;
                        }

                        // Checks direction
                        if (direction != 'N' && direction != 'E' && direction != 'S' && direction != 'W') {
                            System.out.println("Error: Invalid direction. Use N, E, S, or W.");
                            continue;
                        }

                        // Checks if position is occupied
                        if (plateau.checkIfPositionOccupied(xPos, yPos)) {
                            System.out.println("Error: Position is occupied by another rover.");
                            continue;
                        }

                        // If all checks pass then break out of the loop
                        break;

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid position. Please enter integers for x and y.");
                    }
                } else {
                    System.out.println("Error: Invalid input format. Please enter in the format 'x y direction'.");
                }
            }

            // Create and add the rover to the plateau
            Rover rover = new Rover(xPos, yPos, direction, plateau);
            plateau.addRover(rover);

            // Validate Rover's Movement Commands
            while (true) {
                System.out.print("Enter commands for rover " + (i + 1) + ": ");
                String commands = scanner.nextLine();

                // Validate movement commands against regular expression
                if (!commands.matches("[LRM]*")) {
                    System.out.println("Error: Invalid commands. Only 'L', 'R', and 'M' are allowed.");
                    continue;
                }

                // Process commands
                for (char command : commands.toCharArray()) {
                    if (command == 'M') {
                        rover.move();
                    } else if (command == 'L' || command == 'R') {
                        rover.rotate(command);
                    }
                }
                break;
            }

            // Output final rover position and direction
            System.out.println("Rover " + (i + 1) + " final position: " + rover.toString());
        }

        scanner.close();
    }

}
