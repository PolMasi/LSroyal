package com.company.Persistence;

import java.sql.*;

public class UserSQL implements UserDAO {
    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbUrl;
    private final int port;
    private Connection con;

    public UserSQL(String dbName, String dbUser, String password, String dbUrl, int port) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.password = password;
        this.dbUrl = dbUrl;
        this.port = port;
        getConexion();
    }

    public void getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUser, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validLogin(String name, String password) {

       /* PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT  Usuario, Contraseña, Mail FROM usuarios WHERE usuario = ? OR Mail = ?";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, name);
            ps.setString(2, password);
            ps.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;


        }

*/     return false;
    }



    public boolean validSignUp(String user, String pass, String mail) {
        PreparedStatement ps;
        String sql = "INSERT INTO usuarios (Usuario, Contraseña, Mail) VALUES (?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, mail);
            ps.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
