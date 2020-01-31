import game.Board;
//import game.*;

import java.io.*;
//import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        try {
            Board board = new Board(8);

            System.out.println();
            System.out.print(board.occupancyString());
            System.out.println();
            //board.getPiece('W','Y').setPlace(board, new int[]{6, 4});


            board.getPiece('K','Y').setPlace(board, "g4");
            board.getPiece('W','P').setPlace(board, new int[]{5, 4});
            System.out.println();
            System.out.print(board.occupancyString());
            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.print("Success!\n");
    }
}
