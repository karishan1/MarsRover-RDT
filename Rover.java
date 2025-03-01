import java.util.*;

public class Rover implements Movable{
    private int x_pos;
    private int y_pos;
    private char direction;
    private final Plateau plateau;

    private static final List<Character> DIRECTIONS = List.of('N', 'E', 'S', 'W');

    Rover(int x_pos, int y_pos, char direction,Plateau plateau) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.direction = direction;
        this.plateau = plateau;

    }

    public int getXPos() {
        return x_pos;
    }
    public int getYPos() {
        return y_pos;
    }
    public char getDirection() {
        return direction;
    }

    // Rotates rover
    @Override
    public void rotate(char rotation) {
        int index = DIRECTIONS.indexOf(this.direction);
        if (rotation == 'L') {
            this.direction = DIRECTIONS.get((index + 3) % 4);
        }
        else if (rotation == 'R') {
            this.direction = DIRECTIONS.get((index + 1) % 4);
        }
    }

    // Moves rover forward
    @Override
    public void move() {

        // Checks if valid movement
        if (canMove()){
            switch(direction){
                case 'N': y_pos += 1; break;
                case 'E': x_pos += 1; break;
                case 'S': y_pos -= 1; break;
                case 'W': x_pos -= 1; break;
            }
        }
        else{
            System.out.println("Invalid Command - Rover cannot move out of bounds of plateau or collide with another rover.");

        }

    }

    // Checks validity of movement
    @Override
    public boolean canMove() {
        int newX = x_pos;
        int newY = y_pos;

        switch (direction){
            case 'N': newY += 1; break;
            case 'E': newX += 1; break;
            case 'S': newY -= 1; break;
            case 'W': newX -= 1; break;
        }

        // Checks if collision occurs or out of bounds
        if (newX < 0 || newX > plateau.getWidth() || newY < 0 || newY > plateau.getHeight() || plateau.checkIfCollisionOccurs(newX, newY)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Position: (" + x_pos + ", " + y_pos + ") | Facing: " + direction;
    }


}
