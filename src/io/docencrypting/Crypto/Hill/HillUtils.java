package io.docencrypting.Crypto.Hill;

/**
 * Utilities for hill cipher
 */
class HillUtils {

    /**
     * Calculate number of blocks
     * @param length Length of string
     * @param sideSize Size of side
     * @return number of blocks
     */
    public static int calculateNumberOfBlocks(int length, int sideSize) {
        if (length < sideSize) {
            return 1;
        } else if (length % sideSize == 0) {
            return length / sideSize;
        } else {
            return length / sideSize + 1;
        }
    }

    /**
     * Get side size of matrix
     * @param length Length of matrix side
     * @return
     */
    public static int getSideSizeOfMatrix(int length) {
        return (Math.sqrt(length) - (int) Math.sqrt(length) > 0)
                ? ((int) Math.sqrt(length)) + 1
                : (int) Math.sqrt(length);
    }
}
