package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {
    private JButton playGame;
    private JButton reproduce;
    private JButton ranking;
    private JButton exit;

    public static final String PLAYGAME_BTN = "PLAYGAME_BTN";
    public static final String REPRODUCE_BTN = "REPRODUCE_BTN";
    public static final String RANKING_BTN = "RANKING_BTN";
    public static final String EXIT_BTN = "EXIT_BTN";

    public MenuView(){
        this.setLayout(new GridBagLayout()); // la interfaz tendra un GrindBag layout

        JLabel title1 = new JLabel("MAIN", SwingConstants.CENTER);
        JLabel title2 = new JLabel("MENU", SwingConstants.CENTER);

        GridBagConstraints constraints = new GridBagConstraints(); // necesario para el layout
        this.setBackground(Color.DARK_GRAY);

        this.add(title1);
        title1.setForeground(Color.YELLOW);
        title1.setFont(new Font("Helvetica", Font.BOLD, 100));

        constraints.gridx =0; // el area que empieza es columna 0
        constraints.gridy =0; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        this.add(title1, constraints);
        constraints.weightx = 0.0;

        this.add(title2);
        title2.setForeground(Color.YELLOW);
        title2.setFont(new Font("Helvetica", Font.BOLD, 100));

        constraints.gridx =1; // el area que empieza es columna 0
        constraints.gridy =1; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        this.add(title2, constraints);
        constraints.weightx = 0.0;

        //BUTTONS
        playGame = new JButton("Play");
        playGame.setActionCommand(String.valueOf(CardEnum.PLAYGAME_BTN));
        playGame.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(playGame, constraints);
        constraints.weightx = 0.0;

        reproduce = new JButton("Reproduce game");
        reproduce.setActionCommand(String.valueOf(CardEnum.REPRODUCE_BTN));
        reproduce.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(reproduce, constraints);
        constraints.weightx = 1.0;

        ranking = new JButton("Ranking");
        ranking.setActionCommand(String.valueOf(CardEnum.RANKING_BTN));
        ranking.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(ranking, constraints);
        constraints.weightx = 1.0;

        exit = new JButton("Exit");
        exit.setActionCommand(String.valueOf(CardEnum.EXIT_BTN));
        exit.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(exit, constraints);
        constraints.weightx = 1.0;


    }

    public void registerMenuController(ActionListener listener) {
        playGame.addActionListener(listener);
        reproduce.addActionListener(listener);
        ranking.addActionListener(listener);

    }


    public void registerController(ActionListener listener) {
        exit.addActionListener(listener);                               //preguntar edu ja que ni havia goBck
    }
}