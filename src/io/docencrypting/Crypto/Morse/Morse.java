package io.docencrypting.Crypto.Morse;

import io.docencrypting.Crypto.ICrypt;
import io.docencrypting.Entities.CryptoEntity;

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
     * @see #encode(io.docencrypting.Entities.CryptoEntity)
     */
    @Override
    public void decode(CryptoEntity entity) throws IOException {
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
    }

    /**
     * Encode file with Hill cipher
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #decode(io.docencrypting.Entities.CryptoEntity)
     */
    @Override
    public void encode(CryptoEntity entity) throws IOException {
        process(entity, new LineProcessing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (Character character : line.toUpperCase().toCharArray()) {
                    builder.append(cipher.getMorse(character.toString()) + " ");
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
    private void process(CryptoEntity entity, LineProcessing processing) throws IOException {
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(processing.perform(line));
            writer.newLine();
        }
        reader.close();
        writer.close();
    }



    private interface LineProcessing {

        public String perform(String str);

    }

}
