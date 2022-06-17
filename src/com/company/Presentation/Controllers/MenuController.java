package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Main;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.MenuView;
import com.company.Presentation.Views.RankingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cotrola la funcinalitat del menu
 */
public class MenuController implements ActionListener {
    private MenuView menuView;
    private UserModel userModel;
    private MainController mainController;      //TODO preguntar Edu
    private RankingView rankingView;
    private final static String LOGOUT_TEXT = "Do you want to logout?";
    private final static String DELETE_TEXT = "If you want to delete your account write your username:";
    private final static String[] LOGOUT_OPT = {"YES","NO"};

    /**
     * Constructor de la clase MenuCotroller
     * @param menuView conté la informació de la vista del menu
     * @param userModel  conté la informació del ususaris
     * @param mainController conte informació el controlador principal de les vistes
     * @param rankingView conté la informació de la vista del ranking
     */
    public MenuController(MenuView menuView, UserModel  userModel, MainController mainController, RankingView rankingView) {
        this.menuView = menuView;
        this.userModel = userModel;
        this.mainController = mainController;
        this.rankingView = rankingView;
        registerMenuViewListener();
    }

    /**
     * Boto per el registre del menu principal
     */
    public void registerMenuViewListener() {
        this.menuView.registerMenuController(this);
    }

    /**
     * mostra mistges depenent de la acció y el event actual al menu
     * @param e variable per controlar la acció
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case MenuView.PLAYGAME_BTN : mainController.switchView(MainView.BOARD_VIEW);
            break;
            case MenuView.LOGOUT_BTN :
                if(mainController.showConfirm(LOGOUT_TEXT, LOGOUT_OPT) == 0) {
                    userModel.logout();
                    mainController.switchView(MainView.START_VIEW);
                }
            break;
            case MenuView.DELETE_BTN:
                if(userModel.checkDeleteUserName(mainController.showInput(DELETE_TEXT))) {
                    userModel.delete();
                    mainController.switchView(MainView.START_VIEW);
                }
                break;

            case MenuView.RANKING_BTN : rankingView.configPanel(userModel.getRanking(), this);
            mainController.switchView(MainView.RANKING_VIEW);
            break;

            case RankingView.RANKING_BACK: mainController.switchView(MainView.MENU_VIEW);
            break;

            case MenuView.REPRODUCE_BTN:  mainController.switchView(MainView.GAMELIST_VIEW);

        }
    }
}
