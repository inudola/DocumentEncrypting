package io.docencrypting.Crypto.Playfair;

import io.docencrypting.Crypto.ICrypt;
import io.docencrypting.Entities.CryptoEntity;

import java.io.*;
import java.util.Vector;

public class Playfair implements ICrypt {

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

    @Override
    public void decode(CryptoEntity entity) throws IOException {
        PlayfairCipher cipher = new PlayfairCipher();
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        String password = entity.getPassword();
        cipher.fillKeyMatrix(password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        String line;
        while ((line = reader.readLine()) != null) {
            line = cipher.processLine(line);
            System.out.println(line);
            for (String word : line.split(" ")) {
                Vector<String> pairs = cipher.createPairArray(cipher.modifyText(word));
                System.out.println(pairs);
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
    }

    @Override
    public void encode(CryptoEntity entity) throws IOException {
        PlayfairCipher cipher = new PlayfairCipher();
        File fileIn = entity.getFileIn();
        File fileOut = entity.getFileOut();
        String password = entity.getPassword();
        cipher.fillKeyMatrix(password);

        BufferedReader reader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
        String line;
        while ((line = reader.readLine()) != null) {
            line = cipher.processLine(line);
            System.out.println(line);
            for (String word : line.split(" ")) {
                Vector<String> pairs = cipher.createPairArray(cipher.modifyText(word));
                System.out.println(pairs);
                StringBuilder encodeText = new StringBuilder();
                for (String pair : pairs) {

                    Pair coordFirstCharacter  = cipher.getCoordinate(pair.charAt(0));
                    Pair coordSecondCharacter = cipher.getCoordinate(pair.charAt(1));

                    if (coordFirstCharacter == null || coordSecondCharacter == null) continue;

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
    }



    public static void main(String[] args) {
        Playfair cipher = new Playfair();
//        System.out.println(cipher.fillKeyMatrix("BADY"));
//        System.out.println(cipher.getCoordinate('Q').toString());
//        System.out.println(cipher.modifyText("BAAD"));
//        System.out.println(cipher.createPairArray(cipher.modifyText("BAAD")));
        CryptoEntity entity = new CryptoEntity();
        entity.setFileIn(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/text.txt"));
        entity.setFileOut(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/text_playfair.txt"));
        entity.setPassword("GYBNQKURP");
        try {
            cipher.encode(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        entity.setFileIn(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/text_playfair.txt"));
        entity.setFileOut(new File("/Users/dmitriy/Documents/JavaProject/DocumentEncrypting/text_encrypt.txt"));

        try {
            cipher.decode(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
