package com.company.Presentation.Views;

import com.company.Presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {


    private JButton playGame;
    private JButton reproduce;
    private JButton ranking;
    private JButton exit;
    private JButton delete;

    public static final String PLAYGAME_BTN = "PLAYGAME_BTN";
    public static final String REPRODUCE_BTN = "REPRODUCE_BTN";
    public static final String RANKING_BTN = "RANKING_BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";
    public static final String DELETE_BTN = "DELETE_BTN";

    public MenuView(){


        this.setLayout(new BorderLayout()); // la interfaz tendra un layout

        JLabel title1 = new JLabel("MAIN MENU", SwingConstants.CENTER);


        //GridBagConstraints constraints = new GridBagConstraints(); // necesario para el layout
        this.setBackground(Color.DARK_GRAY);

        this.add(title1, BorderLayout.NORTH);
        title1.setForeground(Color.YELLOW);
        title1.setFont(new Font("Helvetica", Font.BOLD, 100));
/*
        //constraints.gridx =0; // el area que empieza es columna 0
        constraints.gridy =0; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        this.add(title1, constraints);
        constraints.weightx = 0.0;

        //this.add(title2);
        //title2.setForeground(Color.YELLOW);
        //title2.setFont(new Font("Helvetica", Font.BOLD, 100));

        constraints.gridx =1; // el area que empieza es columna 0
        constraints.gridy =1; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        //this.add(title2, constraints);
        constraints.weightx = 0.0;

 */
        FlowLayout flowLayout =  new FlowLayout();
        JPanel buttons = new JPanel(flowLayout);
        buttons.setBackground(Color.DARK_GRAY);
        JPanel center = new JPanel(new BorderLayout());

        //BUTTONS
        playGame = new JButton("Play");
        playGame.setActionCommand(MenuView.PLAYGAME_BTN);
        playGame.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttons.add(playGame);
        /*
        constraints.gridx= 2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(playGame, constraints);
        constraints.weightx = 0.0;

         */

        reproduce = new JButton("Reproduce game");
        reproduce.setActionCommand(MenuView.REPRODUCE_BTN);
        reproduce.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttons.add(reproduce);

        /*
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(reproduce, constraints);
        constraints.weightx = 1.0;

         */

        ranking = new JButton("Ranking");
        ranking.setActionCommand(MenuView.RANKING_BTN);
        ranking.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttons.add(ranking);
        center.add(buttons,BorderLayout.NORTH);

        JPanel logoutPanel = new JPanel(new FlowLayout());
        logoutPanel.setBackground(Color.DARK_GRAY);
        JPanel Panelvacio = new JPanel();
        Panelvacio.setBackground(Color.DARK_GRAY);
        //center.add(Box.createRigidArea(new Dimension(100, 1000)), BorderLayout.NORTH);
        center.add(Panelvacio, BorderLayout.NORTH);
        //TODO FER QUE SIGUI LOGOUT

        exit = new JButton("Log out");
        exit.setActionCommand(LOGOUT_BTN);
        exit.setFont(new Font("Helvetica", Font.PLAIN, 20));
        exit.setBackground(Color.RED);
        exit.setBorderPainted(true);
        logoutPanel.add(exit);

        delete = new JButton("Delete Account");
        delete.setActionCommand(DELETE_BTN);
        delete.setFont(new Font("Helvetica", Font.PLAIN, 20));
        delete.setBackground(Color.RED);
        delete.setBorderPainted(true);
        logoutPanel.add(delete);
        center.add(logoutPanel,BorderLayout.SOUTH);


        /*
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(exit, constraints);
        constraints.weightx = 1.0;

         */
        this.add(buttons, BorderLayout.CENTER);
        this.add(logoutPanel, BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.WEST);
        //add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.SOUTH);


    }

    public void registerMenuController(ActionListener listener) {
        playGame.addActionListener(listener);
        reproduce.addActionListener(listener);
        ranking.addActionListener(listener);
        exit.addActionListener(listener);
        delete.addActionListener(listener);
    }

    public void registerBoardController(ActionListener listener) {
        playGame.addActionListener(listener);
    }

    public void registerGameListController(ActionListener listener){

        reproduce.addActionListener(listener);

    }



}