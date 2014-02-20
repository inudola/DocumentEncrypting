package io.docencrypting.Config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AppConfig {

    private AtomicReference<String> name;
    private AtomicBoolean isConsole;
    private AtomicReference<String> morseCipherPath;

    private static String DEFAULT_MORSE_CFG_PATH = "Ciphers/morse_cipher.cfg";

    public boolean isConsole() {
        return isConsole.get();
    }

    public void setConsole(boolean console) {
        isConsole.set(console);
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

    private AppConfig() {
        name = new AtomicReference<>();
        isConsole = new AtomicBoolean(false);
        morseCipherPath = new AtomicReference<>(DEFAULT_MORSE_CFG_PATH);
    }

    public static AppConfig getInstance() {
        return ConfigHolder.APP_CONFIG;
    }

    private static class ConfigHolder {
        public static final AppConfig APP_CONFIG = new AppConfig();
    }

}
