package com.company.Presentation;

import com.company.Presentation.Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Serveix per canviar de vistes segons el buto que es premi
 */
public class MainController implements ActionListener {
    private MainView mainView;

    /**
     * Inicialitzem la classe mainView
     * @param mainView classe de totes les vistes
     */
    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    /**
     * Asignem el listener al controller del taulell
     * @param listener parametre per sabes on estem
     */
    public void setBoardController(ActionListener listener) {
        mainView.setBoardListener(listener);
    }

    /**
     * Asignem el listener per controlar la lista de la partida
     * @param actionListener per detectar y manejar events d'acció
     */
    public void setGameListController(ActionListener actionListener){

        mainView.setGameListListener(actionListener);

    }
    /**
     * Funcio per canviar de vista desde el controller
     * @param view
     */
    public void switchView(String view) {
        mainView.switchView(view);
    }


    /**
     * Mostra un misatge d'error
     * @param error string que conté l'error
     */
    public void showError(String error) {
        mainView.showError(error);
    }

    /**
     * Mostra un missatge
     * @param text string que conté informació
     * @return la informació
     */
    public String showInput(String text){

        return mainView.showInputPopUp(text);
    }

    /**
     * mostra missatge de confirmació
     * @param text string que conté informació
     * @param questions array de strings que conté preguntes
     * @return la informació y les preguntes
     */
    public int showConfirm(String text, String[] questions) {
        return mainView.showConfirmPopUp(text, questions);
    }

    /**
     * Organiza les distintes posibles opcions
     * @param e controlar el switch de les opcions
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){


            case StartView.START_LOGIN_BTN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;

            case StartView.START_SIGNUP_BTN:
                mainView.switchView(MainView.SIGNUP_VIEW);
                break;

            case SignupView.SIGNUP_BACK_BTN:

            case LoginView.LOGIN_BACK_BTN:
                mainView.switchView(MainView.START_VIEW);
                break;
            case RankingView.RANKING_BACK:
            case LoginView.BUTTON_LOGIN:
                mainView.switchView(MainView.MENU_VIEW);
                break;

        }
    }
}
