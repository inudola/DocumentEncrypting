package io.docencrypting.Crypto.Hill;

class HillUtils {

    static int calculateNumberOfBlocks(int count, int sideSize) {
        if (count < sideSize) {
            return 1;
        } else if (count % sideSize == 0) {
            return count / sideSize;
        } else {
            return count / sideSize + 1;
        }
    }

    static int getSideSizeOfMatrix(int lengthPassword) {
        return (Math.sqrt(lengthPassword) - (int) Math.sqrt(lengthPassword) > 0)
                ? ((int) Math.sqrt(lengthPassword)) + 1
                : (int) Math.sqrt(lengthPassword);
    }
}
