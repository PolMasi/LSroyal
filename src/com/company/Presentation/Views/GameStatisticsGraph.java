package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class GameStatisticsGraph extends JFrame {

    private int livesPlayer;
    private int livesIA;
    private int tropsPlayer;
    private int tropsIA;
    private JProgressBar PBlivesPlayer;
    private JProgressBar PBlivesIA;
    private JProgressBar PBtropesPlayer;
    private JProgressBar PBtropesIA;

    public GameStatisticsGraph(int livesPlayer, int livesIA, int tropsPlayer, int tropsIA) {
        this.livesPlayer = livesPlayer;
        this.livesIA = livesIA;
        this.tropsPlayer = tropsPlayer;
        this.tropsIA = tropsIA;
    }
    public GameStatisticsGraph() {
    }
    // FALTA ESTIRAR LES COSES http://www.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php
    private void  GameStatistics(){
        JPanel informacion = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        informacion.setBackground(Color.DARK_GRAY);

        // Titulo
        JLabel lblinfo = new JLabel("Information",SwingConstants.CENTER);
        lblinfo.setForeground(Color.YELLOW);
        lblinfo.setFont(new Font("Helvetica", Font.BOLD, 75));
        constraints.gridx =2;
        constraints.gridy =1;
        constraints.gridwidth =5; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblinfo, constraints);
        //constraints.weightx = 0.0;

        //LBL LIFES
        JLabel lblLifes = new JLabel("LIFES",SwingConstants.CENTER);
        lblLifes.setForeground(Color.YELLOW);
        lblLifes.setFont(new Font("Helvetica", Font.BOLD, 40));
        constraints.gridx =1;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblLifes, constraints);

        //LBL TROPS
        JLabel lblTropes = new JLabel("TROPES",SwingConstants.CENTER);
        lblTropes.setForeground(Color.YELLOW);
        lblTropes.setFont(new Font("Helvetica", Font.BOLD, 40));
        constraints.gridx =5;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblTropes, constraints);

        // Panel tropas
        JPanel infoTrops = new JPanel();
        infoTrops.setBackground(Color.WHITE);
        constraints.gridx =5;
        constraints.gridy =4;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(infoTrops, constraints);


        // Panel vidas
        JPanel infoLifes = new JPanel(new BorderLayout());
        infoLifes.setBackground(Color.WHITE);
        constraints.gridx =1;
        constraints.gridy =4;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(infoLifes, constraints);

        //LBL TROPS_PLAYER
        JLabel lblTropesPlayer = new JLabel("X",SwingConstants.CENTER); // Aqui en X valor de trops del jugador
        lblTropesPlayer.setForeground(Color.BLACK);
        lblTropesPlayer.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =5;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblTropesPlayer, constraints);

        //LBL TROPS_IA
        JLabel lblTropesIA = new JLabel("XIA",SwingConstants.CENTER); // Aqui en XIA valor de trops de la IA
        lblTropesIA.setForeground(Color.BLACK);
        lblTropesIA.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =5;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblTropesIA, constraints);

        //LBL LIFES_PLAYER
        JLabel lblLifesPlayer = new JLabel("X",SwingConstants.CENTER); // Aqui en X valor de vidas del Jugador
        lblLifesPlayer.setForeground(Color.BLACK);
        lblLifesPlayer.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =5;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblLifesPlayer, constraints);

        //LBL LIFES_IA
        JLabel lblLifesIA = new JLabel("XIA",SwingConstants.CENTER); // Aqui en XIA valor de vidas de la IA
        lblLifesIA.setForeground(Color.BLACK);
        lblLifesIA.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =5;
        constraints.gridy =3;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblLifesIA, constraints);

        // Panel dinero
        JPanel infoMoney = new JPanel(new BorderLayout());
        infoMoney.setBackground(Color.WHITE);
        constraints.gridx =3;
        constraints.gridy =8;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(infoMoney, constraints);

        //LBL dinero
        JLabel lblMoney = new JLabel("money",SwingConstants.CENTER); // aqui valor del dinero
        lblMoney.setForeground(Color.BLACK);
        lblMoney.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =3;
        constraints.gridy =8;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblMoney, constraints);

        //LBL dinero Icono
        JLabel lblMoneyIcon = new JLabel(new ImageIcon("Icon/dineroImagen.png"),SwingConstants.CENTER); // https://programmerclick.com/article/1422148280/
        constraints.gridx =4;
        constraints.gridy =8;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblMoneyIcon, constraints);



        // https://docs.oracle.com/javase/7/docs/api/javax/swing/JProgressBar.html
        PBlivesPlayer = new JProgressBar(0, task.getLengthOfTask()); // AQUI EN EL TASK VA VINCULAT AMB EL JOC EN DIRECTE
        PBlivesPlayer.setValue(0); // Aqui lo inicializo a 0. pero tendra que variar segun evolucione la partida
        PBlivesPlayer.setStringPainted(true);
        PBlivesPlayer.setOrientation(SwingConstants.VERTICAL);
        constraints.gridx =1;
        constraints.gridy =4;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(PBlivesPlayer, constraints);


        PBlivesIA = new JProgressBar(0, task.getLengthOfTask());
        PBlivesIA.setValue(0); // Aqui lo inicializo a 0. pero tendra que variar segun evolucione la partida
        PBlivesIA.setStringPainted(true);
        PBlivesIA.setOrientation(SwingConstants.VERTICAL);
        constraints.gridx =2;
        constraints.gridy =4;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(PBlivesIA, constraints);



        PBtropesPlayer = new JProgressBar(0, task.getLengthOfTask());
        PBtropesPlayer.setValue(0); // Aqui lo inicializo a 0. pero tendra que variar segun evolucione la partida
        PBtropesPlayer.setStringPainted(true);
        PBtropesPlayer.setOrientation(SwingConstants.VERTICAL);
        constraints.gridx =5;
        constraints.gridy =4;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(PBtropesPlayer, constraints);



        PBtropesIA = new JProgressBar(0, task.getLengthOfTask());
        PBtropesIA.setValue(0); // Aqui lo inicializo a 0. pero tendra que variar segun evolucione la partida
        PBtropesIA.setStringPainted(true);
        PBtropesIA.setOrientation(SwingConstants.VERTICAL);
        constraints.gridx =6;
        constraints.gridy =4;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(PBtropesIA, constraints);

    }
}
