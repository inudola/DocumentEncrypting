package io.docencrypting.Crypto;

import io.docencrypting.Entities.CryptoEntity;

import java.io.IOException;

public interface ICrypto {

    public void decode(CryptoEntity entity) throws IOException;

    public void encode(CryptoEntity entity) throws IOException;

}
