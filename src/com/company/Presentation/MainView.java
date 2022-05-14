package com.company.Presentation;

import com.company.Presentation.Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private LoginView loginView;
    private LogoutView logoutView;
    private SignupView signupView;
    private StartView startView;
    private BoardView boardView;

    private CardLayout cardLayout;      // gestionaremos como una baraja de cartas y este tendra todas las vista(cartas)

    public static final String LOGIN_VIEW = "LOGIN_VIEW";
    public static final String LOGOUT_VIEW = "LOGOUT_VIEW";
    public static final String SIGNUP_VIEW = "SIGNUP_VIEW";
    public static final String START_VIEW = "START_VIEW";
    public static final String BOARD_VIEW = "BOARD_VIEW";

    public static final String TITLE = "LSROYAL";

    public MainView(LoginView loginView, LogoutView logoutView, SignupView signupView,
                    StartView startView, BoardView boardView) {
        this.startView = startView;
        this.loginView = loginView;
        this.logoutView = logoutView;
        this.signupView = signupView;
        this.boardView = boardView;
        configureLayout();
        configurationFrame();
    }

    private void configurationFrame() {
        pack();     //dejarlo compacto
        setTitle(TITLE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();      //dimensiones de tu pantalla
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //que pasara cuando cierres la pantalla //Acabar el programa
    }

    /**
     * Añadimos todas las vistas al cardlayout
     */
    private void configureLayout() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);     //asignamos a esta ventana el cardlayout
        //this.add(MainView.BOARD_VIEW, boardView);
        this.add(String.valueOf(CardEnum.START_VIEW), startView);
        this.add(String.valueOf(CardEnum.LOGIN_VIEW), loginView);    //le asginamos una vista con una frase y cuando le pasamos la frase pasa la vista asociada
        this.add(String.valueOf(CardEnum.SIGNUP_VIEW), signupView);
        this.add(String.valueOf(CardEnum.LOGOUT_VIEW), logoutView);
    }

    /**
     * Cambiar de vista
     * @param view String que identifica la vista que hay que cambiar
     */
    public void switchView(String view) {
        cardLayout.show(getContentPane(),view);
    }

    /**
     * Asignar el mismo listener a todas las vistas
     * @param listener
     */
    public void setListeners(ActionListener listener){
        loginView.registerController(listener);
        logoutView.registerController(listener);
        startView.registerController(listener);
        signupView.registerController(listener);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
}
