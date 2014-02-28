package io.docencrypting.Crypto;

public enum EncryptingKinds {
    Hill("Hill"),
    Morse("Morse"),
    Playfair("Playfair");
    private String name;

    private EncryptingKinds(String name) {
        this.name = name;
    }

    public ICrypto getCrypt() {
        return CryptoFactory.getCryptoAlgorithm(this);
    }
}
