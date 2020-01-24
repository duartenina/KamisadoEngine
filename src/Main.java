import game.Board;

import java.io.*;

public class Main {


    public static void main(String[] args) {

        try {
            Board board = new Board(8);
            System.out.print(board.OccupancyString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.print("Success!\n");
    }
}
