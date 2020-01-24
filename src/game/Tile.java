package game;

public class Tile {
    private char color;

    @Override
    public String toString() {//can return generic info to print or something else
        return String.valueOf(color);
    }

    public Tile(char color) {
        //System.out.println("Constructed w/ color!");
        this.color = color;
    }

    public Tile() {
        //System.out.println("Constructed!");
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
