package io.docencrypting.Crypto;

/**
 * Encryption kinds
 */
public enum EncryptingKinds {
    Hill("Hill"),
    Morse("Morse"),
    Playfair("Playfair");

    private String name;    ///Name cipher

    /**
     * Create encrypting kinds
     * @param name crypt name
     */
    private EncryptingKinds(String name) {
        this.name = name;
    }

    /**
     * Get crypt
     * @return crypt
     */
    public ICrypt getCrypt() {
        return CryptoFactory.getCryptAlgorithm(this);
    }
}
