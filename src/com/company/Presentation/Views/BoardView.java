package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class BoardView extends JPanel {
    private GridLayout gridBoard;
    public static final int ROWS = 8;
    public static final int COLUMNS = 7;

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

        setLayout(new GridLayout(1,2));

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
        //add(title, BorderLayout.NORTH);
        JPanel informacion = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        informacion.setBackground(Color.DARK_GRAY);

        // Jpanels vacio
        JPanel jpvacio = new JPanel();
        jpvacio.setBackground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy =0;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =11; // el area que ocupa alto
        constraints.weighty = 1.0;
        //constraints.weighty = 1.0;
        informacion.add(jpvacio, constraints);
        constraints.weighty = 0.0;
        //constraints.weighty = 0.0;

        // Jpanels vacio
        JPanel jpvacio1 = new JPanel();
        jpvacio1.setBackground(Color.DARK_GRAY);
        constraints.gridx = 16;
        constraints.gridy =0;
        constraints.gridwidth =1; // el area que ocupa ancho
        constraints.gridheight =11; // el area que ocupa alto
        //constraints.weighty = 1.0;
        constraints.weighty = 1.0;
        informacion.add(jpvacio1, constraints);
        constraints.weighty = 0.0;
        //constraints.weighty = 0.0;

        // Jpanels vacio
        JPanel jpvacio2 = new JPanel();
        jpvacio2.setBackground(Color.DARK_GRAY);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy =0;
        constraints.gridwidth =17; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        informacion.add(jpvacio2, constraints);
        constraints.weighty = 0.0;

        // Jpanels vacio
        JPanel jpvacio3 = new JPanel();
        jpvacio3.setBackground(Color.DARK_GRAY);
        constraints.gridx =0;
        constraints.gridy =10;
        constraints.gridwidth =17; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        informacion.add(jpvacio3, constraints);
        constraints.weightx = 0.0;

        // Titulo
        JLabel lblinfo = new JLabel("Information",SwingConstants.CENTER);
        lblinfo.setForeground(Color.YELLOW);
        lblinfo.setFont(new Font("Helvetica", Font.BOLD, 50));
        constraints.gridx =2;
        constraints.gridy =1;
        constraints.gridwidth =13; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        //constraints.weighty = 1.0;
        informacion.add(lblinfo, constraints);
        constraints.weightx = 0.0;
        //constraints.weighty = 0.0;

        //LBL LIFES
        JLabel lblLifes = new JLabel("LIFES",SwingConstants.CENTER);
        lblLifes.setForeground(Color.YELLOW);
        lblLifes.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =1;
        constraints.gridy =3;
        constraints.gridwidth =5; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        informacion.add(lblLifes, constraints);
        constraints.weightx = 0.0;

        //LBL TROPS
        JLabel lblTropes = new JLabel("TROPES",SwingConstants.CENTER);
        lblTropes.setForeground(Color.YELLOW);
        lblTropes.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =10;
        constraints.gridy =3;
        constraints.gridwidth =5; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        informacion.add(lblTropes, constraints);
        constraints.weightx = 0.0;

        // Panel tropas
        JPanel infoTrops = new JPanel(new GridLayout(1, 1));
        infoTrops.setBackground(Color.WHITE);
        constraints.gridx =10;
        constraints.gridy =4;
        constraints.gridwidth =5; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        informacion.add(infoTrops, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        // Panel vidas
        JPanel infoLifes = new JPanel(new GridLayout(1, 1));
        infoLifes.setBackground(Color.WHITE);
        constraints.gridx =1;
        constraints.gridy =4;
        constraints.gridwidth =5; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        informacion.add(infoLifes, constraints);
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        //LBL TROPS_PLAYER
        JLabel lblTropesPlayer = new JLabel("X"); // Aqui en X valor de trops del jugador
        lblTropesPlayer.setForeground(Color.WHITE);
        lblTropesPlayer.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =11;
        constraints.gridy =5;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblTropesPlayer, constraints);

        //LBL TROPS_IA
        JLabel lblTropesIA = new JLabel("XIA",SwingConstants.RIGHT); // Aqui en XIA valor de trops de la IA
        lblTropesIA.setForeground(Color.WHITE);
        lblTropesIA.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =13;
        constraints.gridy =5;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblTropesIA, constraints);

        //LBL LIFES_PLAYER
        JLabel lblLifesPlayer = new JLabel("X",SwingConstants.RIGHT); // Aqui en X valor de vidas del Jugador
        lblLifesPlayer.setForeground(Color.WHITE);
        lblLifesPlayer.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =2;
        constraints.gridy =5;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblLifesPlayer, constraints);

        //LBL LIFES_IA
        JLabel lblLifesIA = new JLabel("XIA"); // Aqui en XIA valor de vidas de la IA
        lblLifesIA.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLifesIA.setForeground(Color.WHITE);
        lblLifesIA.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =4;
        constraints.gridy =5;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblLifesIA, constraints);

        // Panel dinero
        JPanel infoMoney = new JPanel();
        infoMoney.setBackground(Color.WHITE);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx =7;
        constraints.gridy =7;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =3; // el area que ocupa alto
        constraints.weightx = 1.0;
        informacion.add(infoMoney, constraints);
        constraints.weightx = 0.0;

        //LBL dinero
        JLabel lblMoney = new JLabel("MONEY",SwingConstants.CENTER); // aqui valor del dinero
        lblMoney.setForeground(Color.YELLOW);
        lblMoney.setFont(new Font("Helvetica", Font.BOLD, 20));
        constraints.gridx =7;
        constraints.gridy =6;
        constraints.gridwidth =2; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        //constraints.weightx = 1.0;
        informacion.add(lblMoney, constraints);
        this.add(informacion);

        // LBL dinero total
        JLabel lblMoneyTotal = new JLabel("50"); // aqui hay que poner el dinero en directo
        lblMoneyTotal.setFont(new Font("Helvetica", Font.BOLD, 20));
        infoMoney.add(lblMoneyTotal);

        //LBL dinero $
        //JLabel lblMoneyIcon = new JLabel(new ImageIcon("dineroImagen.png"),SwingConstants.CENTER); // https://programmerclick.com/article/1422148280/
        JLabel lblMoneyIcon = new JLabel("$");
        lblMoneyIcon.setFont(new Font("Helvetica", Font.BOLD, 20));
        infoMoney.add(lblMoneyIcon);

        JProgressBar PBlivesPlayer = new JProgressBar(0, 10); // AQUI EN EL TASK VA VINCULAT AMB EL JOC EN DIRECTE
        PBlivesPlayer.setValue(2); // Aqui lo inicializo a 2. pero tendra que variar segun evolucione la partida
        PBlivesPlayer.setStringPainted(true);
        PBlivesPlayer.setOrientation(SwingConstants.VERTICAL);
        infoLifes.add(PBlivesPlayer);

        JProgressBar PBlivesIA = new JProgressBar(0, 10);
        PBlivesIA.setValue(0); // Aqui lo inicializo a 0. pero tendra que variar segun evolucione la partida
        PBlivesIA.setStringPainted(true);
        PBlivesIA.setOrientation(SwingConstants.VERTICAL);
        infoLifes.add(PBlivesIA);

        JProgressBar PBtropesPlayer = new JProgressBar(0, 10);
        PBtropesPlayer.setValue(5); // Aqui lo inicializo a 5. pero tendra que variar segun evolucione la partida
        PBtropesPlayer.setStringPainted(true);
        PBtropesPlayer.setOrientation(SwingConstants.VERTICAL);
        infoTrops.add(PBtropesPlayer);

        JProgressBar PBtropesIA = new JProgressBar(0, 10);
        PBtropesIA.setValue(3); // Aqui lo inicializo a 3. pero tendra que variar segun evolucione la partida
        PBtropesIA.setStringPainted(true);
        PBtropesIA.setOrientation(SwingConstants.VERTICAL);
        infoTrops.add(PBtropesIA );
        add(informacion);
    }
    // http://www.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php


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
