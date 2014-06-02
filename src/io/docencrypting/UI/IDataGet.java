package io.docencrypting.UI;

import io.docencrypting.Crypt.DialogHandler;

import java.io.File;

public interface IDataGet {

    /**
     * Get files input
     * @return input files
     */
    public File[] getFilesIn();

    /**
     * Get file out
     * @return file out
     */
    public File getFileOut();

    /**
     * Get password
     * @return password
     */
    public String getPassword();

    /**
     * Get need hidden files
     * @return need hidden files
     */
    public boolean getNeedHiddenFiles();

    /**
     * Get name encrypting algorithm
     * @return name encrypting algorithm
     */
    public String getNameEncryptingAlgorithm();

    /**
     * Get dialog handler
     * @return dialog handler
     */
    public DialogHandler getDialogHandler();

}
