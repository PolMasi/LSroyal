package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Business.UserOption;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.SignupView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController implements ActionListener {
    private SignupView signupView;
    private UserModel userModel;
    private MainController mainController;      //TODO preguntar Edu

    //TODO funcion mostrar error usuario

    public SignUpController(UserModel userModel, SignupView signupView, MainController mainController) {
        this.signupView = signupView;
        this.userModel = userModel;
        this.mainController = mainController;
        registerSignUpListener();
    }

    public void registerSignUpListener() {
        this.signupView.registerSignUpController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = signupView.getUser();
        String pass = signupView.getPassword();
        String passConfirm = signupView.getPasswordConfirm();
        String email = signupView.getEmail();

        switch (userModel.signUp(user, pass, passConfirm, email)) {

            case UserOption.EVERYTHING_OK -> mainController.switchView(MainView.LOGIN_VIEW);
            case UserOption.EMPTY_FIELD -> mainController.showError("There is an empty field!");
            case UserOption.ERROR_SAVE -> mainController.showError("There has been an error on creating the account!");
            case UserOption.INCORRECT_MAIL -> mainController.showError("That is not a valid mail!");
            case UserOption.INCORRECT_PASS -> mainController.showError("That is not a valid password!");
            case UserOption.MISMATCHING_PASS -> mainController.showError("The passwords do not match! Try again!");
        }
    }
}
