package io.docencrypting.Controllers;

import io.docencrypting.Config.AppConfig;
import io.docencrypting.Parser.ArgumentParser;
import io.docencrypting.UI.Graphical.MainFrame;
import io.docencrypting.UI.UserInterface;

public class ApplicationController {

    public ApplicationController(String[] args) {
        ArgumentParser.parse(args);     /// parsing configurations from command line arguments
    }

    public void run() {
        AppConfig appConfig = AppConfig.getInstance();
        UserInterface userInterface;
        if (appConfig.isConsole()) {
            System.out.println("Cnsl");
        } else {
            System.out.println("Frame");
            new MainFrame();

        }
    }
}
