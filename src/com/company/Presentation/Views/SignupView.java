package com.company.Presentation.Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;


public class SignupView extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JTextField emailText;
    private JPasswordField passwordConfirmText;
    private JButton signUp;
    private JButton goBack;

    public static final String SIGNUP_BTN = "SIGNUP_BTN";
    public static final String SIGNUP_BACK_BTN = "SIGNUP_BACK_BTN";

    public SignupView (){
        JPanel panel = new JPanel(new BorderLayout());

        //BUTTONS
        signUp = new JButton("Sign up");
        goBack = new JButton("Go back");
        signUp.setActionCommand(String.valueOf(CardEnum.SIGNUP_BTN));
        goBack.setActionCommand(String.valueOf(CardEnum.SIGNUP_BACK_BTN));
        
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

    public void registerSignUpController(ActionListener listener) {
        signUp.addActionListener(listener);
    }

    public void registerController(ActionListener listener) {
        goBack.addActionListener(listener);
    }
   
    public String getUser(){
        return userText.getText();
    }
    public String getEmail(){
        return emailText.getText();
    }
    public String getPassword(){
        return passwordText.getText();
    }
    public String getPasswordConfirm(){
        return passwordConfirmText.getText();
    }

}