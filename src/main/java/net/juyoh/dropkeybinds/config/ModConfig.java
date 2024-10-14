package net.juyoh.dropkeybinds.config;

import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class ModConfig {
    private static ModConfig instance;

    public static int amount_1 = 64;
    public static int amount_2 = 48;
    public static int amount_3 = 32;
    public static int amount_4 = 16;
    public static int amount_5 = 8;

    public static ModConfig getInstance() {
        if (instance == null) {
            instance = new ModConfig();
            instance.loadConfig();
        }
        return instance;
    }

    public static void loadConfig() {
        try {
            File file = new File("config/dropkeybinds_config.properties");
            Properties properties = new Properties();
            if (file.exists()) {
                FileInputStream input = new FileInputStream(file);
                properties.load(input);

                amount_1 = Integer.parseInt(properties.getProperty("amount1", "64"));
                amount_2 = Integer.parseInt(properties.getProperty("amount2", "48"));
                amount_3 = Integer.parseInt(properties.getProperty("amount3", "32"));
                amount_4 = Integer.parseInt(properties.getProperty("amount4", "16"));
                amount_5 = Integer.parseInt(properties.getProperty("amount5", "8"));

                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            File file = new File("config/dropkeybinds_config.properties");
            Properties properties = new Properties();

            properties.setProperty("amount1", Integer.toString(getInstance().amount_1));
            properties.setProperty("amount2", Integer.toString(getInstance().amount_2));
            properties.setProperty("amount3", Integer.toString(getInstance().amount_3));
            properties.setProperty("amount4", Integer.toString(getInstance().amount_4));
            properties.setProperty("amount5", Integer.toString(getInstance().amount_5));

            FileOutputStream output = new FileOutputStream(file);
            properties.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
