package io.docencrypting.Entities;

import io.docencrypting.Crypt.DialogHandler;
import java.io.File;

/**
 * Class that contains all information about processing file
 * @see io.docencrypting.Crypt.Morse.Morse
 * @see io.docencrypting.Crypt.Hill.Hill
 * @see io.docencrypting.Crypt.Playfair.Playfair
 */

public class CryptEntity {

    private String name = null;                     /// Name of cipher algorithm
    private File fileIn = null;                     /// Input file
    private File fileOut = null;                    /// Output file
    private String password = null;                 /// Password for cipher algorithm (not for all)
    private boolean needHiddenFiles = false;        /// If need processing hidden files
    private DialogHandler dialogHandler = null;     /// Handle message

    /**
     * Get name of cipher algorithm
     * @return name of cipher algorithm
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of cipher algorithm
     * @param name new name of cipher algorithm
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * If need processing hidden files
     * @return need processing hidden files
     * @see #setNeedHiddenFiles(boolean)
     */
    public boolean isNeedHiddenFiles() {
        return needHiddenFiles;
    }

    /**
     * Set if need processing hidden files
     * @param needHiddenFiles need processing hidden files
     * @see #isNeedHiddenFiles()
     */
    public void setNeedHiddenFiles(boolean needHiddenFiles) {
        this.needHiddenFiles = needHiddenFiles;
    }

    /**
     * Get input file
     * @return input file
     * @see #setFileIn(java.io.File)
     */
    public File getFileIn() {
        return fileIn;
    }

    /**
     * Set input file
     * @param fileIn input file
     * @see #getFileIn()
     */
    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }

    /**
     * Get output file
     * @return output file
     * @see #setFileOut(java.io.File)
     */
    public File getFileOut() {
        return fileOut;
    }

    /**
     * Set output file
     * @param fileOut output file
     * @see #getFileOut()
     */
    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    /**
     * Get password
     * @return password
     * @see #setPassword(String)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password password
     * @see #getPassword()
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get handler
     * @return handler
     * @see #setDialogHandler(io.docencrypting.Crypt.DialogHandler)
     */
    public DialogHandler getDialogHandler() {
        return dialogHandler;
    }

    /**
     * Set handler
     * @param dialogHandler handler
     * @see #getDialogHandler()
     */
    public void setDialogHandler(DialogHandler dialogHandler) {
        this.dialogHandler = dialogHandler;
    }

}
