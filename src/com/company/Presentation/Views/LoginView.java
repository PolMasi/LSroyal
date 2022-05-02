package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {     //JFrame es una ventana //JPanel panel dentro de la ventana
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton button;

    public static final String BUTTON_LOGIN = "BUTTON_LOGIN";

    public LoginView() throws HeadlessException {
        configurePanel();
    }

    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());       // bordes
        JPanel center = new JPanel(new FlowLayout());       // arriba y abajo

        JLabel title = new JLabel("READY TO PLAY?");
        this.setBackground(Color.DARK_GRAY);

        button = new JButton("ACCEDER");

        Container user = new Container();
        Container password = new Container();

        user.setLayout(new BoxLayout(user, BoxLayout.PAGE_AXIS));
        password.setLayout(new BoxLayout(password, BoxLayout.PAGE_AXIS));

        user.add(new JLabel("USUARI@"));
        userText = new JTextField(20);
        user.add(userText);

        password.add(new JLabel("CONTRASEÑA"));
        passwordText  = new JPasswordField(20);
        password.add(passwordText);

        center.add(user);
        center.add(password);
        center.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        title.setHorizontalAlignment(JLabel.CENTER);
        button.setActionCommand(BUTTON_LOGIN);

        main.add(center, BorderLayout.CENTER);
        main.add(title, BorderLayout.NORTH);
        main.add(button, BorderLayout.SOUTH);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);

        add(main);
    }

    public void registerController(ActionListener listener){
        button.addActionListener(listener);
    }

    public String getUser(){
        return userText.getText();
    }

    public String getPassword(){
        return passwordText.getText();
    }
}
