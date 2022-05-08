package com.company.Persistence;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;

public interface ConfigurationDAO {
    ConfigFile loadConfigFile();
    Offensive[] loadOffensiveTroops();
    Defensive[] loadDefensiveTroops();
}
