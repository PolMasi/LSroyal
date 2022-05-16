package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;

import java.util.ArrayList;

public class PlayerModel implements Runnable {
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;

    public PlayerModel(ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive) {
        this.listOffensive = listOffensive;
        this.listDefensive = listDefensive;
    }

    @Override
    public void run() {


    }
}
