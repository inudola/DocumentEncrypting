package io.docencrypting.Crypto;

import io.docencrypting.Entities.CryptoEntity;
import java.io.IOException;

/**
 * General interface for all cipher algorithms
 */

public interface ICrypto {

    /**
     * Decode the file that provide {@link io.docencrypting.Entities.CryptoEntity}
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptoEntity)
     */
    public void decode(CryptoEntity entity) throws IOException;

    /**
     * Encode the file that provide {@link io.docencrypting.Entities.CryptoEntity}
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptoEntity)
     */
    public void encode(CryptoEntity entity) throws IOException;

}
