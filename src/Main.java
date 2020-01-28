import game.Board;
import game.*;

import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        try {
            Board board = new Board(8);
            System.out.print(board.OccupancyString());

            /*
            //This can be used to test Piece.getPossibleMoves;
            Piece test = board.getPiece('W','Y');

            System.out.println("Piece position: " + (char)('a'+test.getPlace().getPosition()[1])+ (test.getPlace().getPosition()[0]+1));
            ArrayList<String> moveList = test.getPossibleMoves(board);
            for(String move:moveList){
                System.out.println(move);
            }
            */
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.print("Success!\n");
    }
}
