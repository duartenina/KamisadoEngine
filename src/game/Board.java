package game;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Board {
    private int boardSize;
    Tile[][] tiles;
    Piece[] pieces;

    public int getBoardSize() {
        return boardSize;
    }
    public Tile getTileAt(int y, int x){
        return tiles[y][x];
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Board(int size) throws IOException {
        if (size == 8) { //TO DO: generalize for any size
            tiles = load_board_txt("data/board_8x8.txt");
            //pieces = set_pieces(tiles);
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }

        boardSize = size;

        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(tiles[i][j]);
            }
            System.out.print("\n");
        }
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
