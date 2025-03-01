import java.util.*;

public class Plateau {
    private final int height;
    private final int width;
    private List<Rover> activeRovers;

    public Plateau(int height, int width) {
        this.height = height;
        this.width = width;
        this.activeRovers = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    // Adds newly deployed rover to list
    public void addRover(Rover rover) {
        if (checkIfPositionOccupied(rover.getXPos(), rover.getYPos())){
            System.out.println("Error: Another rover is already at position (" + rover.getXPos() + ", " + rover.getYPos() + ").");
            return;
        }
        activeRovers.add(rover);
    }

    public List<Rover> getActiveRovers() {
        return activeRovers;
    }

    // Checks if collision occurs with other rovers from new move on plateau
    public boolean checkIfCollisionOccurs(int newX, int newY) {
        for (Rover activeRover : this.getActiveRovers()) {
            if (activeRover.getXPos() == newX && activeRover.getYPos() == newY) {
                return true;
            }
        }
        return false;
    }

    // Checks position occupation status
    public boolean checkIfPositionOccupied(int x, int y) {
        for (Rover activeRover : activeRovers) {
            if (activeRover.getXPos() == x && activeRover.getYPos() == y) {
                return true;
            }
        }
        return false;
    }





}