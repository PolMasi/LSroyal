package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.io.*;
import java.lang.reflect.Array;
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
        Offensive[] list1 = new Offensive[3];

        list1 =  gson.fromJson(offensive, list1.getClass());

        for (int i = 0; i < list1.length; i++) {
            list.add(list1[i]);
        }

        return list;
    }

    @Override
    public ArrayList<Defensive> loadDefensiveTroops() {
        ArrayList<Defensive> list = new ArrayList<>();
        Defensive[] list1 = new Defensive[3];

        list1 = gson.fromJson(defensive, list1.getClass());

        for (int i = 0; i < list1.length; i++) {
            list.add(list1[i]);
        }

        return list;
    }
}
