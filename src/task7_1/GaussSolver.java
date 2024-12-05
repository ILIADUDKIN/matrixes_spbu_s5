package task7_1;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

public class GaussSolver {

    public static double[] solveGauss(Matrix A, double[] b) {

        if (A.height != A.width || A.height != b.length) {
            throw new IllegalArgumentException("Матрица и вектор правых частей должны иметь одинаковую размерность");
        }

        // Расширенная матрица
        double[][] augmentedMatrix = getAugmentedMatrix(A,b);

        // Прямой ход
        for (int i = 0; i < A.height; i++) {
            int pivotRow = StandartMatrixOperations.findPivotRow(augmentedMatrix, i, i);
            if (augmentedMatrix[pivotRow][i] == 0) {
                throw new ArithmeticException("Система не имеет единственного решения");
            }
            StandartMatrixOperations.swapRows(augmentedMatrix, i, pivotRow);


            double pivot = augmentedMatrix[i][i];
            for (int j = i; j < augmentedMatrix[i].length; j++) {
                augmentedMatrix[i][j] /= pivot;
            }


            for (int j = i + 1; j < A.height; j++) {
                double factor = augmentedMatrix[j][i];
                eliminateElement(augmentedMatrix, j, i, factor);
            }
        }

        // обратный ход
        return inverseStep(A, augmentedMatrix);
    }


    public static void normalizeRow(double[][] matrix, int row, double pivot) {
        for (int j = 0; j < matrix[row].length; j++) {
            matrix[row][j] /= pivot;
        }
    }

    public static void eliminateElement(double[][] matrix, int row, int col, double value) {
        for (int j = 0; j < matrix[row].length; j++) {
            matrix[row][j] -= value * matrix[col][j];
        }
    }

    public static double[] inverseStep(Matrix A, double[][] augmentedMatrix) {
        double[] x = new double[A.height];
        for (int i = A.height - 1; i >= 0; i--) {
            x[i] = augmentedMatrix[i][A.width];
            for (int j = i + 1; j < A.height; j++) {
                x[i] -= augmentedMatrix[i][j] * x[j];
            }
        }
        return x;
    }

    public static double[][] getAugmentedMatrix(Matrix A, double[] b) {
        double[][] augmentedMatrix = new double[A.height][A.width + 1];
        for (int i = 0; i < A.height; i++) {
            System.arraycopy(A.components[i], 0, augmentedMatrix[i], 0, A.width);
            augmentedMatrix[i][A.width] = b[i];
        }
        return augmentedMatrix;
    }

    public static Matrix gilbertMatrix(int n) {
        double[][] comp = new double[n][n];
        double epsilon = 10e-6;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                comp[i][j] = (i == j) ? (double) 1 / ((i + 1) + (j + 1) - 1) + epsilon : (double) 1 / ((i + 1) + (j + 1) - 1) ;
            }
        }
        return new Matrix(comp);
    }


}
