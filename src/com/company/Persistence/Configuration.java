package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class Configuration implements ConfigurationDAO{
    private final Gson gson;
    private Reader offensive;
    private Reader defensive;

    public Configuration(String offensive, String defensive) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            this.defensive = new FileReader(defensive);
            this.offensive = new FileReader(offensive);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ConfigFile loadConfigFile() {
        return null;
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
