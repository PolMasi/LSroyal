package com.company;

import com.company.Business.*;
import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Persistence.*;
import com.company.Presentation.Controllers.*;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.*;

import java.sql.DriverManager;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        Configuration configuration = new Configuration("files/offensive.json", "files/defensive.json", "files/config.json");
        configuration.loadOffensiveTroops();
        UserDAO userdao = new UserSQL(configuration.getName(), configuration.getUser(), configuration.getPassword(), configuration.getIP(), Integer.parseInt(configuration.getPort()), "files/user.txt");
        GameDAO gamedao = new GameSQL(configuration.getName(), configuration.getUser(), configuration.getPassword(), configuration.getIP(), Integer.parseInt(configuration.getPort()));

        //ArrayList<String[]> nombre = gameSQL.getMatchList("gerard4");



        ConfigurationDAO configurationDAO = new ConfigurationDAO() {
            @Override
            public void loadConfigFile() {

            }

            @Override
            public ArrayList<Offensive> loadOffensiveTroops() {
                Offensive giant = new Offensive("giant", 1000, 4, 1, 300);
                Offensive ibai = new Offensive("ibai", 1500, 6, 2, 400);
                Offensive vinijr = new Offensive("vinijr", 200, 4, 2, 500);
                ArrayList<Offensive> offense = new ArrayList<>();
                offense.add(giant);
                offense.add(ibai);
                offense.add(vinijr);
                return offense;
            }

            @Override
            public ArrayList<Defensive> loadDefensiveTroops() {
                Defensive giant = new Defensive("fio", 1000, 4, 1, 300);
                Defensive ibai = new Defensive("retro", 1500, 6, 2, 400);
                Defensive vinijr = new Defensive("defeo", 200, 4, 2, 500);
                ArrayList<Defensive> defense = new ArrayList<>();
                defense.add(giant);
                defense.add(ibai);
                defense.add(vinijr);
                return defense;
            }
        };

        LoginView loginView = new LoginView();
        SignupView signupView = new SignupView();
        StartView startView = new StartView();
        LogoutView logoutView = new LogoutView();
        BoardView boardView = new BoardView();
        MenuView menuView = new MenuView();

        MainView mainView = new MainView(loginView,logoutView,signupView, startView, boardView, menuView);
        MainController mainController = new MainController(mainView);

        UserOption userOption = new UserOption();
        UserModel loginModel = new UserModel(userdao, userOption);
        LogicModel logicModel = new LogicModel(configurationDAO.loadOffensiveTroops(), configurationDAO.loadDefensiveTroops(), gamedao, userdao);

        LoginController loginController = new LoginController(loginModel, loginView, mainController);
        SignUpController signUpController = new SignUpController(loginModel, signupView, mainController);
        LogoutController logoutController = new LogoutController(loginModel, logoutView, mainController);
        BoardController boardController = new BoardController(logicModel,boardView, mainController);
        MenuController menuController = new MenuController(menuView, mainController);
        startView.registerDeleteController(logoutController);

        mainView.setVisible(true);


    }
}
