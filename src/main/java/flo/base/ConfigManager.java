package flo.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import flo.utils.Constants;

public class ConfigManager {
    private static Properties properties = new Properties();
    
    static {
        String env = System.getProperty("env", "stage");
        String config_path = Constants.CONFIG_DIR + String.format("/config-%s.properties", env);

        try {
            FileInputStream inputStream = new FileInputStream(config_path);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
