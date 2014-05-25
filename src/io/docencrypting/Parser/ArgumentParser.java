package io.docencrypting.Parser;

import io.docencrypting.Config.AppConfig;

import java.io.File;
import java.util.Vector;

public class ArgumentParser {

    static Vector<String> filesIn;
    static String pathOut;

    public static void parse(String[] args) {
        filesIn = new Vector<>();
        AppConfig appConfig = AppConfig.getInstance();

        System.out.println(args.length);

        for (String arg : args) {
            System.out.println(arg);
            switch (arg) {
                case "-c":
                    System.out.println("Set console");
                    appConfig.setConsole(true);
                    break;
                case "-l":
                    appConfig.toString();
                    break;
                case "-h":
                    System.out.println("Help");
                    System.exit(0);
                    break;
                default:
                    if (new File(arg).exists()) {
                        filesIn.add(arg);
                    } else {
                        System.out.println("Path out: " + arg);
                        pathOut = arg;
                    }
            }
        }
    }
}
