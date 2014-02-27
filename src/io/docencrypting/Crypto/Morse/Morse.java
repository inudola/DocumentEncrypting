package io.docencrypting.Crypto.Morse;

import io.docencrypting.Crypto.ICrypto;
import io.docencrypting.Entities.CryptoEntity;

import java.io.*;

public class Morse implements ICrypto {

    @Override
    public void decode(CryptoEntity entity) throws IOException {
        process(entity, new LineProcessing() {
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

    @Override
    public void encode(CryptoEntity entity) throws IOException {
        process(entity, new LineProcessing() {
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

    private interface LineProcessing {

        public String perform(String str);

    }

//    public static void main(String[] args) {
//        // example
//        // required file "test.txt" in root directory
//        ICrypto morse = CryptoFactory.makeMorse();
//        CryptoEntity entityEncrypt = new CryptoEntity();
//        CryptoEntity entityDecrypt = new CryptoEntity();
//        Vector<File> files = FileUtils.getFilesFromPath(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/testing"), true);
//        for (File file : files) {
//            System.out.println("File: " + file.getName() + ", Mime type: " + new MimetypesFileTypeMap().getContentType(file));
//            if (!(new MimetypesFileTypeMap().getContentType(file).equals("text/plain"))) {
//                continue;
//            }
//            entityEncrypt.setFileIn(file);
//            entityEncrypt.setFileOut(new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + "_encoding.txt"));
//            entityDecrypt.setFileIn(entityEncrypt.getFileOut());
//            entityDecrypt.setFileOut(new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + "_decoding.txt"));
//            try {
//                morse.encode(entityEncrypt);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                morse.decode(entityDecrypt);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
