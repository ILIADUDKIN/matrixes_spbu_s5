package task7_3;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;
import task7_1.GaussSolver;

import java.util.Random;

import static java.lang.Math.sqrt;

public class CholeskySolver {

    public static Matrix L;

    public static void choleskyDecomposition(Matrix A) throws Exception {
        if (!StandartMatrixOperations.Silvester(A)) {
            throw new Exception("Матрица должна быть положительно-определённой.");
        }

        double[][] compL = new double[A.height][A.width];
        for (int i = 0; i < A.height; i++) {
            for (int j = 0; j < A.width; j++) {

                if (i == 0 && j == 0) {
                    compL[i][j] = sqrt(A.components[i][j]);
                }

                if (j == 0 && i > 0 ) {
                    compL[i][j] = A.components[i][j] / compL[0][0];
                }
                if (j > 0 && i == j) {
                    double sum = 0;
                    for (int p = 0; p < i; p++) {
                        sum += compL[i][p] * compL[i][p];
                    }
                    compL[i][j] = sqrt(A.components[i][j] -  sum);
                }

                if (j >= 1 && j <= A.height - 2 && i >= j + 1 && i <= A.height - 1) {
                    double sum = 0;
                    for (int p = 0; p < i; p++) {
                        sum += compL[i][p] * compL[j][p];
                    }

                    compL[i][j] = (A.components[i][j] - sum) / compL[j][j];
                }
            }
        }

        L = new Matrix(compL);
    }

    public static double[] choleskySolver(Matrix A, double[] b) throws Exception {
        choleskyDecomposition(A);
        double[] y = GaussSolver.solveGauss(L,b);
        return GaussSolver.solveGauss(StandartMatrixOperations.transpositionMatrix(L), y);
    }

    public static Matrix choleskyGeneration(int n) throws Exception {
        Matrix matrix = new Matrix(new double[n][n]);
        do {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matrix.components[i][j] = matrix.components[j][i] = rand.nextDouble();
            }
        }
        } while (!StandartMatrixOperations.Silvester(matrix));
        return matrix;
    }

}
