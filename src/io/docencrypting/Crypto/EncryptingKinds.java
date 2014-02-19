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
        if (this.equals(Hill)) {
            return CryptoFactory.makeHill();
        } else if (this.equals(Morse)) {
            return CryptoFactory.makeMorse();
        } else if (this.equals(Playfair)) {
            return CryptoFactory.makePlayfair();
        }
        return null;
    }
}
