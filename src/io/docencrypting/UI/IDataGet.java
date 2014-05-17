package io.docencrypting.UI;

import java.io.File;

public interface IDataGet {

    public File[] getFilesIn();

    public File getFileOut();

    public String getPassword();

    public boolean getNeedHiddenFiles();

    public String getNameEncryptingAlgorithm();

}
