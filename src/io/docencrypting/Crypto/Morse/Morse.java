package io.docencrypting.Crypto.Morse;

import io.docencrypting.Crypto.CryptoFactory;
import io.docencrypting.Crypto.ICrypto;
import io.docencrypting.Entities.CryptoEntity;

import java.io.*;

public class Morse implements ICrypto {

    @Override
    public void decode(CryptoEntity entity) throws IOException {
        process(entity, new Processing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (String morse : line.split(" ")) {
                    builder.append(MorseCipher.getLetterFromMorse(morse));
                }
                return builder.toString();
            }
        });
    }

    private void process(CryptoEntity entity, Processing processing) throws IOException {
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

    @Override
    public void encode(CryptoEntity entity) throws IOException {
        process(entity, new Processing() {
            @Override
            public String perform(String line) {
                StringBuilder builder = new StringBuilder();
                for (Character character : line.toUpperCase().toCharArray()) {
                    builder.append(MorseCipher.getMorseFromLetter(character.toString()) + " ");
                }
                return builder.toString();
            }
        });
    }

    private interface Processing {

        public String perform(String str);

    }

    public static void main(String[] args) {
        // example
        // required file "test.txt" in root directory
        ICrypto morse = CryptoFactory.makeMorse();
        CryptoEntity entity = new CryptoEntity();
        entity.setFileIn(new File("test.txt"));
        entity.setFileOut(new File("test_encrypted_morse.txt"));
        try {
            morse.encode(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity.setFileIn(entity.getFileOut());
        entity.setFileOut(new File("test_decrypted_morse.txt"));

        try {
            morse.decode(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
