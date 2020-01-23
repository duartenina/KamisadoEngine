package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Board {
    Tile[][] tiles;
    Piece[] pieces;

    public Board(int size) throws IOException {
        if (size == 8) {
            tiles = load_board_txt("data/board_8x8.txt");
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    protected Tile[][] load_board_txt(String filename) throws IOException {
        String data;
        data = new String(Files.readAllBytes(Paths.get(filename)));

        String[] lines = data.split("\n", 0);

        int row=0, col=0;

        for (String line : lines) {
            if ((line.charAt(0) == '#') || (line.trim().length() < 1)){
                continue;
            }

            String[] tile_chrs = line.split(",");
            for (String chr : tile_chrs) {
                System.out.println(String.format(
                        "%d, %d: %s", row, col, chr
                ));
                col++;
            }
            row++;
            col = 0;
        }

        return null;
    }
}
