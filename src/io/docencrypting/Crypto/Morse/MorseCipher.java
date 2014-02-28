package io.docencrypting.Crypto.Morse;

import io.docencrypting.Config.AppConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MorseCipher {

    private static String DIVIDER = "DIVIDER";
    private static String SPACE = " ";
    private HashMap<String, String> letterToMorse = null;
    private HashMap<String, String> morseToLetter = null;

    private MorseCipher() {
        letterToMorse = new HashMap<>();
        morseToLetter = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(AppConfig.getInstance().getMorseCipherPath()));
        } catch (Exception ex) {
            throw new MorseCipherNotLoadException();
        }
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                String[] tokens = str.split("#");
                for (int i = 0; i < tokens.length - 1; i++) {
                    letterToMorse.put(tokens[i], tokens[tokens.length - 1]);
                }
                morseToLetter.put(tokens[tokens.length - 1], tokens[0]);
            }
            reader.close();
        } catch (IOException ex) {
            throw new MorseCipherNotLoadException();
        }

    }

    public static MorseCipher getInstance() {
        return MorseHolder.MORSE_CIPHER;
    }

    public static synchronized String getMorseFromLetter(String letter) {
        return getInstance().getMorse(letter);
    }

    public static synchronized String getLetterFromMorse(String morse) {
        return getInstance().getLetter(morse);
    }

    public HashMap<String, String> getLetterToMorse() {
        return letterToMorse;
    }

    public HashMap<String, String> getMorseToLetter() {
        return morseToLetter;
    }

    private String getMorse(String letter) {
        String morse = letterToMorse.get(letter);
        if (morse == null) {
            morse = letterToMorse.get(DIVIDER);
        }
        return morse;
    }

    private String getLetter(String morse) {
        String letter = morseToLetter.get(morse);
        if (letter == null || letter.equals(DIVIDER)) {
            letter = SPACE;
        }
        return letter;
    }

    private static class MorseHolder {
        public static final MorseCipher MORSE_CIPHER = new MorseCipher();
    }

}
