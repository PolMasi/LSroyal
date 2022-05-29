package com.company.Persistence;

import com.company.Business.Entities.Troop;

import java.util.ArrayList;

/**
 * GameDAO
 */

public interface GameDAO {

    ArrayList<String[]> getMatchList(String userName);
    void matrixToJson(String [][][] matrix);
    void saveMovement(int gameID, String movement);
    boolean saveGame(int userID, String name, int result);
    ArrayList <String []> getSavedGames(int userID);
    ArrayList<String[][][]> getReplayGame(int gameID);

}
