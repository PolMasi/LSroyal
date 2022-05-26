package com.company.Persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class UserSQL implements UserDAO {
    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbIP;
    private final String dbUrl;
    private final int port;
    private Connection con;
    private String userID;

    public UserSQL(String dbName, String dbUser, String password, String dbIP, int port, String userID) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.password = password;
        this.dbIP = dbIP;
        this.dbUrl = "jdbc:mysql://" + dbIP + ":" + port + "/" + dbName;
        this.port = port;
        this.userID = userID;
        getConexion();
    }

    public void getConexion() {
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validLogin(String name, String password) {

        PreparedStatement ps;
        ResultSet rs;
        int result = 0;

        String sql = "SELECT COUNT(*) as count FROM usuarios WHERE (Usuario like ? OR Mail like ?) AND Contraseña like binary ?";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, name);
            ps.setString(2, name);
            ps.setString(3, password);
            rs = ps.executeQuery();


            while (rs.next()) {

                result = rs.getInt("count");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }

        return result > 0;
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


    public boolean checkUserName(String name) {

        PreparedStatement ps;
        ResultSet rs;
        int result = 0;

        String sql = "SELECT COUNT(*) as count FROM usuarios WHERE (Usuario like ? or Mail like ?)";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, name);
            ps.setString(2, name);
            rs = ps.executeQuery();


            while (rs.next()) {

                result = rs.getInt("count");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }
        System.out.println(result);
        return result <= 0;
    }

    public boolean delete(String user){

        PreparedStatement ps;
        String sql = "DELETE FROM usuarios where Usuario like ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getUserName(String mail) {

        PreparedStatement ps;
        ResultSet rs;
        String result = null;

        String sql = "SELECT Usuario as user, ID FROM usuarios WHERE (Usuario like ? or Mail like ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, mail);
            ps.setString(2, mail);
            rs = ps.executeQuery();


            while (rs.next()) {

                result = rs.getString("user");
                saveUserID(rs.getInt("ID"));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

        return result;


    }

    @Override
    public int userID() {

        try {
            FileReader reader = new FileReader(userID);

            return Character.getNumericValue(reader.read());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void saveUserID(int ID){

        try {
            PrintWriter writer = new PrintWriter(userID);
            writer.print(ID);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    }








