package io.docencrypting.Crypto.Morse;

import io.docencrypting.Crypto.ICrypt;
import io.docencrypting.Entities.CryptEntity;

import java.io.*;

/**
 * Implements Morse cipher
 */

public class Morse implements ICrypt {

    MorseCipher cipher = new MorseCipher(); ///Morse cipher logic

    /**
     * Decode file
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #encode(io.docencrypting.Entities.CryptEntity)
     */
    @Override
    public boolean decode(CryptEntity entity) throws IOException {
        process(entity, new LineProcessing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (String morse : line.split(" ")) {
                    builder.append(cipher.getLetter(morse));
                }
                return builder.toString();
            }
        });
        return true;
    }

    /**
     * Encode file with Hill cipher
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptEntity)
     */
    @Override
    public boolean encode(final CryptEntity entity) throws IOException {
        return process(entity, new LineProcessing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (Character character : line.toUpperCase().toCharArray()) {
                    String morse = cipher.getMorse(character.toString(), entity.getDialogHandler());
                    if (morse == null) {
                        return null;
                    }
                    builder.append(morse + " ");
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
