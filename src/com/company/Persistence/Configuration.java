package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Configuration implements ConfigurationDAO {

    private final Gson gson;
    private Reader offensive;
    private Reader defensive;
    private Reader config;

    private String IP;
    private String port;
    private String name;
    private String user;
    private String password;

    public Configuration(String offensive, String defensive, String config) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            this.defensive = new FileReader(defensive);
            this.offensive = new FileReader(offensive);
            this.config = new FileReader(config);

        } catch (IOException e) {
            e.printStackTrace();
        }

        loadConfigFile();
    }

    public String getIP() {
        return IP;
    }

    public String getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void loadConfigFile() {

        JsonParser jsonParser = new JsonParser();
        JsonElement object;
        object = jsonParser.parse(config);
        try {
            config.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject conf = object.getAsJsonObject();
        JsonObject dataBase = conf.getAsJsonObject("dataBase");
        this.IP = ((dataBase.get("IP").getAsString()));
        this.port = ((dataBase.get("port").getAsString()));
        this.name = ((dataBase.get("dbName").getAsString()));
        this.user = ((dataBase.get("dbUser").getAsString()));
        this.password = ((dataBase.get("password").getAsString()));




    }

    @Override
    public ArrayList<Offensive> loadOffensiveTroops() {
        ArrayList<Offensive> list = new ArrayList<>();

        try {
            list = gson.fromJson(offensive, list.getClass());
            offensive.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ArrayList<Defensive> loadDefensiveTroops() {
        ArrayList<Defensive> list = new ArrayList<>();

        try {
            list = gson.fromJson(defensive, list.getClass());
            defensive.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
