package io.docencrypting.Crypt.Hill;

/**
 * Class representation matrix
 */
class Matrix {

    private Integer[][] matrix;     /// Matrix

    /**
     * Create matrix with rows and columns
     * @param rows Rows in matrix
     * @param cols Columns in matrix
     */
    public Matrix(int rows, int cols) {
        matrix = new Integer[rows][cols];
    }

    /**
     * Get rows in matrix
     * @return rows
     */
    public int getRows() {
        return matrix.length;
    }

    /**
     * Get columns in matrix
     * @return columns
     */
    public int getColumns() {
        return matrix[0].length;
    }

    /**
     * Get element in position [row][column]
     * @param row Row position
     * @param column Column position
     * @return element
     */
    public Integer getElement(int row, int column) {
        return matrix[row][column];
    }

    /**
     * Set value in position [row][column]
     * @param row Row position
     * @param column Column position
     * @param value Value
     */
    public void setElement(int row, int column, Integer value) {
        matrix[row][column] = value;
    }

    /**
     * Multiple two matrix
     * @param newMatrix Matrix
     * @param mod Modulo
     * @return Result multiple matrix by modulo
     */
    public Matrix multiple(Matrix newMatrix, int mod) {
        if (getColumns() != newMatrix.getRows()) {
            return null;
        }
        Matrix result = new Matrix(getRows(), newMatrix.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Integer value = 0;
                for (int k = 0; k < getColumns(); k++) {
                    value += getElement(i, k) * newMatrix.getElement(k, j);
                }
                result.setElement(i, j, detByModulo(value, mod));
            }
        }
        return result;
    }

    /**
     * Calculate adjoint matrix
     * @return adjoint matrix
     */
    private Matrix adjoint() {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, (int) Math.pow(-1, i + j) * getElement(i, j));
            }
        }
        return result;
    }

    /**
     * Transpose matrix
     * @return transposed matrix
     */
    private Matrix transpose() {
        Matrix result = new Matrix(getColumns(), getRows());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(j, i, getElement(i, j));
            }
        }
        return result;
    }

    /**
     * Calculate minor
     * @param row Row
     * @param column Column
     * @return minor for matrix
     */
    private Matrix minor(int row, int column) {
        Matrix result = new Matrix(getRows() - 1, getColumns() - 1);
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (i == row || j == column) continue;
                int iTemp = 0, jTemp = 0;
                if (i > row) iTemp++;
                if (j > column) jTemp++;
                result.setElement(i - iTemp, j - jTemp, getElement(i, j));
            }
        }
        return result;
    }

    /**
     * Calculate determinant of matrix
     * @return determinant
     */
    private double determinant() {
        double result = 0.0;

        if (getRows() == 1 && getColumns() == 1) {
            return (double) getElement(0, 0);
        }

        for (int i = 0; i < getColumns(); i++) {
            result += Math.pow(-1, i) * getElement(0, i) * minor(0, i).determinant();
        }
        return result;
    }

    /**
     * Calculate cofactor
     * @return cofactor
     */
    private Matrix cofactor() {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, (int) minor(i, j).determinant());
            }
        }
        return result;
    }

    /**
     * Inverse matrix by module
     * @param mod Modulo
     * @return inverse matrix
     */
    public Matrix inverse(int mod) {
        int det = (int) determinant();
        Matrix result = this.cofactor().adjoint().transpose();
        det %= mod;
        int d = mi(detByModulo(det, mod), mod) % mod;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, detByModulo(result.getElement(i, j) * d, mod));
            }
        }
        return result;
    }

    /**
     * Calculate detByModulo
     * @param det Determinant
     * @param mod Modulo
     * @return determinant by modulo
     */
    private int detByModulo(int det, int mod) {
        return (det > 0) ? det % mod : (mod + det % mod);
    }

    private int mi(int det, int mod) {
        int q, r1, r2, r, t1, t2, t;
        r1 = mod;
        r2 = det;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0) {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return t1 + t2;
    }

}
