package io.docencrypting.Crypt.Morse;

/**
 * Exception throw when cfg file not load
 */
public class MorseCipherNotLoadException extends RuntimeException {

    public MorseCipherNotLoadException(String message) {
        super(message);
    }
}
