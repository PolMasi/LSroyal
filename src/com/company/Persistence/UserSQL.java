package com.company.Persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * gestion de la base de dados
 */
public class UserSQL implements UserDAO {
    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbIP;
    private final String dbUrl;
    private final int port;
    private Connection con;
    private String userIDpath;

    /**
     * Constructor de la clase sql
     * @param dbName DB nombre
     * @param dbUser Usuario que ha introducido
     * @param password contrasenya usuario
     * @param dbIP IP
     * @param port el puerto para conectarse
     * @param userID nombre usuario
     */


    public UserSQL(String dbName, String dbUser, String password, String dbIP, int port, String userID) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.password = password;
        this.dbIP = dbIP;
        this.dbUrl = "jdbc:mysql://" + dbIP + ":" + port + "/" + dbName;
        this.port = port;
        this.userIDpath = userID;
        getConexion();
    }

    /**
     * Obtener conexion con BBDD
     */

    public void getConexion() {
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iniciar sesion con usuario ya registrado
     * @param name nombre del usuario
     * @param password contraseña
     * @return login correcto
     */

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

    /**
     * Registrar usuario
     * @param user nombre usuario
     * @param pass contraseña
     * @param mail mail
     * @return si es correcto el signUP
     */


    public boolean validSignUp(String user, String pass, String mail) {
        PreparedStatement ps;
        String sql = "INSERT INTO usuarios (Usuario, Contraseña, Mail, Victorias, Jugadas) VALUES (?, ?, ?, 0, 0)";

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

    /**
     * Añadir victorias
     * @param userID ID usuario
     * @return añade victoria
     */

    public boolean addVictory(int userID) {

        PreparedStatement ps;
        //comando sql
        String sql = "UPDATE usuarios SET Victorias = Victorias + 1 WHERE ID = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    /**
     * Añadir partidas
     * @param userID ID usuario
     * @return afegeix partida
     */

    public boolean addGame(int userID) {

        PreparedStatement ps;
        //comando sql
        String sql = "UPDATE usuarios SET Jugadas = Jugadas + 1 WHERE ID = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    /**
     * Comprobar un usuario registrado
     * @param name nombre usuario
     * @return si es correcto el usuario
     */

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

    /**
     * Borrar un usuario ya registrado
     * @param user borrado del usuario
     * @return indica si se ha borrado
     */

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

    /**
     * Obtener el nombre del usuario que inicia sesion
     * @param mail mail del usuario
     * @return nombre usuario
     */
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

    /**
     *
     * Identificador del usuario
     * @return ID usuario
     */

    @Override
    public int userID() {

        try {
            FileReader reader = new FileReader(userIDpath);

            return Character.getNumericValue(reader.read());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     * Guardar identificador del usuario
     * @param ID identificador usuario
     */

    private void saveUserID(int ID){

        try {
            PrintWriter writer = new PrintWriter(userIDpath);
            writer.print(ID);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

/**
 * Hacer logout del usuario
 *
 */
    }
    public void logOutUserID(){

        try {
            PrintWriter writer = new PrintWriter(userIDpath);
            writer.print(0);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Obtener el ranking de los usuarios
     * @return
     */

    public ArrayList<String[]> getRanking() {

        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String[]> result = new ArrayList<>();

        String sql = "SELECT Usuario, Victorias, Victorias/Jugadas AS Ratio FROM usuarios ORDER BY Ratio DESC;";

        try {
            ps = con.prepareStatement(sql);


            rs = ps.executeQuery();



            while (rs.next()) {
                String[] results = new String[3];

                results[0] = rs.getString("Usuario");
                results[1] = String.valueOf(rs.getInt("Victorias"));
                results[2] = String.valueOf(rs.getInt("Ratio"));

                result.add(results);
            }


        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

        return result;


    }


    }








