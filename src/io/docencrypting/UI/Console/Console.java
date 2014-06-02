package io.docencrypting.UI.Console;

import io.docencrypting.Controllers.EncryptingController;
import io.docencrypting.Crypto.DialogHandler;
import io.docencrypting.Parser.ArgumentParser;
import io.docencrypting.UI.IDataGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Console class
 */
public class Console implements IDataGet {

    private Vector<String> filesIn;
    private String password = null;
    private boolean needHidden;
    private String encryptName;
    private EncryptingController encryptingController;

    private static final Logger logger = LogManager.getLogger(Console.class.getName());

    private DialogHandler handler = new DialogHandler() {
        @Override
        public boolean show(String message, String title) {
            System.out.println(title);
            System.out.println(message);
            System.out.println("Enter [y/n]: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return reader.readLine().equalsIgnoreCase("y");
            } catch (Exception ex) {
                logger.error("Equals error");
                return false;
            }
        }
    };

    /**
     * Constructor
     */
    public Console() {
        encryptingController = new EncryptingController();
        encryptingController.setView(this);
    }

    @Override
    public File[] getFilesIn() {
        Vector<File> files= new Vector<>();
        for (String file : filesIn) {
            files.add(new File(file));
        }
        return files.toArray(new File[files.size()]);
    }

    @Override
    public File getFileOut() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getNeedHiddenFiles() {
        return needHidden;
    }

    @Override
    public String getNameEncryptingAlgorithm() {
        return encryptName;
    }

    @Override
    public DialogHandler getDialogHandler() {
        return handler;
    }

    /**
     * Run method
     */
    public void run() {
        filesIn = ArgumentParser.filesIn;
        password = ArgumentParser.password;
        needHidden = ArgumentParser.needHidden;
        encryptName = ArgumentParser.encryptName;
        System.out.println("Encrypting name: " +  encryptName);
        if (encryptName == null) {
            System.out.println("Exit");
            System.exit(1);
        }
        try {
            if (ArgumentParser.isEncrypt) {
                encryptingController.encrypt();
            } else {
                encryptingController.decrypt();
            }
        } catch (IOException e) {
            logger.error("Encrypt exit with error");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
