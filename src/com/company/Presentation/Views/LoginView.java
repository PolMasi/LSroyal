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

        JLabel title = new JLabel("READY TO PLAY?");
        this.setBackground(Color.DARK_GRAY);

        loginButton = new JButton("ACCEDER");
        goBack = new JButton("Go back");

        goBack.setActionCommand(String.valueOf(CardEnum.LOGIN_BACK_BTN));

        Container user = new Container();
        Container password = new Container();

        user.setLayout(new BoxLayout(user, BoxLayout.PAGE_AXIS));
        password.setLayout(new BoxLayout(password, BoxLayout.PAGE_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));

        user.add(new JLabel("USUARI@"));
        userText = new JTextField(20);
        user.add(userText);

        password.add(new JLabel("CONTRASEÑA"));
        passwordText  = new JPasswordField(20);
        password.add(passwordText);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottom.add(loginButton);
        bottom.add(goBack);

        center.add(user);
        center.add(password);
        center.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        title.setHorizontalAlignment(JLabel.CENTER);
        loginButton.setActionCommand(String.valueOf(CardEnum.BUTTON_LOGIN));

        main.add(center, BorderLayout.CENTER);
        main.add(title, BorderLayout.NORTH);
        main.add(bottom, BorderLayout.SOUTH);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);

        add(main);
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
