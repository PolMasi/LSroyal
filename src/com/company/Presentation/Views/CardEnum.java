package com.company.Presentation.Views;

public enum CardEnum {
     LOGIN_VIEW("LOGIN_VIEW"),
     LOGOUT_VIEW("LOGOUT_VIEW"),
     SIGNUP_VIEW("SIGNUP_VIEW"),
     START_VIEW("START_VIEW"),
     MENU_VIEW("MENU_VIEW"),
     START_LOGIN_BTN("START_LOGIN_BTN"),
     START_SIGNUP_BTN("START_SIGNUP_BTN"),
     SIGNUP_BTN("SIGNUP_BTN"),
     SIGNUP_BACK_BTN("SIGNUP_BACK_BTN"),
     BUTTON_LOGIN("BUTTON_LOGIN"),
     LOGIN_BACK_BTN("LOGIN_BACK_BTN"),
     PLAYGAME_BTN("PLAYGAME_BTN"),
     REPRODUCE_BTN("REPRODUCE_BTN"),
     RANKING_BTN("RANKING_BTN"),
     EXIT_BTN("EXIT_BTN");

     private final String text;
     CardEnum(final String text) {
          this.text = text;
     }

     public String toString() {
          return text;
     }
}
