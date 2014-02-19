package io.docencrypting.Crypto;

public class CryptoFactory {

    public static Hill makeHill() {
        return new Hill();
    }

    public static Morse makeMorse() {
        return new Morse();
    }

    public static Playfair makePlayfair() {
        return new Playfair();
    }

}
