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

    public static String SPACE = " ";  /// Space character
    public static int SPACE_INDEX = 0; /// Index of space character
    private static HashMap<Integer, Character> integerToString = null;  /// Contains that replacement
    private static HashMap<Character, Integer> stringToInteger = null;  /// Contains that replacement

    /**
     * Initialize hashmaps
     */
    public HillCipher() {
        integerToString = new HashMap<>();
        stringToInteger = new HashMap<>();
        fillHashMaps();
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
    public String getLetterHill(Integer number) {
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
    public Integer getNumberHill(Character character) {
        return stringToInteger.get(character);
    }

    /**
     * Fill hash maps from file
     */
    private void fillHashMaps() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(AppConfig.DEFAULT_HILL_CFG_PATH));
        } catch (Exception ex) {
            throw new HillCipherNotLoadException("Don't find " + AppConfig.DEFAULT_HILL_CFG_PATH);
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
            throw new HillCipherNotLoadException(AppConfig.DEFAULT_HILL_CFG_PATH + " don't read");
        }
    }


}
