package io.docencrypting.Crypto.Hill;


//TODO delete unused functions
class Matrix {

    private Integer[][] matrix;

    public Matrix(int rows, int cols) {
        matrix = new Integer[rows][cols];
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public Integer getElement(int row, int column) {
        return matrix[row][column];
    }

    public void setElement(int row, int column, Integer value) {
        matrix[row][column] = value;
    }

    public Matrix add(Matrix newMatrix) {
         return processPlusMinus(newMatrix, new Operation() {
             @Override
             public Integer performOperation(Integer left, Integer right) {
                 return left + right;
             }
         });
    }

    public Matrix subtract(Matrix newMatrix) {
        return processPlusMinus(newMatrix, new Operation() {
            @Override
            public Integer performOperation(Integer left, Integer right) {
                return left - right;
            }
        });
    }

    private Matrix processPlusMinus(Matrix newMatrix, Operation operation) {
        if (!this.equalsSize(newMatrix)) {
            return null;
        }
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, operation.performOperation(getElement(i, j), newMatrix.getElement(i, j)));
            }
        }
        return result;
    }

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
                result.setElement(i, j, mod(value, mod));
            }
        }
        return result;
    }

    public Matrix adjoint() {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, (int) Math.pow(-1, i + j) * getElement(i, j));
            }
        }
        return result;
    }

    public Matrix multiple(int mult) {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, getElement(i, j) * mult);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(getColumns(), getRows());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(j, i, getElement(i, j));
            }
        }
        return result;
    }



    public Matrix divide(Matrix newMatrix) {
        Matrix result = null;   //TODO fix it

        return result;
    }

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

    public static void show(Matrix mat) {
        for (int i = 0; i < mat.getRows(); i++) {
            for (int j = 0; j < mat.getColumns(); j++) {
                System.out.print(mat.getElement(i, j) % 26 + "\t");
            }
            System.out.println();
        }
    }

    public double determinant() {
        double result = 0.0;

        if (getRows() == 1 && getColumns() == 1) {
            return (double) getElement(0, 0);
        }

        for (int i = 0; i < getColumns(); i++) {
            result += Math.pow(-1, i) * getElement(0, i) * minor(0, i).determinant();
        }
        return result;
    }

    public Matrix cofactor() {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, (int) minor(i, j).determinant());
            }
        }
        return result;
    }

    private int greatestCommonDivisor(int mod) {
        double det = determinant();
        float c =  (float) ((Math.sqrt(det) < Math.sqrt(mod)) ? Math.sqrt(det) : Math.sqrt(mod));
        int count = (c - Math.round(c) > 0) ? Math.round(c) + 1 : Math.round(c);
        int gcd = 1;
        for (int i = 2; i < count; i++) {
            if (det%i==0 && mod%i==0)  {
                gcd = i;
            }
        }
        return gcd;
    }

    public boolean equalsSize(Matrix newMatrix) {
        return (getRows() == newMatrix.getRows() && getColumns() == newMatrix.getColumns());
    }

    public Matrix inverse(int mod) {
        int det = (int) determinant();
        Matrix result = this.cofactor().adjoint().transpose();
        det %= mod;
        int d = mi(mod(det, mod), mod)%mod;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, mod(result.getElement(i, j) * d, mod));
            }
        }
        return result;
    }

    public int mod(int m, int mod) {
        return (m > 0) ? m % mod : (mod + m % mod);
    }

    public int mi(int det, int mod)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = mod;
        r2 = det;
        t1 = 0;
        t2 = 1;
        while(r1 != 1 && r2 != 0)
        {
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

    private interface Operation {

        public Integer performOperation(Integer left, Integer right);

    }

//    public static void main(String[] args) {
//        Matrix firstMatrix = new Matrix(3, 3);
//        Matrix secondMatrix = new Matrix(3, 1);
//        for (int i = 0; i < firstMatrix.getRows(); i++) {
//            for (int j = 0; j < firstMatrix.getColumns(); j++) {
//                firstMatrix.setElement(i, j, (int)(Math.random()*100));
//            }
//        }
//        show(firstMatrix);
//        System.out.println("GCD: " + firstMatrix.greatestCommonDivisor(26));
//        if (firstMatrix.greatestCommonDivisor(26) > 1) {
//            System.out.println("Invalid key");
//            System.exit(1);
//        }
//        secondMatrix.setElement(0, 0, 3);
//        secondMatrix.setElement(1, 0, 14);
//        secondMatrix.setElement(2, 0, 6);
//        show(secondMatrix);
//        Matrix cipher = firstMatrix.multiple(secondMatrix, 26);
//        show(cipher);
//        Matrix result = firstMatrix.inverse(26);//.multiple(firstMatrix.determinant()%26);//.multiple(firstMatrix.determinant()%41)/*multiple(secondMatrix).byModulo(26)*/;
//        show(result);
//        Matrix decode = result.multiple(cipher, 26);
//        show(decode);
//    }
}
