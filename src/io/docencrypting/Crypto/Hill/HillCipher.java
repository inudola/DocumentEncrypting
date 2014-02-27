package io.docencrypting.Crypto.Hill;

import io.docencrypting.Config.AppConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HillCipher {

    private static String SPACE = " ";
    private static int SPACE_INDEX = 0;

    private static HashMap<Integer, Character> integerToString = null;
    private static HashMap<Character, Integer> stringToInteger = null;

    private HillCipher() {
        integerToString = new HashMap<>();
        stringToInteger = new HashMap<>();
        fillHashMaps();
    }

    public static HillCipher getInstance() {
        return HillHolder.HILL_CIPHER;
    }

    private String getLetterHill(Integer number) {
        return integerToString.keySet().contains(number)
                    ? integerToString.get(number).toString()
                        : SPACE;
    }

    private int getNumberHill(Character character) {
        return stringToInteger.keySet().contains(character)
                ? stringToInteger.get(character)
                : SPACE_INDEX;
    }

    public static synchronized String getLetter(Integer number) {
        return getInstance().getLetterHill(number);
    }

    public static synchronized Integer getNumber(Character character) {
        return getInstance().getNumberHill(character);
    }

    public static int getModulo() {
        return stringToInteger.size();
    }

    public static String getSpace() {
        return SPACE;
    }

    public static Integer getSpaceIndex() {
        return SPACE_INDEX;
    }

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

    private static class HillHolder {
        public static final HillCipher HILL_CIPHER = new HillCipher();
    }

}
