package io.docencrypting.Crypt;

/**
 * Encryption kinds
 */
public enum EncryptingKinds {
    Hill(),
    Morse(),
    Playfair();

    /**
     * Get crypt
     * @return crypt
     */
    public ICrypt getCrypt() {
        return CryptFactory.getCryptAlgorithm(this);
    }
}
