package io.docencrypting.Controllers;

import io.docencrypting.Crypto.EncryptingKinds;
import io.docencrypting.Entities.CryptoEntity;
import io.docencrypting.UI.IDataGet;
import io.docencrypting.Utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class EncryptingController {

    private IDataGet viewData;

    public void setViewData(IDataGet viewData) {
        this.viewData = viewData;
    }

    public EncryptingKinds[] getAvailableEncrypting() {
        return EncryptingKinds.values();
    }

    public void encryptWith() throws IOException {
        CryptoEntity cryptoEntity = createCryptoEntity();
        if (cryptoEntity == null) {
            //TODO fault message
            return;
        }
        if (cryptoEntity.getName() != null && !cryptoEntity.getName().isEmpty()) {
            EncryptingKinds.valueOf(cryptoEntity.getName()).getCrypt().encode(cryptoEntity);
        }
    }

    private CryptoEntity createCryptoEntity() {
        CryptoEntity cryptoEntity = new CryptoEntity();
        File fileIn;
        File fileOut;
        String password = null;
        String name = null;
        boolean needHidden = false;
        if (viewData == null) {
            return null;
        }
        fileIn = FileUtils.getFile(viewData.getFileIn());
        fileOut = FileUtils.getFile(viewData.getFileOut());
        needHidden = viewData.getNeedHiddenFiles();
        if (viewData.getPassword() != null) {
            password = viewData.getPassword();  //might be empty
        }
        if (viewData.getNameEncryptingAlgorithm() != null &&
                !viewData.getNameEncryptingAlgorithm().isEmpty()) {
            name = viewData.getNameEncryptingAlgorithm();   //don't might be empty
        }
        cryptoEntity.setName(name);
        cryptoEntity.setNeedHiddenFiles(needHidden);
        cryptoEntity.setFileIn(fileIn);
        cryptoEntity.setFileOut(fileOut);
        cryptoEntity.setPassword(password);
        return cryptoEntity;
    }

}
