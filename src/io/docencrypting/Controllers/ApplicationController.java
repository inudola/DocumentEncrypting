package io.docencrypting.Controllers;

import io.docencrypting.Config.AppConfig;
import io.docencrypting.Parser.ArgumentParser;
import io.docencrypting.UI.Console.Console;
import io.docencrypting.UI.Graphical.MainFrame;
import io.docencrypting.UI.UserInterface;

import javax.swing.*;

public class ApplicationController {

    String[] args;

    public ApplicationController(String[] args) {
        this.args = args;
    }

    public void run() {
        ArgumentParser.parse(args);
        AppConfig appConfig = AppConfig.getInstance();
        UserInterface userInterface;
        System.out.println("Is console" + appConfig.isConsole());
        if (appConfig.isConsole()) {
            System.out.println("Cnsl");

        } else {
            System.out.println("Frame");
            new MainFrame().setVisible(true);

        }
    }
}
