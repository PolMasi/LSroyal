package com.company.Presentation.Views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Controlar la pantalla d'inici
 */
public class StartView extends JPanel {
    private JButton signUp;
    private JButton logIn;

    public static final String START_LOGIN_BTN = "START_LOGIN_BTN";
    public static final String START_SIGNUP_BTN = "START_SIGNUP_BTN";

    /**
     * Contructor de la funció on es configura el panel
     */
    public StartView(){
        this.setLayout(new GridBagLayout()); // la interfaz tendra un GrindBag layout

        JLabel title1 = new JLabel("AGE", SwingConstants.CENTER);
        JLabel title2 = new JLabel("ROYALE", SwingConstants.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();// necesario para el layout
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
        signUp = new JButton("Sign Up");
        signUp.setActionCommand(StartView.START_SIGNUP_BTN);
        signUp.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 2; 
        constraints.gridy=3; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(signUp, constraints);
        constraints.weightx = 0.0;



        logIn = new JButton("Log In");
        logIn.setActionCommand(StartView.START_LOGIN_BTN);
        logIn.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1; 
        constraints.gridy=3; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(logIn, constraints);
        constraints.weightx = 1.0;

        JLabel Register = new JLabel("I want to Sign up");
        Register.setForeground(Color.WHITE);
        Register.setFont(new Font("Helvetica", Font.BOLD, 30));
        constraints.gridx= 2; 
        constraints.gridy=2; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        this.add(Register, constraints);

        JLabel Log = new JLabel("I want to Log in");
        Log.setFont(new Font("Helvetica", Font.BOLD, 30));
        Log.setForeground(Color.WHITE);
        constraints.gridx= 1; 
        constraints.gridy=2; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        this.add(Log, constraints);


    }

    /**
     * Control de los botons del log in i el sign up
     * @param listener paramete per saber on estem
     */
    public void registerController(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);

    }

}

