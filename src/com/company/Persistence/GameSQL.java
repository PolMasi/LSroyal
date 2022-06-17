package com.company.Persistence;

import com.company.Business.Entities.Offensive;
import com.company.Business.Entities.Troop;
import com.company.Business.UserModel;
import com.company.Presentation.Views.BoardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSQL implements GameDAO {

    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbIP;
    private final String dbUrl;
    private final int port;
    private Connection con;
    private ArrayList<String> moveList;

    /**
     * Constructor
     * @param dbName db name
     * @param dbUser db user
     * @param password contraseña
     * @param dbIP dbIP
     * @param port puerto
     */

    public GameSQL(String dbName, String dbUser, String password, String dbIP, int port) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.password = password;
        this.dbIP = dbIP;
        this.dbUrl = "jdbc:mysql://" + dbIP + ":" + port + "/" + dbName;
        this.port = port;
        this.moveList = new ArrayList<>();
        getConexion();
    }

    /**
     * Conexion
     */
    public void getConexion() {
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Lista de partidas
     * @param userName nombre usuario
     * @return array
     */
    public ArrayList<String[]> getMatchList(String userName) {

        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String[]> result = new ArrayList();

        String sql = "SELECT partidas.* from partidas join usuarios where partidas.usuario_ID = usuarios.ID and usuarios.Usuario like ?";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, userName);

            rs = ps.executeQuery();


            while (rs.next()) {

                String[] res = new String[4];
                res[0] = String.valueOf(rs.getInt("ID"));
                res[1] = (rs.getString("nombre"));
                res[2] = String.valueOf((rs.getDate("fecha")));
                res[3] = String.valueOf((rs.getInt("resultado")));

                result.add(res);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    /**
     * Matriz al Json
     * @param matrix tabla
     */
    public void matrixToJson(String[][][] matrix) {


        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Id.class, new IdTypeAdapter())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        //System.out.println(matrix[1][3]);
        moveList.add(gson.toJson(matrix));


    }

    /**
     * Matriz desde el Json
     * @param json json
     * @return matriz triple
     */

    public String [][][] matrixFromJson(String json){

        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Id.class, new IdTypeAdapter())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        String [][][] matrix = new String [BoardView.ROWS][BoardView.COLUMNS][4];

             return  gson.fromJson(json,matrix.getClass());
    }

    /**
     *
     * Guardar movimiento
     * @param gameID id partida
     * @param movement movimiento
     */


    public void saveMovement(int gameID, String movement) {


        PreparedStatement ps;
        String sql = "INSERT INTO movimientos (partida_ID, estado) VALUES (?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, gameID);
            ps.setString(2, movement);
            ps.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    /**
     *
     * Guardar partida
     * @param userID usuario id
     * @param gameName nombre partida
     * @param result resultado
     * @return devuelve si se ha guardado
     */
    @Override
    public boolean saveGame(int userID, String gameName, int result) {

        if (!checkGameName(gameName)) {
            return false;
        }

        int gameID = createGame(userID, gameName, result);

        for (String movement : moveList) {
            saveMovement(gameID, movement);

        }
        return true;
    }

    /**
     *
     * Crear partida
     * @param userID id usuario
     * @param gameName nombre partida
     * @param result resultado
     * @return si se ha creado
     */

    public int createGame(int userID, String gameName, int result) {

        PreparedStatement ps;
        ResultSet rs;
        int ID = 0;
        String sql = "INSERT INTO partidas (nombre, resultado, usuario_ID) SELECT ?,?,?";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, "'" + gameName + "'");
            ps.setInt(2, result);
            ps.setInt(3, userID);
            ps.execute();

            sql = "SELECT ID FROM partidas WHERE usuario_ID = ? AND nombre LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, "'" + gameName + "'");
            rs = ps.executeQuery();

            while (rs.next()) {

                ID = rs.getInt("ID");

            }

            return ID;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

        return ID;
    }

    private boolean checkGameName(String name) {

        PreparedStatement ps;
        ResultSet rs;
        int result = 0;

        String sql = "SELECT COUNT(*) as count FROM partidas WHERE nombre like ?";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, name);
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
     * Guardar partidas
     * @param userID usuario id
     * @return array de Strings
     */
    public ArrayList<String[]> getSavedGames(int userID) {

        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String[]> result = new ArrayList<>();

        String sql = "SELECT ID, nombre, fecha, resultado FROM partidas WHERE usuario_ID = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();


            while (rs.next()) {
                String[] results = new String[4];

                results[0] = String.valueOf(rs.getInt("ID"));
                results[1] = rs.getString("nombre");
                results[2] = rs.getString("fecha");
                results[3] = String.valueOf(rs.getInt("resultado"));

                result.add(results);
            }


        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

        return result;


    }

    /**
     *
     * Reproducir partida
     * @param gameID id de la partida
     * @return array de String
     */

    public ArrayList<String[][][]> getReplayGame(int gameID) {

        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String[][][]> result = new ArrayList<>();

        String sql = "SELECT estado FROM movimientos WHERE partida_ID = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, gameID);
            rs = ps.executeQuery();


            while (rs.next()) {

                result.add(matrixFromJson(rs.getString("estado")));
            }


        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

        return result;


    }


}



