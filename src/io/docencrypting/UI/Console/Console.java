package io.docencrypting.UI.Console;

import io.docencrypting.Crypto.DialogHandler;
import io.docencrypting.UI.IDataGet;
import io.docencrypting.UI.UserInterface;

import java.io.File;

public class Console implements IDataGet, UserInterface {

    private File[] filesIn = null;
    private File fileOut;

    @Override
    public File[] getFilesIn() {
        return filesIn;
    }

    @Override
    public File getFileOut() {
        return fileOut;
    }

    @Override
    public String getPassword() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getNeedHiddenFiles() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getNameEncryptingAlgorithm() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DialogHandler getDialogHandler() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void makeUI() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void run() {

    }

}
