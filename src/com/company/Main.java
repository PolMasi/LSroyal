package com.company;

import com.company.Business.UserModel;
import com.company.Business.UserOption;
import com.company.Persistence.UserDAO;
import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.Controllers.LogoutController;
import com.company.Presentation.Controllers.SignUpController;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.LoginView;
import com.company.Presentation.Views.LogoutView;
import com.company.Presentation.Views.SignupView;
import com.company.Presentation.Views.StartView;

public class Main {

    public static void main(String[] args) {

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

        MainView mainView = new MainView(loginView,logoutView,signupView, startView);
        MainController mainController = new MainController(mainView);

        UserOption userOption = new UserOption();
        UserModel loginModel = new UserModel(userDAO, userOption);

        LoginController loginController = new LoginController(loginModel, loginView, mainController);
        SignUpController signUpController = new SignUpController(loginModel, signupView, mainController);
        LogoutController logoutController = new LogoutController(loginModel, logoutView, mainController);

        mainView.setVisible(true);


    }
}
