package game;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Board {
    private int boardSize;
    private Tile[][] tiles;
    private Piece[][] pieces;

    public int WHITE=0, BLACK=1;

    public int getBoardSize() {
        return boardSize;
    }

    public Tile getTileAt(int y, int x){
        return tiles[y][x];
    }
    public Tile getTileAt(String coord) {
        // a1 -> x = 0; y = 0
        // h8 -> x = 7; y = 7

        char colChr = coord.charAt(0);
        int col = colChr - 'a';
        int row = Integer.parseInt(coord.substring(1)) - 1;

        return tiles[row][col];
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < boardSize ; i++){
            for(int j = 0 ; j < boardSize ; j++){
                str.append(tiles[i][j]);
            }
            str.append("\n");
        }

        return str.toString();
    }

    public Board(int size) throws IOException {
        boardSize = size;

        if (boardSize == 8) { //TO DO: generalize for any size
            tiles = load_board_txt("data/board_8x8.txt");
            pieces = Piece.setPosition(this, "data/initialPosition_8x8.txt");
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.out.println(this);
    }

    protected Tile[][] load_board_txt(String filename) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(filename)));
        String[] lines = data.split("\n", 0);
        Tile[][] output = new Tile[8][8];

        int row=0, col=0;
        for (String line : lines) {
            if (line.trim().length() < 1 || line.charAt(0) == '#'){
                continue;
            }

            String[] tile_chrs = line.split(",");
            for (String chr : tile_chrs) {
                output[row][col] = new Tile(chr.charAt(0));
                //System.out.println(String.format("%d, %d: %s", row, col, chr));
                col++;
            }
            row++;
            col = 0;
        }

        return output;
    }
}
