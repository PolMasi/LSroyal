package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class BoardView extends JPanel {
    private GridLayout gridBoard;
    private static final int ROWS = 8;
    private static final int COLUMNS = 7;

    private JPanel[][] grids;

    private JPanel game;

    public BoardView() {
        grids = new JPanel[ROWS][COLUMNS];
    }

    public void configurePanel(ActionListener listener) {
        gridBoard = new GridLayout(ROWS, COLUMNS);
        JPanel board = new JPanel(gridBoard);
        JLabel title = new JLabel("GAME");

        title.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new BorderLayout());

        game = new JPanel(new BorderLayout());

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

                grids[i][j] = new JPanel(new BorderLayout());

                JButton grid = new JButton();
                grid.setActionCommand(button);
                grid.addActionListener(listener);

                grids[i][j].add(grid, BorderLayout.WEST);
                board.add(grids[i][j], BorderLayout.CENTER);
            }
        }

        game.add(board);
        board.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 20));

        add(game, BorderLayout.WEST);
        add(title, BorderLayout.NORTH);
    }

    public void configureCards(String[] offensive, String[] defensive, ActionListener listener) {

        FlowLayout flow = new FlowLayout();
        JPanel flowLayout = new JPanel(flow);

        flowLayout.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));

        GridLayout defCards = new GridLayout(1, defensive.length / 3);
        GridLayout offCards = new GridLayout(1, offensive.length / 3);

        JPanel defPanel = new JPanel(defCards);
        JPanel offPanel = new JPanel(offCards);

        int cardNumber = 1;

        for (int i = 0; i < defensive.length; i++) {

            JPanel cardDef = new JPanel(new BorderLayout());
            JPanel cardOff = new JPanel(new BorderLayout());

            JButton iconDef = new JButton(String.valueOf(defensive[i].charAt(0)));
            JButton iconOff = new JButton(String.valueOf(offensive[i].charAt(0)));

            iconDef.setActionCommand("DEF" + cardNumber);
            iconOff.setActionCommand("OFF" + cardNumber);

            iconDef.addActionListener(listener);
            iconOff.addActionListener(listener);

            cardNumber++;

            cardDef.add(iconDef, BorderLayout.CENTER);
            cardOff.add(iconOff, BorderLayout.CENTER);
            i++;

            JLabel defLabel = new JLabel(defensive[i]);
            JLabel offLabel = new JLabel(offensive[i]);

            defLabel.setAlignmentX(CENTER_ALIGNMENT);
            offLabel.setAlignmentX(CENTER_ALIGNMENT);

            cardDef.add(defLabel, BorderLayout.SOUTH);
            cardOff.add(offLabel, BorderLayout.SOUTH);

            defPanel.add(cardDef);
            offPanel.add(cardOff);
            i++;
        }

        JPanel offBox = new JPanel();
        offPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        offBox.setLayout(new BoxLayout(offBox, BoxLayout.PAGE_AXIS));
        offBox.add(new JLabel("OFFENSIVES"));
        offBox.add(offPanel);

        JPanel defBox = new JPanel();
        defPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        defBox.setLayout(new BoxLayout(defBox, BoxLayout.PAGE_AXIS));
        defBox.add(new JLabel("DEFENSIVES"));
        defBox.add(defPanel);

        defBox.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        flowLayout.add(offBox);
        flowLayout.add(defBox);

        game.add(flowLayout, BorderLayout.SOUTH);

    }
}
