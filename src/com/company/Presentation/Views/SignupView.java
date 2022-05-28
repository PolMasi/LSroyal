package com.company.Presentation.Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;

/**
 * Controlar la pantalla de sign up
 */
public class SignupView extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JTextField emailText;
    private JPasswordField passwordConfirmText;
    private JButton signUp;
    private JButton goBack;

    public static final String SIGNUP_BTN = "SIGNUP_BTN";
    public static final String SIGNUP_BACK_BTN = "SIGNUP_BACK_BTN";

    /**
     * Contructor de la funció on es configura el panel
     */
    public SignupView (){
        JPanel panel = new JPanel(new BorderLayout());

        //BUTTONS
        signUp = new JButton("Sign up");
        goBack = new JButton("Go back");
        signUp.setActionCommand(SignupView.SIGNUP_BTN);
        goBack.setActionCommand(SignupView.SIGNUP_BACK_BTN);
        
        //JLabel 
        JLabel title = new JLabel("REGISTER FORM",SwingConstants.CENTER);
        JLabel name = new JLabel("Name", SwingConstants.RIGHT);
        JLabel email  = new JLabel("Email", SwingConstants.RIGHT);
        JLabel passwordConfirm = new JLabel("Password Confirmation", SwingConstants.RIGHT);
        JLabel password = new JLabel("Password", SwingConstants.RIGHT);
        
        // font de las etiquetas y buttons
        title.setFont(new Font("Helvetica", Font.BOLD, 100));
        title.setForeground(Color.YELLOW);

        name.setFont(new Font("Helvetica", Font.PLAIN, 20));
        email.setFont(new Font("Helvetica", Font.PLAIN, 20));
        passwordConfirm.setFont(new Font("Helvetica", Font.PLAIN, 20));
        password.setFont(new Font("Helvetica", Font.PLAIN, 20));

        goBack.setFont(new Font("Helvetica", Font.PLAIN, 20));
        signUp.setFont(new Font("Helvetica", Font.BOLD, 20));

        //text introduced
        userText = new JTextField(20);
        emailText = new JTextField(20);
        passwordText = new JPasswordField(20);
        passwordConfirmText = new JPasswordField(20);
        
        // recuadro grande, toda la pantalla
        this.setBackground(Color.DARK_GRAY);
        
        //bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(signUp);
        bottomPanel.add(goBack);

        // central panel
        JPanel gridJPanel = new JPanel();
        gridJPanel.setLayout(new GridLayout(4,2,15,10));
        //gridJPanel.setBorder(new LineBorder(Color.BLACK, 1));
        gridJPanel.setBackground(Color.LIGHT_GRAY);

        //name
        gridJPanel.add(name);
        gridJPanel.add(userText);

        //email
        gridJPanel.add(email);
        gridJPanel.add(emailText);

        //password
        gridJPanel.add(password);
        gridJPanel.add(passwordText);
        
        //password confirmation
        gridJPanel.add(passwordConfirm);
        gridJPanel.add(passwordConfirmText);
        
        gridJPanel.setBorder(new LineBorder(Color.ORANGE, 2));

        //posicion
        panel.add(gridJPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);


    }

    /**
     * Controla el boto de registre del sign up
     * @param listener paramete per saber on estem
     */
    public void registerSignUpController(ActionListener listener) {
        signUp.addActionListener(listener);
    }

    /**
     * Controla el boto de sortir del panel
     * @param listener paramete per saber on estem
     */
    public void registerController(ActionListener listener) {
        goBack.addActionListener(listener);
    }

    /**
     * getter del user
     * @return user
     */
    public String getUser(){
        return userText.getText();
    }

    /**
     * getter del mail
     * @return mail
     */
    public String getEmail(){
        return emailText.getText();
    }


    /**
     * getter de la contrasenya
     * @return la contraseña amagada
     */
    public String getPassword(){
        return passwordText.getText();
    }

    /**
     * getter de la confirmació de contrasenya
     * @return la confirmació de la contrasenya amagada
     */
    public String getPasswordConfirm(){
        return passwordConfirmText.getText();
    }

    /**
     * Eliminar la informació escrita previament una vegada se cambia de frame
     */
    public void clear(){userText.setText(""); emailText.setText(""); passwordText.setText(""); passwordConfirmText.setText("");}

}