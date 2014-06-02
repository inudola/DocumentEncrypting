package io.docencrypting.Crypt.Hill;

/**
 * Exception throw when cfg file not load
 */
public class HillCipherNotLoadException extends RuntimeException {

    public HillCipherNotLoadException(String message) {
        super(message);
    }
}
