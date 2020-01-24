package game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Piece {
    private char color, player;
    private Tile place;

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public char getPlayer() {
        return player;
    }

    static Piece[][] setInitialPosition(Board board){
        int size = board.getBoardSize();
        Piece[][] pieces = new Piece[2][size];
        Tile proxy;

        for(int i = 0; i<size;i++){
            proxy = board.getTileAt(1,i);
            //pieces[0][i].player = ;

        }
        return null;
    }

    static Piece[][] setPosition(Board board,String filename) throws IOException{
        int size = board.getBoardSize();
        Piece[][] output = new Piece[2][size];
        String data = new String(Files.readAllBytes(Paths.get(filename)));
        String[] lines = data.split("\n", 0);

        int row=0;
        for (String line : lines) {
            if (line.trim().length() < 1 || line.charAt(0) == '#'){
                continue;
            }

            String[] tile_chrs = line.split("");
            output[0][row].setColor(tile_chrs[0].charAt(0));
            output[1][row].setColor(tile_chrs[1].charAt(0));
            output[0][row].setPlayer('w');
            output[1][row].setPlayer('b');

            row++;
        }

        return null;
    }
    //private int x,y;

}
