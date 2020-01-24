import game.Board;

import java.io.IOException;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {
            Board board = new Board(8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Success!\n");
    }
}
