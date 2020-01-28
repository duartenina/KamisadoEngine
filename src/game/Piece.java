package game;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Piece {
    private char color, player;
    private Tile place;

    public char getColor() {
        return color;
    }

    public char getPlayer() {
        return player;
    }

    public Tile getPlace() {
        return place;
    }

    public Piece(char color, char player, Tile place) {

        if(place.isOccupied() ) {
            System.out.println("Error - Tile already occupied. Cannot set piece.");
        }
        else{
            this.color = color;
            this.player = player;
            this.place = place;
            place.setOccupied(true);
        }
    }

    public Piece(char color, char player) {
        this.color = color;
        this.player = player;
        this.place = null;
    }


    static Piece[][] setPosition(Board board, String filename) throws IOException{
        int size = board.getBoardSize();
        Piece[][] output = new Piece[2][size];
        String data = new String(Files.readAllBytes(Paths.get(filename)));
        String[] lines = data.split("\n", 0);

        int whiteCount = 0;
        int blackCount = 0;
        for (String line : lines) {
            if (line.trim().length() < 1 || line.charAt(0) == '#'){
                continue;
            }

            String[] tile_chrs = line.split(" ");
            String coord = tile_chrs[0];
            char player = tile_chrs[1].charAt(0);
            char color = tile_chrs[2].charAt(0);

            Tile tile = board.getTileAt(coord);
            Piece piece = new Piece(color, player, tile);

            if (player == 'W') {
                if (whiteCount >= size) {
                    System.out.println("too many white pieces");
                    continue;
                }
                output[board.WHITE][whiteCount] = piece;
                whiteCount++;
            }
            else {
                if (blackCount >= size) {
                    System.out.println("too many black pieces");
                    continue;
                }
                output[board.BLACK][blackCount] = piece;
                blackCount++;
            }
        }

        if (whiteCount < size) { // Not enough pieces
            System.out.println("not enough white pieces");
            // ERROR
        }
        if (blackCount < size) { // Not enough pieces
            System.out.println("not enough black pieces");
            // ERROR
        }

        return output;
    }

    /*
    public ArrayList<String> getPossibleMoves(Board board) {
        ArrayList<String> moveList = new ArrayList<String>(0);
        int[] currentPosition = this.place.getPosition();
        int move;
        if(this.player == 'W') move = +1;
        else if(this.player == 'K') move = -1;
        else{
            System.out.println("Error - No valid player found.");
            System.exit(-1);
        }

        board.OccupationChar();
        return moveList;
    }
    */

}
