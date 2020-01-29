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

    public void setPlace(Board board, int[] new_position){//working on it now
        boolean move_in_movelist = false;

        ArrayList<String> possibleMoves = getPossibleMoves(board);

        //char[] position = {(char)(new_position[0]+'a'),(char)('1' + new_position[1])};
        //System.out.println("Want to go to:  " + (char)(position[0]) + position[1]);
        for(String move:possibleMoves){
            int[] coord_values = {move.charAt(0)-'a',move.charAt(1)-'1'};
            if (Arrays.equals(coord_values,new_position) ){
                move_in_movelist = true;
                break;
            }
        }

        if (move_in_movelist) {
            place.setOccupied(false);
            place = board.getTileAt(new_position[1], new_position[0]);
            //System.out.println("Went to:  " + position[0] +""+ position[1]);
            place.setOccupied(true);

        }
        else{
            System.out.println("Cannot move to " + (char)(new_position[0]+'a') + (new_position[1]+1));
            if(board.getTileAt(new_position[1], new_position[0]).isOccupied())
                System.out.println("Tile has another piece.");
        }
    }

    public void setPlace(Board board, String new_position) {//working on it now
        setPlace(board,new int[] {new_position.charAt(0)-'a',new_position.charAt(1)-'1'});
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


    public ArrayList<String> getPossibleMoves(Board board) {
        ArrayList<String> move_List = new ArrayList<>(0);
        int[] current_Position = this.place.getPosition();
        int direction;
        if(this.player == 'W') direction = -1;
        else if(this.player == 'K') direction = +1;
        else{
            direction = 0;
            System.out.println("Error - No valid player found.");
            System.exit(-1);
        }


        int y_init = current_Position[0], x_init = current_Position[1];
        //System.out.println(y_init + " " + direction);
        for(int y = y_init+direction; 0 <= y && y < 8; y+=direction){
            //System.out.println(y);
            int delta_move = Math.abs(y-y_init);
            boolean[] blocked = {false,false,false};
            int[] x_arr = {x_init-delta_move, x_init, x_init+delta_move};
            int row = 0;
            for (int x:x_arr) {
                //System.out.println(y + " " + x);
                if (0 <= x && x < 8 && !blocked[row]) {
                    if (board.OccupationChar()[y][x] == '.') {
                        char rowchar = (char) (x + 'a');
                        String move = String.valueOf(rowchar) + (y+1);
                        move_List.add(move);
                    }
                    else{
                        blocked[row] = true;
                    }
                row++;
                }
            }
        }
        /*for (String move:move_List){
            System.out.println(move);
        }*/

        return move_List;
    }

}
