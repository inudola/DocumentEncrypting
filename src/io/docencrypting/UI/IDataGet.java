package io.docencrypting.UI;

import java.io.File;

public interface IDataGet {

    public String getFileIn();

    public String getFileOut();

    public String getPassword();

    public boolean getNeedHiddenFiles();

    public String getNameEncryptingAlgorithm();

}
