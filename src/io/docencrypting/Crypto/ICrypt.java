package io.docencrypting.Crypto;

import io.docencrypting.Entities.CryptEntity;

import java.io.IOException;

/**
 * Provide interface for all cipher algorithms
 */

public interface ICrypt {

    /**
     * Decode the file that provide {@link io.docencrypting.Entities.CryptEntity}
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #encode(io.docencrypting.Entities.CryptEntity)
     */
    public boolean decode(CryptEntity entity) throws IOException;

    /**
     * Encode the file that provide {@link io.docencrypting.Entities.CryptEntity}
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptEntity)
     */
    public boolean encode(CryptEntity entity) throws IOException;

}
