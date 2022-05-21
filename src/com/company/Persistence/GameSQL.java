package com.company.Persistence;

import com.company.Business.Entities.Troop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.ArrayList;

public class GameSQL {

    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbIP;
    private final String dbUrl;
    private final int port;
    private Connection con;

    public GameSQL(String dbName, String dbUser, String password, String dbIP, int port) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.password = password;
        this.dbIP = dbIP;
        this.dbUrl = "jdbc:mysql://" + dbIP + ":" + port + "/" + dbName;
        this.port = port;
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

    public String matrixToJson(Troop[][] matrix){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(matrix);




    }



}
