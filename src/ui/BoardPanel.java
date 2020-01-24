package ui;

import game.Board;
import game.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private Board board;
    private JPanel boardPanel;

    public BoardPanel(Board board) {
        super();

        this.board = board;

//        boardPanel = new JPanel(new GridLayout(board.getBoardSize(), board.getBoardSize()));
//        JPanel boardConstrain = new JPanel(new GridBagLayout());
//        boardConstrain.add(boardPanel);
//        this.add(boardConstrain);

        this.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));

        paintBoard();
    }

    @Override
    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension prefSize = null;
        Component c = getParent();
        if (c == null) {
            prefSize = new Dimension(
                    (int)d.getWidth(),(int)d.getHeight());
        } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
            prefSize = c.getSize();
        } else {
            prefSize = d;
        }
        int w = (int) prefSize.getWidth();
        int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        int s = (w>h ? h : w);
        return new Dimension(s,s);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    protected void paintBoard() {
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (Tile[] tileRow : board.getTiles()) {
            for (Tile tile : tileRow) {
                JButton tileBtn = new JButton();
                tileBtn.setMargin(buttonMargin);

                char colorChr = tile.getColor();
                tileBtn.setBackground(getTileColorFromChar(colorChr));

                this.add(tileBtn);
            }
        }
    }

    protected Color getTileColorFromChar(char colorChr) {
        Color color;
        switch (colorChr) {
            case 'O': color = Color.ORANGE; break;
            case 'B': color = Color.BLUE; break;
            case 'P': color = new Color(128, 0, 128); break;
            case 'I': color = Color.PINK; break;
            case 'Y': color = Color.YELLOW; break;
            case 'R': color = Color.RED; break;
            case 'G': color = Color.GREEN; break;
            case 'N': color = new Color(165, 42, 42); break;
            default: color = Color.BLACK; break;
        }

        return color;
    }
}
