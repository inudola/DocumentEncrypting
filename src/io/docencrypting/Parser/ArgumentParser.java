package io.docencrypting.Parser;

import io.docencrypting.Config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Vector;

public class ArgumentParser {

    public static Vector<String> filesIn;
    public static String password;
    public static String encryptName;
    public static boolean needHidden = false;
    public static boolean isEncrypt = true;
    private static final Logger logger = LogManager.getLogger(ArgumentParser.class.getName());

    public static void parse(String[] args) {
        filesIn = new Vector<>();
        AppConfig appConfig = AppConfig.getInstance();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-c":
                    appConfig.setConsole(true);
                    break;
                case "-l":
                    appConfig.setLogging(true);
                    break;
                case "-h":
                    System.out.println("usage: DocumentEncryption.jar -n \"Encrypting name\" [-p \"Password\"] input files [-e | -d]");
                    System.out.println("Encrypting name: Morse, Hill, Playfair");
                    System.exit(0);
                    break;
                case "-n":
                    if (args.length < i + 1) {
                        logger.error("Wrong format");
                        System.exit(1);
                    }
                    encryptName = args[++i];
                    break;
                case "-p":
                    if (args.length < i + 1)  {
                        logger.error("Wrong format");
                        System.exit(1);
                    }
                    password = args[++i];
                    break;
                case "-v":
                    needHidden = true;
                    break;
                case "-e":
                    isEncrypt = true;
                    break;
                case "-d":
                    isEncrypt = false;
                    break;
                default:
                    if (new File(arg).exists()) {
                        filesIn.add(arg);
                    } else {
                        logger.error("Wrong parameter: " + arg);
                        System.exit(2);
                    }
            }
        }
    }
}
