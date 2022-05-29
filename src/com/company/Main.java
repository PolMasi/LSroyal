package com.company;

import com.company.Business.*;
import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Persistence.*;
import com.company.Presentation.Controllers.*;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.*;
import com.google.gson.Gson;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Realitzem totes les comandes per poder executar el programa
 */
public class Main {

    public static void main(String[] args) {

        Configuration configuration = new Configuration("files/offensive.json", "files/defensive.json", "files/config.json");
        UserDAO userdao = new UserSQL(configuration.getName(), configuration.getUser(), configuration.getPassword(), configuration.getIP(), Integer.parseInt(configuration.getPort()), "files/user.txt");
        GameDAO gamedao = new GameSQL(configuration.getName(), configuration.getUser(), configuration.getPassword(), configuration.getIP(), Integer.parseInt(configuration.getPort()));

        LoginView loginView = new LoginView();
        SignupView signupView = new SignupView();
        StartView startView = new StartView();
        BoardView boardView = new BoardView();
        MenuView menuView = new MenuView();
        RankingView rankingView = new RankingView();
        GameListView gameListView = new GameListView();

        MainView mainView = new MainView(loginView, signupView, startView, boardView, menuView, rankingView, gameListView);
        MainController mainController = new MainController(mainView);

        UserOption userOption = new UserOption();
        UserModel loginModel = new UserModel(userdao, userOption);
        LogicModel logicModel = new LogicModel(configuration.loadOffensiveTroops(), configuration.loadDefensiveTroops(), gamedao, userdao);
        SwingUtilities.invokeLater(logicModel);


        LoginController loginController = new LoginController(loginModel, loginView, mainController);
        SignUpController signUpController = new SignUpController(loginModel, signupView, mainController);
        BoardController boardController = new BoardController(logicModel,boardView, mainController);
        MenuController menuController = new MenuController(menuView, loginModel, mainController, rankingView);
        ReplayController gameListController = new ReplayController(logicModel, gameListView, mainController, boardView);
        mainView.setVisible(true);

    }
}
