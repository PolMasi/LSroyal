package com.company.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.company.Business.ConfigDB;

/* Clase ConexionDB se encarga de crear la conexion con la BBDD*/

public class ConexionDB {

    private final String dbName;
    private final String dbUser;
    private final String password;
    private final String dbUrl;
    private final int port;
    private Connection con;

/* (Constructor) Configuracion de la conexion con la BBDD*/

    public ConexionDB(ConfigDB config) {
        this.port = config.getPort();
        this.dbName = config.getDatabaseName();
        this.dbUser = config.getDatabaseUser();
        this.dbUrl = config.getDatabaseUrl() + this.port + "/" + this.dbName;
        this.password = config.getPassword();
        this.con = null;

    }

    /*Realizamos la connexio con la BBDD
     retorna la conexion que se ha hecho con la BBDD*/

    public Connection getConexion()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbUser, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}




