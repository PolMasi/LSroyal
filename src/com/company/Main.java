package com.company;

import com.company.Business.LoginModel;
import com.company.Persistence.UserDAO;
import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.LoginView;

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
        };
	    LoginModel loginModel = new LoginModel(userDAO);
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginModel, loginView);
        loginView.setVisible(true);     //mostrar
        MainView mainView = new MainView(loginView);
        MainController mainController = new MainController(loginController, mainView);

    }
}
