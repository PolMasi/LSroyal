package com.company.Persistence;

public interface UserDAO {
    boolean validLogin(String name, String password);
    boolean validSignUp(String user, String pass, String mail);
    boolean checkUserName(String name);
    boolean delete(String user);
    String getUserName(String mail);
    int userID();

}
