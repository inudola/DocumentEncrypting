package io.docencrypting.Parser;

import io.docencrypting.Config.AppConfig;

import java.io.File;
import java.util.Vector;

public class ArgumentParser {

    static Vector<String> filesIn;
    static String pathOut;
    static String password;

    public static void parse(String[] args) {
        filesIn = new Vector<>();
        AppConfig appConfig = AppConfig.getInstance();

        System.out.println(args.length);

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            System.out.println(arg);
            switch (arg) {
                case "-c":
                    System.out.println("Set console");
                    appConfig.setConsole(true);
                    break;
                case "-l":
                    System.out.println(appConfig.toString());
                    break;
                case "-h":
                     System.out.println("Help");
                    System.exit(0);
                    break;
                case "-p":
                    if (args.length < i + 1)
                        System.exit(1);
                    password = args[++i];
                    System.out.println("Password: " + password);
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
