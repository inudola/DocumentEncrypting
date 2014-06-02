package io.docencrypting.Crypt;

import io.docencrypting.Crypt.Hill.Hill;
import io.docencrypting.Crypt.Morse.Morse;
import io.docencrypting.Crypt.Playfair.Playfair;

public class CryptFactory {

    /**
     * Create Hill cipher
     * @return Hill cipher instance
     */
    public static Hill makeHill() {
        return new Hill();
    }

    /**
     * Create Morse cipher
     * @return Morse cipher instance
     */
    public static Morse makeMorse() {
        return new Morse();
    }

    /**
     * Create Playfair cipher
     * @return Playfair cipher instance
     */
    public static Playfair makePlayfair() {
        return new Playfair();
    }

    /**
     * Create cipher
     * @param kind kind cipher
     * @return cipher
     */
    public static ICrypt getCryptAlgorithm(EncryptingKinds kind) {
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
