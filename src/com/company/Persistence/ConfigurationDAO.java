package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;

import java.util.ArrayList;

/**
 * Configuracion DAO
 */
public interface ConfigurationDAO {
    void loadConfigFile();
    ArrayList<Offensive> loadOffensiveTroops();
    ArrayList<Defensive> loadDefensiveTroops();
}
