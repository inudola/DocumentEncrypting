package io.docencrypting.Crypto.Morse;

import io.docencrypting.Config.AppConfig;
import io.docencrypting.Crypto.DialogHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MorseCipher {

    private static String DIVIDER = "DIVIDER";    /// divider symbol
    private static String SPACE = " ";            /// space
    private HashMap<String, String> letterToMorse = null;  ///conversation from letter to morse sequence
    private HashMap<String, String> morseToLetter = null;  ///conversation from morse sequence to letter

    /**
     * Initialize hashmaps
     */
    public MorseCipher() {
        letterToMorse = new HashMap<>();
        morseToLetter = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(AppConfig.DEFAULT_MORSE_CFG_PATH));
        } catch (Exception ex) {
            throw new MorseCipherNotLoadException("Don't find " + AppConfig.DEFAULT_MORSE_CFG_PATH);
        }
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                String[] tokens = str.split("#");
                int lastIndex = tokens.length - 1;
                for (int i = 0; i < lastIndex; i++) {
                    if (lastIndex > 0 && lastIndex < tokens.length)
                        letterToMorse.put(tokens[i], tokens[lastIndex]);
                }
                if (lastIndex > 0 && lastIndex < tokens.length)
                    morseToLetter.put(tokens[lastIndex], tokens[0]);
            }
            reader.close();
        } catch (IOException ex) {
            throw new MorseCipherNotLoadException(AppConfig.DEFAULT_MORSE_CFG_PATH + " don't read");
        }

    }

    /**
     * Get morse sequence from letter
     * @param letter letter for convert
     * @return morse sequence
     */
    public String getMorse(String letter, DialogHandler handler) {
        String morse = letterToMorse.get(letter);
        if (morse == null && handler.show("Symbol don't find\nSkip this?", "Error")) {
            morse = letterToMorse.get(DIVIDER);
        }
        return morse;
    }

    /**
     * Get letter from morse sequence
     * @param morse morse sequence for convert
     * @return letter
     */
    public String getLetter(String morse) {
        String letter = morseToLetter.get(morse);
        if (letter == null || letter.equals(DIVIDER)) {
            letter = SPACE;
        }
        return letter;
    }

}
