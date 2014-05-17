package io.docencrypting.Controllers;

import io.docencrypting.Crypto.EncryptingKinds;
import io.docencrypting.Entities.CryptoEntity;
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
        Vector<File> fileVector = FileUtils.getFilesFromPath(files, view.getNeedHiddenFiles());
        for (File file : fileVector) {
            System.out.println(file.getAbsolutePath());
            CryptoEntity cryptoEntity = createCryptoEntity(file, new IGetFileOut() {
                @Override
                public File getFileOut(String name, File fileIn) {
                    return new File(view.getFileOut(), "Encrypt_" + name + "_" + fileIn.getName());
                }
            });
            System.out.println(cryptoEntity.getFileOut().getAbsolutePath());
            if (cryptoEntity == null) {
                return false;
            }
            if (cryptoEntity.getName() != null && !cryptoEntity.getName().isEmpty()) {
                EncryptingKinds.valueOf(cryptoEntity.getName()).getCrypt().encode(cryptoEntity);
            }
        }
        return true;
    }

    public boolean decrypt() throws  IOException {
        File[] files = view.getFilesIn();
        if (files.length == 0) {
            return false;
        }
        Vector<File> fileVector = FileUtils.getFilesFromPath(files, view.getNeedHiddenFiles());
        for (File file : fileVector) {
            System.out.println(file.getAbsolutePath());
            CryptoEntity cryptoEntity = createCryptoEntity(file, new IGetFileOut() {
                @Override
                public File getFileOut(String name, File fileIn) {
                    return new File(view.getFileOut(), "Decrypt_" + name + "_" + fileIn.getName().substring(
                            fileIn.getName().lastIndexOf('_') + 1, fileIn.getName().length()));
                }
            });
            if (cryptoEntity == null) {
                return false;
            }
            System.out.println(cryptoEntity.getFileOut().getAbsolutePath());

            if (cryptoEntity.getName() != null && !cryptoEntity.getName().isEmpty()) {
                EncryptingKinds.valueOf(cryptoEntity.getName()).getCrypt().decode(cryptoEntity);
            }
        }
        return true;
    }

    public CryptoEntity createCryptoEntity(File fileIn, IGetFileOut fileOutGetter) {
        CryptoEntity cryptoEntity = new CryptoEntity();
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

        cryptoEntity.setName(name);
        cryptoEntity.setNeedHiddenFiles(needHidden);
        cryptoEntity.setFileIn(fileIn);
        cryptoEntity.setFileOut(fileOut);
        cryptoEntity.setPassword(password);
        return cryptoEntity;
    }

    private interface IGetFileOut {
        public File getFileOut(String name, File fileIn);
    }

}
