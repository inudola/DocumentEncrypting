package io.docencrypting.Config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */

public class AppConfig {

    private static String DEFAULT_MORSE_CFG_PATH = "Ciphers/morse_cipher.cfg";
    private static String DEFAULT_HILL_CFG_PATH = "Ciphers/hill_cipher.cfg";
    private AtomicReference<String> name;
    private AtomicBoolean isConsole;
    private AtomicReference<String> morseCipherPath;
    private AtomicReference<String> hillCipherPath;

    private AppConfig() {
        name = new AtomicReference<>();
        isConsole = new AtomicBoolean(false);
        morseCipherPath = new AtomicReference<>(DEFAULT_MORSE_CFG_PATH);
        hillCipherPath = new AtomicReference<>(DEFAULT_HILL_CFG_PATH);
    }

    public static AppConfig getInstance() {
        return ConfigHolder.APP_CONFIG;
    }

    public boolean isConsole() {
        return isConsole.get();
    }

    public void setConsole(boolean console) {
        isConsole.set(console);
    }

    public String getHillCipherPath() {
        return hillCipherPath.get();
    }

    public void setHillCipherPath(String hillCipherPath) {
        this.hillCipherPath.set(hillCipherPath);
    }

    public String getMorseCipherPath() {
        return morseCipherPath.get();
    }

    public void setMorseCipherPath(String morseCipherPath) {
        this.morseCipherPath.set(morseCipherPath);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private static class ConfigHolder {
        public static final AppConfig APP_CONFIG = new AppConfig();
    }

}
