package io.docencrypting.Controllers;

import io.docencrypting.Crypto.EncryptingKinds;
import io.docencrypting.Entities.CryptEntity;
import io.docencrypting.UI.IDataGet;
import io.docencrypting.Utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class EncryptingController {

    private IDataGet view;

    public void setView(IDataGet view) {
        this.view = view;
    }

    public EncryptingKinds[] getAvailableEncrypting() {
        return EncryptingKinds.values();
    }

    public boolean encryptWith() throws IOException {
        File[] files = view.getFilesIn();
        if (files.length == 0) {
            return false;
        }
        boolean answer = false;
        Vector<File> fileVector = FileUtils.getFilesFromPath(files, view.getNeedHiddenFiles());
        for (File file : fileVector) {
            CryptEntity cryptEntity = createCryptEntity(file, new IGetFileOut() {
                @Override
                public File getFileOut(String name, File fileIn) {
                    return new File(view.getFileOut(), "Encrypt_" + name + "_" + fileIn.getName());
                }
            });
            if (cryptEntity == null) {
                return false;
            }
            if (cryptEntity.getName() != null && !cryptEntity.getName().isEmpty()) {
                answer = EncryptingKinds.valueOf(cryptEntity.getName()).getCrypt().encode(cryptEntity);
            }
        }
        return answer;
    }

    public boolean decrypt() throws  IOException {
        File[] files = view.getFilesIn();
        if (files.length == 0) {
            return false;
        }
        Vector<File> fileVector = FileUtils.getFilesFromPath(files, view.getNeedHiddenFiles());
        for (File file : fileVector) {
            CryptEntity cryptEntity = createCryptEntity(file, new IGetFileOut() {
                @Override
                public File getFileOut(String name, File fileIn) {
                    return new File(view.getFileOut(), "Decrypt_" + name + "_" + fileIn.getName().substring(
                            fileIn.getName().lastIndexOf('_') + 1, fileIn.getName().length()));
                }
            });
            if (cryptEntity == null) {
                return false;
            }

            if (cryptEntity.getName() != null && !cryptEntity.getName().isEmpty()) {
                EncryptingKinds.valueOf(cryptEntity.getName()).getCrypt().decode(cryptEntity);
            }
        }
        return true;
    }

    public CryptEntity createCryptEntity(File fileIn, IGetFileOut fileOutGetter) {
        CryptEntity cryptEntity = new CryptEntity();
//        File fileIn;
        File fileOut;
        String password = null;
        String name = null;
        boolean needHidden;
        if (view == null) {
            return null;
        }
        if (view.getPassword() != null) {
            password = view.getPassword();  //might be empty
        }
        if (view.getNameEncryptingAlgorithm() != null &&
                !view.getNameEncryptingAlgorithm().isEmpty()) {
            name = view.getNameEncryptingAlgorithm();   //don't might be empty
        }
        fileOut = fileOutGetter.getFileOut(name, fileIn);
        needHidden = view.getNeedHiddenFiles();
        cryptEntity.setDialogHandler(view.getDialogHandler());

        cryptEntity.setName(name);
        cryptEntity.setNeedHiddenFiles(needHidden);
        cryptEntity.setFileIn(fileIn);
        cryptEntity.setFileOut(fileOut);
        cryptEntity.setPassword(password);
        return cryptEntity;
    }

    private interface IGetFileOut {
        public File getFileOut(String name, File fileIn);
    }

}
