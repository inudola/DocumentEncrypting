package io.docencrypting.Config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AppConfig {

    private AtomicReference<String> name;
    private AtomicBoolean isConsole;

    public boolean isConsole() {
        return isConsole.get();
    }

    public void setConsole(boolean console) {
        isConsole.set(console);
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
    }

    public static AppConfig getInstance() {
        return ConfigHolder.APP_CONFIG;
    }

    private static class ConfigHolder {
        public static final AppConfig APP_CONFIG = new AppConfig();
    }

}
