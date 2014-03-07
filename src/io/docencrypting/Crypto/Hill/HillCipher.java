package io.docencrypting.Crypto.Hill;

import io.docencrypting.Config.AppConfig;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Helper class for hill cipher letter
 */
public class HillCipher {

    private static String SPACE = " ";  /// Space character
    private static int SPACE_INDEX = 0; /// Index of space character
    private static HashMap<Integer, Character> integerToString = null;  /// Contains that replacement
    private static HashMap<Character, Integer> stringToInteger = null;  /// Contains that replacement

    private HillCipher() {
        integerToString = new HashMap<>();
        stringToInteger = new HashMap<>();
        fillHashMaps();
    }

    /**
     * Get instance
     * @return instance
     */
    public static HillCipher getInstance() {
        return HillHolder.HILL_CIPHER;
    }

    /**
     * Get letter from number
     * @param number number
     * @return Letter
     * @see #getNumber(Character)
     */
    public static synchronized String getLetter(Integer number) {
        return getInstance().getLetterHill(number);
    }

    /**
     * Get number from letter
     * @param character letter
     * @return letter
     * @see #getLetter(Integer)
     */
    public static synchronized Integer getNumber(Character character) {
        return getInstance().getNumberHill(character);
    }

    /**
     * Get modulo
     * @return modulo
     */
    public static int getModulo() {
        return stringToInteger.size();
    }

    /**
     * Get space symbol
     * @return space symbol
     */
    public static String getSpace() {
        return SPACE;
    }

    /**
     * Get space index in map
     * @return space index
     */
    public static Integer getSpaceIndex() {
        return SPACE_INDEX;
    }

    /**
     * Get letter hill
     * If map doesn't contains character, return space
     * @param number index of letter
     * @return character
     */
    private String getLetterHill(Integer number) {
        return integerToString.keySet().contains(number)
                ? integerToString.get(number).toString()
                : SPACE;
    }

    /**
     * Get letter index
     * If index doesn't contains in map, return space index
     * @param character letter
     * @return index
     */
    private int getNumberHill(Character character) {
        return stringToInteger.keySet().contains(character)
                ? stringToInteger.get(character)
                : SPACE_INDEX;
    }

    /**
     * Fill hash maps from file
     */
    private void fillHashMaps() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(AppConfig.getInstance().getHillCipherPath()));
        } catch (Exception ex) {
            throw new HillCipherNotLoadException();
        }
        int count = 0;
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                if (!str.isEmpty()) {
                    integerToString.put(count, str.charAt(0));
                    stringToInteger.put(str.charAt(0), count);
                    count++;
                }
            }
            integerToString.put(count, ' ');
            stringToInteger.put(' ', count);
            SPACE_INDEX = count;
        } catch (IOException e) {
            throw new HillCipherNotLoadException();
        }
    }

    /**
     * Hold Hill instance
     */
    private static class HillHolder {
        public static final HillCipher HILL_CIPHER = new HillCipher();
    }

}
