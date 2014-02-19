package io.docencrypting.Entities;

import java.io.File;

public class CryptoEntity {

    private File fileIn = null;

    private File fileOut = null;

    private String password = null;

    public File getFileIn() {
        return fileIn;
    }

    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }

    public File getFileOut() {
        return fileOut;
    }

    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
