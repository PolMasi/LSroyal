package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Persistence.ConfigurationDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ComputerModel implements Runnable {
    private int counter;
    private ArrayList <Offensive> offensiveTroops;
    private ArrayList <Defensive> defensiveTroops;
    private ConfigurationDAO configurationDAO;
    private int money;
    private Random selectTroop;

    public ComputerModel(ConfigurationDAO configurationDAO){
        this.counter = 0;
        this.configurationDAO = configurationDAO;
        this.money = 5;
        selectTroop = new Random();
        loadTroops();
    }
    @Override
    public void run() {
        defenseTroop();

        if(counter == 4){
            attackTroop();
        }
    }
    public void defenseTroop(){
        //System.out.println("defensa");
        int troop = selectTroop.nextInt(defensiveTroops.size());
        boolean invoked = false;
        Defensive defTroop = defensiveTroops.get(troop);
        //System.out.println("1." + defTroop.getCost());
        if(defTroop.getCost() > money){
            Collections.shuffle(defensiveTroops);
            for (Defensive d: defensiveTroops
                 ) {
                if(d.getCost() <= money && !invoked){
                    invoked = true;
                    //invoke (d)
                    //System.out.println("2. new "+ d.getCost());
                }
            }
        }
        else{
            //invoke defTroop
        }
        counter++;

    }
    public void attackTroop(){
        //System.out.println("ataque");
        int troop = selectTroop.nextInt(offensiveTroops.size());
        boolean invoked = false;
        Offensive offTroop = offensiveTroops.get(troop);
        //System.out.println("1. " + offTroop.getCost());

        if(offTroop.getCost() > money) {
            Collections.shuffle(offensiveTroops);
            for (Offensive d : offensiveTroops) {
                if (d.getCost() <= money && !invoked) {
                    invoked = true;
                    //invoke (d)
                    //System.out.println("2. new "+ d.getCost());
                }
            }

        }
        else {
            //invoke offTroop
        }
        counter = 0;
    }

    public void loadTroops(){
        offensiveTroops = configurationDAO.loadOffensiveTroops();
        defensiveTroops = configurationDAO.loadDefensiveTroops();

    }

    public void addMoney(int money) {
        this.money += money;
    }

}
