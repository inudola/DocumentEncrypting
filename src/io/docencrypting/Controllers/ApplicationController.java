package io.docencrypting.Controllers;

import io.docencrypting.Config.AppConfig;
import io.docencrypting.Parser.ArgumentParser;
import io.docencrypting.UI.Console.Console;
import io.docencrypting.UI.Graphical.MainFrame;

/**
 * Application controller
 */
public class ApplicationController {

    String[] args;

    public ApplicationController(String[] args) {
        this.args = args;
    }

    /**
     * Start app
     */
    public void run() {
        ArgumentParser.parse(args);
        AppConfig appConfig = AppConfig.getInstance();
        if (appConfig.isConsole()) {
            new Console().run();
        } else {
            new MainFrame().setVisible(true);

        }
    }
}
