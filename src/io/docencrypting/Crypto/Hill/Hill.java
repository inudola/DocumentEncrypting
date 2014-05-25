package io.docencrypting.Crypto.Hill;

import io.docencrypting.Crypto.ICrypt;
import io.docencrypting.Crypto.Playfair.PlayfairCipher;
import io.docencrypting.Entities.CryptEntity;

import java.io.*;

/**
 * Implements Hill cipher
 */

public class Hill implements ICrypt {

    HillCipher cipher = new HillCipher();   /// Cipher logic

    /**
     * Decode file
     * @param entity contains all information that needed for cipher algorithm
     * @throws IOException
     * @see #encode(io.docencrypting.Entities.CryptEntity)
     */
    @Override
    public boolean decode(CryptEntity entity) throws IOException {
        return process(entity, new LineProcessing() {
            @Override
            public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password) {
                return createPasswordMatrix(lengthPassword, sideSize, password).inverse(HillCipher.getModulo());
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
        return process(entity, new LineProcessing() {
            @Override
            public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password) {
                return createPasswordMatrix(lengthPassword, sideSize, password);
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
        String password = entity.getPassword();

        int lengthPassword = password.length();
        int sideSize = HillUtils.getSideSizeOfMatrix(lengthPassword);

        Matrix passMatrix = processing.createMagicMatrix(lengthPassword, sideSize, password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String line;
        while ((line = reader.readLine()) != null) {
            int length = line.length();
            int numberOfBlocks = HillUtils.calculateNumberOfBlocks(length, sideSize);

            for (int i = 0; i < numberOfBlocks; i++) {
                Matrix phrase = new Matrix(sideSize, 1);
                for (int j = 0; j < sideSize; j++) {
                    if (i * sideSize + j < length) {
                        Integer number = cipher.getNumberHill(line.charAt(i * sideSize + j));
                        if (number == null) {
                            if (entity.getDialogHandler().show("Symbol don't find\nSkip this?", "Error")) {
                                number = HillCipher.SPACE_INDEX;
                            } else {
                                return false;
                            }
                        }
                        phrase.setElement(j, 0, number);
                    } else {
                        phrase.setElement(j, 0, HillCipher.getSpaceIndex());
                    }
                }

                StringBuilder encodeString = new StringBuilder();
                Matrix result = passMatrix.multiple(phrase, HillCipher.getModulo());

                for (int j = 0; j < sideSize; j++) {
                    encodeString.append(cipher.getLetterHill(result.getElement(j, 0)));
                }
                writer.write(encodeString.toString());
            }

            writer.newLine();
        }
        reader.close();
        writer.close();

        temp.renameTo(fileOut);
        return true;
    }

    private Matrix createPasswordMatrix(int lengthPassword, int sideSize, String password) {
        Matrix passMatrix = new Matrix(sideSize, sideSize);
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                if (i * sideSize + j < lengthPassword) {
                    passMatrix.setElement(i, j, cipher.getNumberHill(password.charAt(i * sideSize + j)));
                } else {
                    passMatrix.setElement(i, j, 0);
                }
            }
        }
        return passMatrix;
    }

    /**
     * Interface for function object
     */
    private interface LineProcessing {

        public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password);

    }

}
