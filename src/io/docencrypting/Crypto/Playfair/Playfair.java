package io.docencrypting.Crypto.Playfair;

import io.docencrypting.Crypto.ICrypt;
import io.docencrypting.Entities.CryptEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Vector;

/**
 * Implement Playfair cipher
 */
public class Playfair implements ICrypt {

    private static final Logger logger = LogManager.getLogger(Playfair.class.getName());
    /**
     * Pair number
     */
    static public class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " : " + y;
        }
    }

    /**
     * Decode logic
     * @param entity contains all information that needed for cipher algorithm
     * @return true if success, otherwise false
     * @throws IOException
     */
    @Override
    public boolean decode(CryptEntity entity) throws IOException {
        PlayfairCipher cipher = new PlayfairCipher();
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        File temp = File.createTempFile("temp", "txt");
        String password = entity.getPassword();
        cipher.fillKeyMatrix(password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String line;
        while ((line = reader.readLine()) != null) {
            line = cipher.processLine(line, entity.getDialogHandler());
            if (line == null) {
                logger.error("Line is empty");
                return false;
            }
            for (String word : line.split(" ")) {
                Vector<String> pairs = cipher.createPairArray(cipher.modifyText(word));
                StringBuilder encodeText = new StringBuilder();
                for (String pair : pairs) {

                    Pair coordFirstCharacter  = cipher.getCoordinate(pair.charAt(0));
                    Pair coordSecondCharacter = cipher.getCoordinate(pair.charAt(1));

                    if (coordFirstCharacter == null || coordSecondCharacter == null) continue;

                    if (coordFirstCharacter.x == coordSecondCharacter.x) {
                        if (coordFirstCharacter.x > 0) {
                            coordFirstCharacter.x--;
                        } else {
                            coordFirstCharacter.x = 4;
                        }
                        if (coordSecondCharacter.x > 0) {
                            coordSecondCharacter.x--;
                        } else {
                            coordSecondCharacter.x = 4;
                        }
                    } else if (coordFirstCharacter.y == coordSecondCharacter.y) {
                        if (coordFirstCharacter.y > 0) {
                            coordFirstCharacter.y--;
                        } else {
                            coordFirstCharacter.y = 4;
                        }
                        if (coordSecondCharacter.y > 0) {
                            coordSecondCharacter.y--;
                        } else {
                            coordSecondCharacter.y = 4;
                        }
                    } else {
                        int firstX = coordFirstCharacter.x;
                        coordFirstCharacter.x = coordSecondCharacter.x;
                        coordSecondCharacter.x = firstX;
                    }
                    encodeText.append(cipher.getCharacter(coordFirstCharacter.x, coordFirstCharacter.y))
                            .append(cipher.getCharacter(coordSecondCharacter.x, coordSecondCharacter.y));
                }
                writer.write(cipher.simplifyText(encodeText.toString()) + " ");
            }
            writer.newLine();
        }
        reader.close();
        writer.close();
        temp.renameTo(fileOut);
        return true;
    }

    /**
     * Encode logic
     * @param entity contains all information that needed for cipher algorithm
     * @return true if success, otherwise false
     * @throws IOException
     */
    @Override
    public boolean encode(CryptEntity entity) throws IOException {
        PlayfairCipher cipher = new PlayfairCipher();
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        File temp = File.createTempFile("temp", "txt");
        String password = entity.getPassword();
        cipher.fillKeyMatrix(password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String line;
        while ((line = reader.readLine()) != null) {
            /*entity.getDialogHandler().show("Symbol don't find\nSkip this?", "Error")*/
            line = cipher.processLine(line, entity.getDialogHandler());
            if (line == null) {
                logger.error("Line is empty");
                return false;
            }
            for (String word : line.split(" ")) {
                Vector<String> pairs = cipher.createPairArray(cipher.modifyText(word));
                StringBuilder encodeText = new StringBuilder();
                for (String pair : pairs) {

                    Pair coordFirstCharacter  = cipher.getCoordinate(pair.charAt(0));
                    Pair coordSecondCharacter = cipher.getCoordinate(pair.charAt(1));

                    if (coordFirstCharacter.x == coordSecondCharacter.x) {
                        if (coordFirstCharacter.x < 4) {
                            coordFirstCharacter.x++;
                        } else {
                            coordFirstCharacter.x = 0;
                        }
                        if (coordSecondCharacter.x < 4) {
                            coordSecondCharacter.x++;
                        } else {
                            coordSecondCharacter.x = 0;
                        }
                    } else if (coordFirstCharacter.y == coordSecondCharacter.y) {
                        if (coordFirstCharacter.y < 4) {
                            coordFirstCharacter.y++;
                        } else {
                            coordFirstCharacter.y = 0;
                        }
                        if (coordSecondCharacter.y < 4) {
                            coordSecondCharacter.y++;
                        } else {
                            coordSecondCharacter.y = 0;
                        }
                    } else {
                        int firstX = coordFirstCharacter.x;
                        coordFirstCharacter.x = coordSecondCharacter.x;
                        coordSecondCharacter.x = firstX;
                    }
                    encodeText.append(cipher.getCharacter(coordFirstCharacter.x, coordFirstCharacter.y))
                              .append(cipher.getCharacter(coordSecondCharacter.x, coordSecondCharacter.y));
                }
                encodeText.append(" ");
                writer.write(encodeText.toString());
            }
            writer.newLine();
        }
        reader.close();
        writer.close();
        temp.renameTo(fileOut);
        return true;
    }

}
