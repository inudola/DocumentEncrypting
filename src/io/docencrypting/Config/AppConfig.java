package io.docencrypting.Config;

public class AppConfig {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return ConfigHolder.APP_CONFIG;
    }

    private static class ConfigHolder {
        public static final AppConfig APP_CONFIG = new AppConfig();
    }

}
