package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutView extends JPanel {
    private JTextField warningMesage;
    private JButton logoutButton;
    private JButton backbutton;

    public static final String LOGOUT_BTN = "LOGOUT_BTN";
    public static final String LOGOUT_BACK_BTN = "LOGOUT_BACK_BTN";

    public LogoutView() throws HeadlessException {
        configurePanel();
    }

    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Are you sure you want to logout?");

        logoutButton = new JButton("LOGOUT");
        backbutton = new JButton("BACK");

        logoutButton.setActionCommand(LOGOUT_BTN);      //la frase que le pasara a los listeners
        backbutton.setActionCommand(LOGOUT_BACK_BTN);

        Container buttons = new Container();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        buttons.add(logoutButton);
        buttons.add(backbutton);

        title.setHorizontalAlignment(JLabel.CENTER);

        main.add(title, BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);

        add(main);
    }

    public void registerLogoutController(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void registerController(ActionListener listener){
        backbutton.addActionListener(listener);
    }
}
