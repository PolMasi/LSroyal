package com.company.Persistence;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ConfigFile {

    public static final String CONFIG_JSON = "config.json";
    private final Gson gson;

    public ConfigFile() {
        gson = new Gson();
    }

    public Optional<ConfigFile> getConfig() {
        try {
            return Optional.of(gson.fromJson(Files.newBufferedReader(Paths.get(CONFIG_JSON)),ConfigFile.class));
        } catch (IOException exception) {
            return Optional.empty();
        }
    }
}
