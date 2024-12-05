package task7_0;

import static java.lang.Math.pow;

public class StandartMatrixOperations {

    public static double[] multiplicationMatrixVector(Matrix a, double[] b) {
        if (a.width != b.length) {
            throw new IllegalArgumentException("Ширина матрицы должна совпадать с размеров массива b");
        }
        double[] result = new double[b.length];
        for (int i = 0; i < a.height; i++) {
            for (int j = 0; j < a.width; j++) {
                result[i] += a.components[i][j] * b[j];
            }
        }
        return result;
    }

    public static double[] sumVector(double[] a, double[] b, boolean sign) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (sign) ? a[i] + b[i] : a[i] - b[i];
        }
        return a;
    }

    public static Matrix sumMatrix(Matrix a, Matrix b, boolean operation) {
        double[][] components = new double[a.components.length][a.components[0].length];
        for (int i = 0; i < a.components.length; i++) {
            for (int j = 0; j < a.components[0].length; j++) {
                if (operation) {components[i][j] = a.components[i][j] + b.components[i][j];}
                else {components[i][j] = a.components[i][j] - b.components[i][j];}

            }
        }
        return new Matrix(components);
    }

    public static Matrix multiplicationMatrix(Matrix a, Matrix b) {
        double[][] components = new double[a.components.length][b.components[0].length];
        for (int i = 0; i < a.components.length; i++) {
            for (int j = 0; j < b.components[0].length; j++) {
                double currentSum = 0;
                for (int k = 0; k < a.components[i].length; k++) {
                    currentSum += a.components[i][k] * b.components[k][j];
                }
                components[i][j] = currentSum;
            }
        }
        return new Matrix(components);
    }

    public static Matrix transpositionMatrix(Matrix a) {
        double[][] components = new double[a.components[0].length][a.components.length];
        for (int i = 0; i < a.components[0].length; i++) {
            for (int j = 0; j < a.components.length; j++) {
                components[i][j] = a.components[j][i];
            }
        }
        return new Matrix(components);
    }

    public static Matrix inverseMatrix(Matrix matrix) throws IllegalArgumentException {
        if (matrix.components.length != matrix.components[0].length) {
            throw new IllegalArgumentException("Матрица должна быть квадратной.");
        }

        int n = matrix.components.length;
        double[][] inverse = new double[n][n];

        double[][] extendedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extendedMatrix[i][j] = matrix.components[i][j];
            }
            extendedMatrix[i][i + n] = 1; // Единичная матрица
        }

        for (int i = 0; i < n; i++) {

            int pivotRow = findPivotRow(extendedMatrix, i, i);

            swapRows(extendedMatrix, i, pivotRow);

            double pivotValue = extendedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                extendedMatrix[i][j] /= pivotValue;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = extendedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        extendedMatrix[k][j] -= factor * extendedMatrix[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(extendedMatrix[i], n, inverse[i], 0, n);
        }

        return new Matrix(inverse);
    }

    public static int findPivotRow(double[][] matrix, int col, int startRow) {
        int pivotRow = startRow;
        for (int i = startRow + 1; i < matrix.length; i++) {
            if (Math.abs(matrix[i][col]) > Math.abs(matrix[pivotRow][col])) {
                pivotRow = i;
            }
        }
        return pivotRow;
    }

    public static double[][] swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
        return matrix;
    }


    public static double firstMatrixNorm(Matrix matrix) {
        double staticMax = 0;
        for (int j = 0; j  < matrix.width; j++) {
            double currentMax = 0;
            for (int i = 0; i < matrix.height; i++) {
                currentMax += Math.abs(matrix.components[i][j]);
            }
            if (currentMax > staticMax) {
                staticMax = currentMax;
            }
        }
        return staticMax;
    }

    public static double FrobeniusMatrixNorm(Matrix matrix) {
        double sum = 0;
        for (int i = 0; i < matrix.height; i++) {
            for (int j = 0; j < matrix.width; j++) {
                sum += Math.abs(matrix.components[i][j] * matrix.components[i][j]);
            }
        }
        return Math.sqrt(sum);
    }

    public static double numberOfConditionality(Matrix matrix) {
        Matrix inverseMatrix = inverseMatrix(matrix);
        return FrobeniusMatrixNorm(inverseMatrix) * FrobeniusMatrixNorm(matrix);
    }

    public static Matrix identityMatrix(int n) {
        double[][] comp_identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    comp_identity[i][j] = 1;
                }
                else {
                    comp_identity[i][j] = 0;
                }
            }
        }
        return new Matrix(comp_identity);
    }

    /**
     * Следующий метод проверяет симметрическую матрицу на положительную определенность.
     * @param matrix - матрица
     * @return целое значение
     */
    public static boolean Silvester(Matrix matrix) throws Exception {
        boolean isPositiveDifinite = true;

        for (int i = 0; i < matrix.height; i++) {
            for (int j = 0; j < matrix.width; j++) {
                if (i != j && matrix.components[i][j] != matrix.components[j][i]) {
                    throw new Exception("Матрица не является симметрической. Сделаете матрицу симметрической. ");
                }
            }
        }

        double[] minors = new double[matrix.height];
        for (int i = 0; i < matrix.height; i++) {
            minors[i] = minor(matrix, i+1);
            if (minors[i] <= 0) {
                isPositiveDifinite = false;
            }
        }

        return isPositiveDifinite;
    }


    public static double minor(Matrix matrix , int n) throws Exception {
        if (matrix.height != matrix.width) {
            throw new Exception("Матрица должна быть квадратной");
        }
        if (n > matrix.height) {
            throw new Exception("Размерность минора не должна превосходить размерность матрицы.");
        }
        if (n == 0) {
            throw new Exception("n >= 1");
        }
        double[][] minor_comp = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix.components[i], 0, minor_comp[i], 0, n);
        }

        Matrix minorMatrix = new Matrix(minor_comp);
        return Determinant.detLU(minorMatrix);
    }

    public static Matrix multiplicationVecVec(double[] vec_1, double[] vec_2) {
        Matrix a = new Matrix(new double[vec_1.length][vec_2.length]);
        for (int i = 0; i < vec_1.length; i++) {
            for (int j = 0; j < vec_2.length; j++) {
                a.components[i][j] = vec_1[i] * vec_2[j];
            }
        }
        return a;
    }

    public static Matrix multiplicationMatrixDouble(double C, Matrix a) {
        for (int i = 0; i < a.height; i++) {
            for (int j = 0; j < a.width; j++) {
                a.components[i][j] *= C;
            }
        }
        return a;
    }

    public static double[] multiplicationVectorDouble(double C, double[] vec) {
        double[] vec_2 = new double[vec.length];
        for (int i = 0; i < vec.length; i++) {
            vec_2[i] = vec[i] * C;
        }
        return vec_2;
    }

    public static double scalarMultiplication(double[] vec1, double[] vec2) {
        double sum = 0;
        for (int i = 0; i < vec1.length; i++) {
            sum += vec1[i] * vec2[i];
        }
        return sum;
    }
    public static double normVector(double[] v) {
        double norm_v = 0;
        for (double value : v) {
            norm_v += value * value;
        }
        norm_v  = Math.sqrt(norm_v);
        return norm_v;
    }


    public static boolean answerChecker(Matrix A, double[] b, double[] x) {
        boolean flag = true;
        double[] b_prev = multiplicationMatrixVector(A, x);
        for (int i = 0; i < b.length; i++) {
            System.out.println();
            if (Math.abs(b_prev[i] - b[i]) >= pow(10, -3)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
