package ru.harimasa.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.harimasa.ServerSwitcher;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ButtonPositionConfig {
    private static int buttonX = -1;
    private static int buttonY = -1;
    private static boolean showConfigureButton = true;
    private static final File CONFIG_FILE = new File("config/server_switcher_button.json");

    // Method for load config
    public static void load() {
        try {
            if (CONFIG_FILE.exists()) {
                JsonObject json = JsonParser.parseReader(new FileReader(CONFIG_FILE)).getAsJsonObject();
                buttonX = json.get("x").getAsInt();
                buttonY = json.get("y").getAsInt();
                showConfigureButton = json.get("showButton").getAsBoolean();
            }
        } catch (Exception e) {
            ServerSwitcher.LOGGER.error("Failed to load config", e);
        }
    }

    // Method for save config
    public static void save(int x, int y, boolean showButton) {
        JsonObject json = new JsonObject();
        json.addProperty("x", x);
        json.addProperty("y", y);
        json.addProperty("showButton", showButton);

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(json.toString());
        } catch (Exception e) {
            ServerSwitcher.LOGGER.error("Failed to save config", e);
        }
    }

    // Getter for visibility
    public static boolean shouldShowConfigureButton() {
        return showConfigureButton;
    }

    // Getter for X
    public static int getX(int defaultX) {
        return buttonX == -1 ? defaultX : buttonX;
    }

    // Getter for Y
    public static int getY(int defaultY) {
        return buttonY == -1 ? defaultY : buttonY;
    }
}