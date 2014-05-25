package io.docencrypting.Config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 *  Singleton Class that contains common application settings
 */

public class AppConfig {

    //default path's for cipher alphabet
    public static final String DEFAULT_MORSE_CFG_PATH = "Ciphers/morse_cipher.cfg";
    public static final String DEFAULT_HILL_CFG_PATH = "Ciphers/hill_cipher.cfg";
    public static final String DEFAULT_PLAYFAIR_CFG_PATH = "Ciphers/playfair_cipher.cfg";

    private AtomicReference<String> name;           /// Name of application
    private AtomicBoolean isConsole;                /// Console mode

    private AppConfig() {
        name = new AtomicReference<>();
        isConsole = new AtomicBoolean(false);
    }

    /**
     * Static method that return instance of current class
     * @return instance of current class
     */
    public static AppConfig getInstance() {
        return ConfigHolder.APP_CONFIG;
    }

    /**
     * Get console mode
     * @return console mode
     */
    public boolean isConsole() {
        return isConsole.get();
    }

    /**
     * Set console mode value
     * @param console value that represent active console mode
     */
    public void setConsole(boolean console) {
        isConsole.set(console);
    }

    /**
     * Get name of application
     * @return name of app
     */
    public String getName() {
        return name.get();
    }

    /**
     * Set name of application
     * @param name new name of application
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Static class for lazy initialisation
     */
    private static class ConfigHolder {
        public static final AppConfig APP_CONFIG = new AppConfig();
    }

}
