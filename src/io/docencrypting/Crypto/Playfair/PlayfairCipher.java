package io.docencrypting.Crypto.Playfair;

import io.docencrypting.Config.AppConfig;
import io.docencrypting.Crypto.DialogHandler;
import io.docencrypting.Crypto.Hill.HillCipherNotLoadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class PlayfairCipher {

    private Vector<Vector<Character>> keyMatrix = new Vector<>();
    private Vector<Character> alphabet = new Vector<>();

    public PlayfairCipher() {
        fillAlphabet();
    }

    public Playfair.Pair getCoordinate(Character character) {
        if (character.equals('J'))
            character = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (character == keyMatrix.get(i).get(j)) {
                    return new Playfair.Pair(i, j);
                }
            }
        }
        return null;
    }

    public Character getCharacter(int x, int y) {
        return keyMatrix.get(x).get(y);
    }

    private void fillAlphabet() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(AppConfig.DEFAULT_PLAYFAIR_CFG_PATH));
        } catch (IOException ex) {
            throw new PlayfairCipherNotLoadException("Don't find " +
                    AppConfig.DEFAULT_PLAYFAIR_CFG_PATH);
        }
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                alphabet.add(str.charAt(0));
            }
        } catch (IOException e) {
            throw new PlayfairCipherNotLoadException(AppConfig.DEFAULT_PLAYFAIR_CFG_PATH + " don't read");
        }
    }

    public Vector<Vector<Character>> fillKeyMatrix(String key) {
        Vector<Character> characters = new Vector<>();
        for (Character character : key.toCharArray()) {
            if (!characters.contains(character))
                characters.add(character);
        }
        for (Character letter : alphabet) {
            if (!characters.contains(letter)) {
                characters.add(letter);
            }
        }
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            keyMatrix.add(new Vector<Character>());
            for (int j = 0; j < 5; j++) {
                keyMatrix.elementAt(i).add(characters.elementAt(counter));
                counter++;
            }
        }
        return keyMatrix;
    }

    public Vector<Character> modifyText(String text) {
        Vector<Character> modifiedText = new Vector<>();
        for (int i = 0; i < text.length(); i++) {
            if (!modifiedText.isEmpty() && i % 2 != 0 && modifiedText.lastElement().equals(text.charAt(i)))
                modifiedText.add('Q');
            modifiedText.add(text.charAt(i));
        }
        if (modifiedText.size() % 2 != 0)
            modifiedText.add('Q');
        return modifiedText;
    }

    public String simplifyText(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i < text.length() - 2 && text.charAt(i) == text.charAt(i + 2) && text.charAt(i+1) == 'Q') {
                builder.append(text.charAt(i));
                i++;
                continue;
            }
            if (i < text.length() - 1 && text.charAt(i) == text.charAt(i+1) && text.charAt(i) == 'Q') {
                builder.append(text.charAt(i));
                i++;
                continue;
            }
            builder.append(text.charAt(i));
        }
        if (builder.length() > 0 && builder.charAt(builder.length() - 1) == 'Q') {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public Vector<String> createPairArray(Vector<Character> text) {
        Vector<String> pairArray = new Vector<>();
        for (int i = 0; i < text.size() / 2; i++)
            pairArray.add(text.get(i * 2).toString() + text.get(i * 2 + 1));
        return pairArray;
    }

    private boolean contains(Character character) {
        return alphabet.contains(character);
    }

    public String processLine(String line, DialogHandler handler) {
        line = line.toUpperCase();
        StringBuilder builder = new StringBuilder();
        for (Character character : line.toCharArray()) {
            if (contains(character) || character ==  ' ') {
                builder.append(character);
            } else {
                if (!handler.show("Symbol don't find\nSkip this?", "Error")) {
                    return null;
                }
            }
        }
        return builder.toString();
    }

}
