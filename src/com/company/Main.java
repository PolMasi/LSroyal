package com.company;

import com.company.Business.*;
import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Persistence.*;
import com.company.Presentation.Controllers.BoardController;
import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.Controllers.LogoutController;
import com.company.Presentation.Controllers.SignUpController;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.*;

import java.sql.DriverManager;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //CONNEXIO BBDD
        //GERARD
        //UserDAO userdao = new UserSQL("agebbdd", "root", "", "jdbc:mysql://localhost/agebbdd", 3306);
        //TONI

        //POL
        UserDAO userdao = new UserSQL("agebbdd", "root", "", "localhost", 3306);
        //GABRIEL

        //MARTA



        Configuration configuration = new Configuration("files/offensive.json", "files/defensive.json");
        configuration.loadOffensiveTroops();

        //Para comprovar
        UserDAO userDAO = new UserDAO() {
            @Override
            public boolean validLogin(String name, String password) {
                if(name.equals("Toni") && password.equals("hola")){
                    return true;
                }
                else{
                    return false;
                }
            }

            @Override
            public boolean validSignUp(String user, String pass, String mail) {
                return true;
            }
        };
        ConfigurationDAO configurationDAO = new ConfigurationDAO() {
            @Override
            public ConfigFile loadConfigFile() {
                return null;
            }

            @Override
            public ArrayList<Offensive> loadOffensiveTroops() {
                Offensive giant = new Offensive("giant", 1000, 5, 1);
                Offensive ibai = new Offensive("ibai", 1500, 6, 2);
                Offensive vinijr = new Offensive("vinijr", 200, 2, 2);
                ArrayList<Offensive> offense = new ArrayList<>();
                offense.add(giant);
                offense.add(ibai);
                offense.add(vinijr);
                return offense;
            }

            @Override
            public ArrayList<Defensive> loadDefensiveTroops() {
                Defensive giant = new Defensive("giant", 1000, 5, 1);
                Defensive ibai = new Defensive("ibai", 1500, 6, 2);
                Defensive vinijr = new Defensive("vinijr", 200, 2, 2);
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

        MainView mainView = new MainView(loginView,logoutView,signupView, startView, boardView);
        MainController mainController = new MainController(mainView);

        UserOption userOption = new UserOption();
        UserModel loginModel = new UserModel(userdao, userOption);
        PlayerModel boardModel = new PlayerModel(configurationDAO.loadOffensiveTroops(), configurationDAO.loadDefensiveTroops());
        ComputerModel computerModel = new ComputerModel(configurationDAO);
        LogicModel logicModel = new LogicModel(computerModel, boardModel, configurationDAO.loadOffensiveTroops(), configurationDAO.loadDefensiveTroops());

        LoginController loginController = new LoginController(loginModel, loginView, mainController);
        SignUpController signUpController = new SignUpController(loginModel, signupView, mainController);
        LogoutController logoutController = new LogoutController(loginModel, logoutView, mainController);
        BoardController boardController = new BoardController(logicModel,boardView, mainController);


        mainView.setVisible(true);


    }
}
