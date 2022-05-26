package com.company.Persistence;

import com.company.Business.Entities.Offensive;
import com.company.Business.Entities.Troop;
import com.company.Business.UserModel;
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

    public void getConexion() {
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getMatchList (String userName){

        PreparedStatement ps;
        ResultSet rs;
        ArrayList <String[]> result =new ArrayList();

        String sql = "SELECT partidas.* from partidas join usuarios where partidas.usuario_ID = usuarios.ID and usuarios.Usuario like ?";

        try {
            ps = con.prepareStatement(sql);
            ;
            ps.setString(1, userName);

            rs = ps.executeQuery();



            while (rs.next()) {

               String [] res = new String[4];
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



    public void matrixToJson(String[][][] matrix){


        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Id.class, new IdTypeAdapter())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        //System.out.println(matrix[1][3]);
        moveList.add(gson.toJson(matrix));


    }


    public void saveMovement(int gameID, String movement){



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

    @Override
    public boolean saveGame(int userID, String gameName, int result) {


        if(!checkGameName(gameName)){

            return false;

        }

        int gameID = createGame(userID, gameName,result);


        for (String movement: moveList) {

            saveMovement(gameID, movement);

        }
              return true;
    }

    public int createGame(int userID, String gameName, int result){

        PreparedStatement ps;
        ResultSet rs;
        int ID = 0;
        String sql = "INSERT INTO partidas (nombre, resultado, usuario_ID) SELECT ?,?,?";

        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, "'" + gameName + "'" );
            ps.setInt(2, result);
            ps.setInt(3,userID);
            ps.execute();

            sql = "SELECT ID FROM partidas WHERE usuario_ID = ? AND nombre LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1,userID);
            ps.setString(2,"'" + gameName + "'");
            rs = ps.executeQuery();

            while(rs.next()){

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





}
