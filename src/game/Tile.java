package game;

public class Tile {
    private char color;
    private boolean occupied;
    private int[] position = new int[2];

    @Override
    public String toString() {//can return generic info to print or something else
        return String.valueOf(color);
    }

    public Tile(char color, int[] position) {
        this.color = color;
        this.occupied = false;
        this.position = position;
    }

    public Tile() {
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
