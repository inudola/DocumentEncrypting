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

    public static ICrypto getCryptoAlgorithm(EncryptingKinds kind) {
        switch (kind) {
             case Hill:
                 return makeHill();
             case Morse:
                 return makeMorse();
             case Playfair:
                return makePlayfair();
        }
        return null;
    }

}
