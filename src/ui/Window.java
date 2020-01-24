package ui;

import game.Board;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends  JFrame{
    public Window(int width, int height) throws IOException {
        super();

        this.setSize(width, height);
        this.createLayout();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    protected void createLayout() throws IOException {
//        BorderLayout mainLayout = new BorderLayout();

        JButton b = new JButton("click"); //creating instance of JButton
        this.add(b, BorderLayout.WEST); //adding button in JFrame

        BoardPanel boardPanel = new BoardPanel(new Board(8));

        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.add(boardPanel);
        this.add(boardConstrain, BorderLayout.CENTER);

//        this.add(boardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        Window w = new Window(400, 500);

        w.setTitle("Kamisado");
        w.pack();
        w.setMinimumSize(new Dimension(350, 350));

        w.setVisible(true);  //making the frame visible
    }
}
