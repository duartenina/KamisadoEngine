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

    public void setPlace( Board board, int[] newPosition ) {
        boolean moveInMoveList = false;

        ArrayList<String> possibleMoves = getPossibleMoves(board);

        //char[] position = {(char)(newPosition[0]+'a'),(char)('1' + newPosition[1])};
        //System.out.println("Want to go to:  " + (char)(position[0]) + position[1]);
        for (String move : possibleMoves) {
            int[] coordValues = {move.charAt(0) - 'a', move.charAt(1) - '1'};
            if (Arrays.equals(coordValues, newPosition)) {
                moveInMoveList = true;
                break;
            }
        }

        if (moveInMoveList) {
            place.setOccupied(false);
            place = board.getTileAt(newPosition[1], newPosition[0]);
            //System.out.println("Went to:  " + position[0] +""+ position[1]);
            place.setOccupied(true);

        } else {
            System.out.println("Cannot move to " + (char) ( newPosition[0] + 'a' ) + ( newPosition[1] + 1 ));
            if (board.getTileAt(newPosition[1], newPosition[0]).isOccupied())
                System.out.println("Tile has another piece.");
        }
    }

    public void setPlace(Board board, String newPosition) {//working on it now
        setPlace(board,new int[] {newPosition.charAt(0)-'a',newPosition.charAt(1)-'1'});
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

            String[] tileChrs = line.split(" ");
            String coord = tileChrs[0];
            char player = tileChrs[1].charAt(0);
            char color = tileChrs[2].charAt(0);

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


    public ArrayList<String> getPossibleMoves(Board board) {
        ArrayList<String> moveList = new ArrayList<>(0);
        int[] current_Position = this.place.getPosition();
        int direction;
        if(this.player == 'W') direction = -1;
        else if(this.player == 'K') direction = +1;
        else{
            direction = 0;
            System.out.println("Error - No valid player found.");
            System.exit(-1);
        }


        int yInit = current_Position[0], xInit = current_Position[1];
        //System.out.println(yInit + " " + direction);
        for(int y = yInit+direction; 0 <= y && y < 8; y+=direction){
            //System.out.println(y);
            int delta_move = Math.abs(y-yInit);
            boolean[] blocked = {false,false,false};
            int[] xArr = {xInit-delta_move, xInit, xInit+delta_move};
            int row = 0;
            for (int x:xArr) {
                //System.out.println(y + " " + x);
                if (0 <= x && x < 8 && !blocked[row]) {
                    if (board.occupationChar()[y][x] == '.') {
                        char rowchar = (char) (x + 'a');
                        String move = String.valueOf(rowchar) + (y+1);
                        moveList.add(move);
                    }
                    else{
                        blocked[row] = true;
                    }
                row++;
                }
            }
        }
        /*for (String move:moveList){
            System.out.println(move);
        }*/

        return moveList;
    }

}
