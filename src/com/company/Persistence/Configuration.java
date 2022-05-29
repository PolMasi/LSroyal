package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Configuacion de la partida jugada
 */
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

    /**
     * Contructor de la configuació
     * @param offensive informaico de la ofensiva
     * @param defensive informaico de la defensa
     * @param config informacio de la configuració
     */
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

    /**
     * getter de la ip
     * @return la ip
     */
    public String getIP() {
        return IP;
    }

    /**
     * getter de port
     * @return el port
     */
    public String getPort() {
        return port;
    }

    /**
     * getter del nom
     * @return el nom
     */
    public String getName() {
        return name;
    }

    /**
     * getter del user
     * @return el user
     */
    public String getUser() {
        return user;
    }

    /**
     * getter de la contrasenya
     * @return la contrasenya
     */
    public String getPassword() {
        return password;
    }

    /**
     * descarregar la inforació de la base de dades
     */
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

    /**
     * carregar les tropes offensives
     * @return la lista de les tropes ofenisves
     */
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

    /**
     * carregar les torpes defesnives
     * @return la lista de les tropes defensives
     */
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
