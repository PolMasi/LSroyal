package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardView extends JPanel {
    private GridLayout gridBoard;
    private static final int ROWS = 7;
    private static final int COLUMNS = 7;

    public BoardView() {

    }

    public void configurePanel(ActionListener listener) {
        gridBoard = new GridLayout(ROWS,COLUMNS);
        JPanel board = new JPanel(gridBoard);
        JLabel title = new JLabel("GAME");

        title.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BorderLayout());

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                String button;
                Integer num = i+1;

                switch (j) {
                    case 0 -> button = num.toString()+"a";
                    case 1 -> button = num.toString()+"b";
                    case 2 -> button = num.toString()+"c";
                    case 3 -> button = num.toString()+"d";
                    case 4 -> button = num.toString()+"e";
                    case 5 -> button = num.toString()+"f";
                    case 6 -> button = num.toString()+"g";
                    default -> button = "0";
                }

                JButton grid = new JButton(button);
                grid.setActionCommand(button);
                grid.addActionListener(listener);
                board.add(grid);
            }
        }

        add(board, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);

    }

}
