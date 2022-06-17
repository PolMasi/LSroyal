package com.company.Presentation.Views;

import com.company.Presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel de la vista del menu del joc
 */
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


    /**
     * Contructor de la funció on es configura el panel
     */
    public MenuView(){
        this.setLayout(new BorderLayout()); // la interfaz tendra un layout

        JLabel title1 = new JLabel("MAIN MENU", SwingConstants.CENTER);

        this.setBackground(Color.DARK_GRAY);
        this.add(title1, BorderLayout.NORTH);
        title1.setForeground(Color.YELLOW);
        title1.setFont(new Font("Helvetica", Font.BOLD, 100));

        FlowLayout flowLayout =  new FlowLayout();
        JPanel buttons = new JPanel(flowLayout);
        buttons.setBackground(Color.DARK_GRAY);
        JPanel center = new JPanel(new BorderLayout());

        //BUTTONS
        playGame = new JButton("Play");
        playGame.setActionCommand(MenuView.PLAYGAME_BTN);
        playGame.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttons.add(playGame);


        reproduce = new JButton("Reproduce game");
        reproduce.setActionCommand(MenuView.REPRODUCE_BTN);
        reproduce.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttons.add(reproduce);


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

        this.add(buttons, BorderLayout.CENTER);
        this.add(logoutPanel, BorderLayout.SOUTH);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.WEST);
    }

    /**
     * Control de botons de jugar la partida, reprodir una partida, el ranking, logout i borrar un usuari
     * @param listener paramete per saber on estem
     */
    public void registerMenuController(ActionListener listener) {
        playGame.addActionListener(listener);
        reproduce.addActionListener(listener);
        ranking.addActionListener(listener);
        exit.addActionListener(listener);
        delete.addActionListener(listener);
    }

    /**
     * Control del boto per jugar la partida
     * @param listener paramete per saber on estem
     */
    public void registerBoardController(ActionListener listener) {
        playGame.addActionListener(listener);
    }

    /**
     * Cotrol del boto per reproduir la partida
     * @param listener paramete per saber on estem
     */
    public void registerGameListController(ActionListener listener){

        reproduce.addActionListener(listener);

    }

}