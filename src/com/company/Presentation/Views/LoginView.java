package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {     //JFrame es una ventana //JPanel panel dentro de la ventana
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton button;

    public static final String BUTTON_LOGIN = "BUTTON_LOGIN";

    public LoginView() throws HeadlessException {
        configurePanel();
        configurationFrame();

    }

    private void configurationFrame() {
        pack();     //dejarlo compacto
        setTitle("Login");
       // Dimension size = Toolkit.getDefaultToolkit().getScreenSize();      //dimensiones de tu pantalla
      //  setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //que pasara cuando cierres la pantalla //Acabar el programa
    }

    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());

        JLabel title = new JLabel("READY TO PLAY?");

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
