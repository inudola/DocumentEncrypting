package io.docencrypting.Crypto.Morse;

/**
 * Exception throw when cfg file not load
 */
public class MorseCipherNotLoadException extends RuntimeException {

    public MorseCipherNotLoadException(String message) {
        super(message);
    }
}
