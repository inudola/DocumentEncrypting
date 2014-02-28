package io.docencrypting.Entities;

import java.io.File;

public class CryptoEntity {

    private String name = null;
    private File fileIn = null;
    private File fileOut = null;
    private String password = null;
    private boolean needHiddenFiles = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeedHiddenFiles() {
        return needHiddenFiles;
    }

    public void setNeedHiddenFiles(boolean needHiddenFiles) {
        this.needHiddenFiles = needHiddenFiles;
    }

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
