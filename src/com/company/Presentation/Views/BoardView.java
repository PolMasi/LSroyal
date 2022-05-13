package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

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

    public void configureCards(String[] offensive, String[] defensive, ActionListener listener) {
        JPanel flowLayout = new JPanel(new FlowLayout());
        GridLayout defCards = new GridLayout(1, defensive.length/3);
        GridLayout offCards = new GridLayout(1, offensive.length/3);

        JPanel defPanel = new JPanel(defCards);
        JPanel offPanel = new JPanel(offCards);
        JPanel defCost = new JPanel(new FlowLayout());
        JPanel offCost = new JPanel(new FlowLayout());

        int cardNumber = 1;

        for (int i = 0; i < defensive.length; i++) {
            JButton iconDef = new JButton(String.valueOf(defensive[i].charAt(0)));
            JButton iconOff = new JButton(String.valueOf(offensive[i].charAt(0)));

            iconDef.setActionCommand("DEF"+cardNumber);
            iconOff.setActionCommand("OFF"+cardNumber);

            iconDef.addActionListener(listener);
            iconOff.addActionListener(listener);

            cardNumber++;

            defPanel.add(iconDef);
            offPanel.add(iconOff);
            i++;
            defCost.add(new JLabel(defensive[i]));
            offCost.add(new JLabel(offensive[i]));
            i++;
        }

        JPanel offBox = new JPanel();
        offBox.setLayout(new BoxLayout(offBox, BoxLayout.PAGE_AXIS));
        offBox.add(new JLabel("OFFENSIVES"));
        offBox.add(offPanel);
        offBox.add(offCost);

        JPanel defBox = new JPanel();
        defBox.setLayout(new BoxLayout(defBox, BoxLayout.PAGE_AXIS));
        defBox.add(new JLabel("DEFENSIVES"));
        defBox.add(defPanel);
        defBox.add(defCost);

        flowLayout.add(offBox);
        flowLayout.add(defBox);

        add(flowLayout, BorderLayout.SOUTH);

    }
}
