package com.company.Persistence;

import com.company.Business.Entities.Troop;

import java.util.ArrayList;

public interface GameDAO {

    ArrayList<String[]> getMatchList(String userName);
    void matrixToJson(Troop[][] matrix);
    void saveMatrix();

}
