package io.docencrypting.Crypt.Morse;

import io.docencrypting.Crypt.DialogHandler;
import io.docencrypting.Crypt.ICrypt;
import io.docencrypting.Entities.CryptEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Implements Morse cipher
 */

public class Morse implements ICrypt {

    MorseCipher cipher = new MorseCipher(); ///Morse cipher logic

    private static final Logger logger = LogManager.getLogger(Morse.class.getName());

    /**
     * Decode file
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #encode(io.docencrypting.Entities.CryptEntity)
     */
    @Override
    public boolean decode(CryptEntity entity) throws IOException {
        final DialogHandler handler = entity.getDialogHandler();
        return process(entity, new LineProcessing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (String morse : line.split(" ")) {
                    String letter = cipher.getLetter(morse, handler);
                    if (letter == null) {
                        logger.error("Decrypting Morse fault");
                        return null;
                    }
                    builder.append(letter);
                }
                return builder.toString();
            }
        });
    }

    /**
     * Encode file with Hill cipher
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptEntity)
     */
    @Override
    public boolean encode(CryptEntity entity) throws IOException {
        final DialogHandler handler = entity.getDialogHandler();
        return process(entity, new LineProcessing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (Character character : line.toUpperCase().toCharArray()) {
                    String morse = cipher.getMorse(character.toString(), handler);
                    if (morse == null) {
                        logger.error("Encrypting Morse fault");
                        return null;
                    }
                    builder.append(morse).append(" ");
                }
                return builder.toString();
            }
        });
    }

    /**
     * Common logic for encode and decode operation
     * @param entity contains all information that needed for cipher algorithm
     * @param processing function object that convert symbols
     * @throws IOException
     */
    private boolean process(CryptEntity entity, LineProcessing processing) throws IOException {
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        File temp = File.createTempFile("temp", "txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String line;
        while ((line = reader.readLine()) != null) {
            String text = processing.perform(line);
            if (text == null) {
                return false;
            }
            writer.write(text);
            writer.newLine();
        }
        reader.close();
        writer.close();
        temp.renameTo(fileOut);
        return true;
    }



    private interface LineProcessing {

        public String perform(String str);

    }

}
