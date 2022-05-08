package com.company;

import com.company.Business.PlayerModel;
import com.company.Business.UserModel;
import com.company.Business.UserOption;
import com.company.Persistence.Configuration;
import com.company.Persistence.UserDAO;
import com.company.Persistence.UserSQL;
import com.company.Presentation.Controllers.BoardController;
import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.Controllers.LogoutController;
import com.company.Presentation.Controllers.SignUpController;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.*;

import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        UserDAO userdao = new UserSQL("agebbdd", "localhost", "", "192.168.64.2", 3303);

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

        LoginView loginView = new LoginView();
        SignupView signupView = new SignupView();
        StartView startView = new StartView();
        LogoutView logoutView = new LogoutView();
        BoardView boardView = new BoardView();

        MainView mainView = new MainView(loginView,logoutView,signupView, startView, boardView);
        MainController mainController = new MainController(mainView);

        UserOption userOption = new UserOption();
        UserModel loginModel = new UserModel(userdao, userOption);
        PlayerModel boardModel = new PlayerModel();

        LoginController loginController = new LoginController(loginModel, loginView, mainController);
        SignUpController signUpController = new SignUpController(loginModel, signupView, mainController);
        LogoutController logoutController = new LogoutController(loginModel, logoutView, mainController);
        BoardController boardController = new BoardController(boardModel,boardView, mainController);


        mainView.setVisible(true);


    }
}
