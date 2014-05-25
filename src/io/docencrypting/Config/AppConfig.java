package io.docencrypting.Config;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 *  Singleton Class that contains common application settings
 */

public class AppConfig {

    //default path's for cipher alphabet
    private static String DEFAULT_MORSE_CFG_PATH = "Ciphers/morse_cipher.cfg";
    private static String DEFAULT_HILL_CFG_PATH = "Ciphers/hill_cipher.cfg";
    private static String DEFAULT_PLAYFAIR_CFG_PATH = "Ciphers/playfair_cipher.cfg";

    private AtomicReference<String> name;           /// Name of application
    private AtomicBoolean isConsole;                /// Console mode
    private AtomicReference<String> morseCipherPath;/// Path of Morse cipher
    private AtomicReference<String> hillCipherPath; /// Path of Hill cipher
    private AtomicReference<String> playfairCipherPath;

    private String fileIn;
    private String fileOut;
    private String password;
    private String typeEncrypt;

    public String getFileIn() {
        return fileIn;
    }

    public void setFileIn(String fileIn) {
        this.fileIn = fileIn;
    }

    public String getFileOut() {
        return fileOut;
    }

    public void setFileOut(String fileOut) {
        this.fileOut = fileOut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeEncrypt() {
        return typeEncrypt;
    }

    public void setTypeEncrypt(String typeEncrypt) {
        this.typeEncrypt = typeEncrypt;
    }

    private AppConfig() {
        name = new AtomicReference<>();
        isConsole = new AtomicBoolean(false);
        morseCipherPath = new AtomicReference<>(DEFAULT_MORSE_CFG_PATH);
        hillCipherPath = new AtomicReference<>(DEFAULT_HILL_CFG_PATH);
        playfairCipherPath = new AtomicReference<>(DEFAULT_PLAYFAIR_CFG_PATH);
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
     * Get the Hill cipher config path
     * @return config path
     */
    public String getHillCipherPath() {
        return hillCipherPath.get();
    }

    /**
     * Set the Hill cipher config path
     * @param hillCipherPath new Hill cipher path
     */
    public void setHillCipherPath(String hillCipherPath) {
        this.hillCipherPath.set(hillCipherPath);
    }

    /**
     * Get the Playfair cipher config path
     * @return config path
     */
    public String getPlayfairCipherPath() {
        return playfairCipherPath.get();
    }

    /**
     * Set the Hill cipher config path
     * @param hillCipherPath new Hill cipher path
     */
    public void setPlayfairCipherPath(String hillCipherPath) {
        this.playfairCipherPath.set(hillCipherPath);
    }

    /**
     * Get the Morse cipher path
     * @return the Morse cipher config path
     */
    public String getMorseCipherPath() {
        return morseCipherPath.get();
    }

    /**
     * Set the Morse cipher config path
     * @param morseCipherPath the Morse cipher path
     */
    public void setMorseCipherPath(String morseCipherPath) {
        this.morseCipherPath.set(morseCipherPath);
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
