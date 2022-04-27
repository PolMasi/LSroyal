package com.company.Presentation.Views;

import java.awt.*;
import javax.swing.*;

public class StartView extends JFrame {

    private JButton SignUp;
    private JButton LogIn;

    public StartView(){
        //dimensiones de tu pantalla
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();     
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new GridBagLayout()); // la interfaz tendra un GrindBag layout

        JLabel title1 = new JLabel("AGE", SwingConstants.CENTER);
        JLabel title2 = new JLabel("ROYALE", SwingConstants.CENTER);

        GridBagConstraints constraints = new GridBagConstraints(); // necesario para el layout
        this.getContentPane().setBackground(Color.DARK_GRAY);

        this.getContentPane().add(title1);
        title1.setForeground(Color.YELLOW);
        title1.setFont(new Font("Helvetica", Font.BOLD, 100));

        constraints.gridx =0; // el area que empieza es columna 0
        constraints.gridy =0; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        this.getContentPane().add(title1, constraints);
        constraints.weightx = 0.0;

        this.getContentPane().add(title2);
        title2.setForeground(Color.YELLOW);
        title2.setFont(new Font("Helvetica", Font.BOLD, 100));

        constraints.gridx =1; // el area que empieza es columna 0
        constraints.gridy =1; // el area que empiza
        constraints.gridwidth =4; // el area que ocupa ancho
        constraints.gridheight =1; // el area que ocupa alto
        constraints.weightx = 1.0;
        this.getContentPane().add(title2, constraints);
        constraints.weightx = 0.0;

        //buttons
        SignUp = new JButton("Sign Up");
        SignUp.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 2; 
        constraints.gridy=3; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.getContentPane().add(SignUp, constraints);
        constraints.weightx = 0.0;

        LogIn = new JButton("Log In");
        LogIn.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1; 
        constraints.gridy=3; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.getContentPane().add(LogIn, constraints);
        constraints.weightx = 1.0;

        JLabel Register = new JLabel("I want to Sign up");
        Register.setForeground(Color.WHITE);
        Register.setFont(new Font("Helvetica", Font.BOLD, 30));
        constraints.gridx= 2; 
        constraints.gridy=2; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        this.getContentPane().add(Register, constraints);

        JLabel Log = new JLabel("I want to Log in");
        Log.setFont(new Font("Helvetica", Font.BOLD, 30));
        Log.setForeground(Color.WHITE);
        constraints.gridx= 1; 
        constraints.gridy=2; 
        constraints.gridwidth=1; 
        constraints.gridheight=1;
        this.getContentPane().add(Log, constraints);
    

    }
public static void main(String[] args){
        StartView view = new StartView();
        view.setVisible(true);
    }

}