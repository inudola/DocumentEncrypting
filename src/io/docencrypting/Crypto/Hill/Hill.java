package io.docencrypting.Crypto.Hill;

import io.docencrypting.Crypto.ICrypto;
import io.docencrypting.Entities.CryptoEntity;

import java.io.*;

public class Hill implements ICrypto {


    @Override
    public void decode(CryptoEntity entity) throws IOException {
        process(entity, new LineProcessing() {
            @Override
            public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password) {
                return createPasswordMatrix(lengthPassword, sideSize, password).inverse(HillCipher.getModulo());
            }
        });
    }

    @Override
    public void encode(CryptoEntity entity) throws IOException {
        process(entity, new LineProcessing() {
            @Override
            public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password) {
                return createPasswordMatrix(lengthPassword, sideSize, password);
            }
        });
    }

    private void process(CryptoEntity entity, LineProcessing processing) throws IOException {
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        String password = entity.getPassword();

        int lengthPassword = password.length();
        int sideSize = HillUtils.getSideSizeOfMatrix(lengthPassword);

        Matrix passMatrix = processing.createMagicMatrix(lengthPassword, sideSize, password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        String line;
        while ((line = reader.readLine()) != null) {
            int count = line.length();
            int numberOfBlocks = HillUtils.calculateNumberOfBlocks(count, sideSize);

            for (int i = 0; i < numberOfBlocks; i++) {
                Matrix phrase = new Matrix(sideSize, 1);
                for (int j = 0; j < sideSize; j++) {
                    if (i * sideSize + j < count) {
                        phrase.setElement(j, 0, HillCipher.getNumber(line.charAt(i * sideSize + j)));
                    } else {
                        phrase.setElement(j, 0, HillCipher.getSpaceIndex());
                    }
                }

                StringBuilder encodeString = new StringBuilder();
                Matrix result = passMatrix.multiple(phrase, HillCipher.getModulo());

                for (int j = 0; j < sideSize; j++) {
                    encodeString.append(HillCipher.getLetter(result.getElement(j, 0)));
                }
                writer.write(encodeString.toString());
            }

            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    private Matrix createPasswordMatrix(int lengthPassword, int sideSize, String password) {
        Matrix passMatrix = new Matrix(sideSize, sideSize);
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                if (i * sideSize + j < lengthPassword) {
                    passMatrix.setElement(i, j, HillCipher.getNumber(password.charAt(i * sideSize + j)));
                } else {
                    passMatrix.setElement(i, j, 0);
                }
            }
        }
        return passMatrix;
    }

//    public static void main(String[] args) throws IOException {
//        Hill hill = CryptoFactory.makeHill();
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        CryptoEntity entity = new CryptoEntity();
//        File file = new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/text.txt");
//        entity.setFileIn(file);
//        entity.setFileOut(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/Ciphers/textCode.txt"));
//        entity.setPassword("GYBNQKURP");
//        hill.encode(entity);
//        entity.setFileIn(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/Ciphers/textCode.txt"));
//        entity.setFileOut(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/Ciphers/textEncode.txt"));
//        hill.decode(entity);
//    }

    private interface LineProcessing {

        public Matrix createMagicMatrix(int lengthPassword, int sideSize, String password);

    }

}
