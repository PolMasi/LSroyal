package com.company.Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogoutView extends JFrame {
    private JTextField warningMesage;
    private JButton LogoutButton;
    private JButton Backbutton;

    public LogoutView() throws HeadlessException {
        configurePanel();
        configurationFrame();

    }

    private void configurationFrame() {
        pack();
        setTitle("Logout");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());

        JLabel title = new JLabel("Are you sure you want to logout?");

        LogoutButton = new JButton("LOGOUT");
        Backbutton = new JButton("BACK");

        Container buttons = new Container();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        buttons.add(LogoutButton);
        buttons.add(Backbutton);

        title.setHorizontalAlignment(JLabel.CENTER);

        main.add(title, BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.EAST);
        main.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.WEST);

        add(main);
    }
    public void registerController(ActionListener listener){
        LogoutButton.addActionListener(listener);
        Backbutton.addActionListener(listener);
    }
}
