package com.sasa.backend.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    
    private DotenvConfig() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static void load() {
        Dotenv.configure()
            .load();
    }
}
