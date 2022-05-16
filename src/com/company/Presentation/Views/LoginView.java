package com.company.Presentation.Views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {     //JFrame es una ventana //JPanel panel dentro de la ventana
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton goBack;

    public static final String BUTTON_LOGIN = "BUTTON_LOGIN";
    public static final String LOGIN_BACK_BTN = "LOGIN_BACK_BTN";

    public LoginView() throws HeadlessException {
        configurePanel();
    }

    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());       // bordes
        JPanel center = new JPanel(new FlowLayout());       // arriba y abajo
        JPanel bottom = new JPanel();
        setLayout(new BorderLayout());

        JLabel title = new JLabel("READY TO PLAY?");
        this.setBackground(Color.DARK_GRAY);
        title.setFont(new Font("Helvetica", Font.BOLD, 100));
        title.setForeground(Color.WHITE);
        loginButton = new JButton("ACCEDER");
        goBack = new JButton("Go back");

        goBack.setActionCommand(String.valueOf(CardEnum.LOGIN_BACK_BTN));

        Container user = new Container();
        Container password = new Container();

        user.setLayout(new BoxLayout(user, BoxLayout.PAGE_AXIS));
        password.setLayout(new BoxLayout(password, BoxLayout.PAGE_AXIS));
        bottom.setLayout(new FlowLayout());

        user.add(new JLabel("USUARI@"));
        userText = new JTextField(20);
        user.add(userText);

        password.add(new JLabel("CONTRASEÑA"));
        passwordText  = new JPasswordField(20);
        password.add(passwordText);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottom.add(goBack);
        bottom.add(loginButton);


        center.add(user);
        center.add(password);


        title.setHorizontalAlignment(JLabel.CENTER);
        loginButton.setActionCommand(String.valueOf(CardEnum.BUTTON_LOGIN));
        main.add(Box.createRigidArea(new Dimension(50, 100)), BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);

        add(title, BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
    }

    public void registerLoginController(ActionListener listener){
        loginButton.addActionListener(listener);
    }

    public void registerController(ActionListener listener) {
        goBack.addActionListener(listener);
    }

    public String getUser(){
        return userText.getText();
    }

    public String getPassword(){
        return passwordText.getText();
    }
}
