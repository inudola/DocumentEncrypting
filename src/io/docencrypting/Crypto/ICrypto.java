package io.docencrypting.Crypto;

import io.docencrypting.Entities.CryptoEntity;

public interface ICrypto {

    public void decode(CryptoEntity entity);

    public void encode(CryptoEntity entity);

}
