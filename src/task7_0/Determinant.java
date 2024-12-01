package task7_0;

import task7_1.GaussSolver;
import task7_2.LUSolver;

public class Determinant {
    public static double detGauss(Matrix matrix) {
        if (matrix.height != matrix.width) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        double[][] matrixCopy = new double[matrix.height][matrix.width];
        for (int i = 0; i < matrix.height; i++) {
            System.arraycopy(matrix.components[i], 0, matrixCopy[i], 0, matrix.width);
        }

        double determinant = 1;
        for (int i = 0; i < matrix.height; i++) {
            int pivotRow = StandartMatrixOperations.findPivotRow(matrixCopy, i, i);
            if (matrixCopy[pivotRow][i] == 0) {
                return 0;
            }
            if (pivotRow != i) {
                determinant *= -1; // Перестановка строк меняет знак определителя
                StandartMatrixOperations.swapRows(matrixCopy, i, pivotRow);
            }
            determinant *= matrixCopy[i][i];
            GaussSolver.normalizeRow(matrixCopy, i, matrixCopy[i][i]);
            for (int j = i + 1; j < matrix.height; j++) {
                GaussSolver.eliminateElement(matrixCopy, j, i, matrixCopy[j][i]);
            }
        }

        return determinant;
    }

    public static double detLU(Matrix matrix) {
        Matrix c = new Matrix(new double[matrix.height][matrix.height]);
        LUSolver.lu(matrix, c);
        Matrix U = LUSolver.U;
        double det = 1;
        for (int i = 0; i < U.height; i++) {
            for (int j = 0; j < U.width; j++) {
                if (i == j) {
                    det *= U.components[i][j];
                }
            }
        }
        return det;
    }
}
