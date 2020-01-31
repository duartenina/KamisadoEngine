package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {
    char playerTurn;
    char colorTurn;
    String newPosition;
    boolean moveMade;

    public void StartGame() {
        playerTurn = 'K';
        System.out.println("Let's play Kamisado.");
        System.out.println("If you need any help, just press 'help'.");
    }

    public void requestInput( String move, char color, Board board ) throws IOException {
        requestMove(color);
        while(!moveMade) {
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (input.equals("help")) {
                System.out.println("To make a move select the piece to be moved and the destination square");
                System.out.println("E.g.: Ye4 moves the White's Yellow piece to the e4 square, if such a move is valid.");
                System.out.println("Valid moves are such that the piece moves forward, either vertically or diagonally.");
                System.out.println("A piece can't jump other pieces and you must move the piece of the color of the tile the other player landed on.");
            }
            else {
                if (move.charAt(0) != colorTurn) {
                    System.out.println("Error - wrong piece.");
                }
                else {
                    newPosition = move.substring(1);
                    board.getPiece(playerTurn, colorTurn).setPlace(board, move.substring(1));
                }
            }
        }
    }

    private void requestMove(char color) {
        moveMade = false;
        colorTurn = color;
        String player = playerTurn == 'K' ? "white" : "black";
        System.out.print("It's" + player + "'s turn. Please make a move.");
        //if (color == '') System.out.print("Please move " + color + " piece.");
    }
}
