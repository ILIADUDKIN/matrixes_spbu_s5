package task8_2;

import task7_0.Matrix;
import task8_1.JacobiMethod;

import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.StandartMatrixOperations.*;
import static task8_1.JacobiMethod.subtractVectors;

public class GaussSeidelMethod {
    public static Matrix B;
    public static double[] c;
    public static Matrix B1;
    public static Matrix B2;

    public static void getBC(Matrix A, double[] b) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }
        double[][] b_comp = new double[A.height][A.width];
        c = new double[A.height];
        for (int i = 0; i < A.height; i++) {
            for (int j = 0; j < A.width; j++) {
                if (i == j && A.components[i][j] == 0) {
                    throw new IllegalArgumentException("Элементы на диагонале не должны быть нулевыми");
                }
                if (i != j) {
                    b_comp[i][j] = -A.components[i][j] / A.components[i][i];
                }
                if (i == j) {
                    b_comp[i][j] = 0;
                }
            }
            c[i] = b[i] / A.components[i][i];
        }
        B = new Matrix(b_comp);
        getB1B2(B.width);
    }

    public static JacobiMethod.PairMatrixVector generateDataSeidel(int n, double bound) {
        Matrix A = generationDataMatrix(n, bound);
        double[] b = generationDataArray(n, bound);
        getBC(A, b);
        while (FrobeniusMatrixNorm(B1) + FrobeniusMatrixNorm(B2) > 1) {
            A = generationDataMatrix(n, bound);
            A = sumMatrix(A, multiplicationMatrixDouble(400, identityMatrix(n)), true);
            b = generationDataArray(n, bound);
            getBC(A, b);
        }
        return new JacobiMethod.PairMatrixVector(A, b);
    }
    public static double[] gaussSeidel(Matrix B, double[] c, double tolerance, int maxIterations) {
        int n = B.width;
        double[] x = new double[n];
        double[] x_new = new double[n];
        double eps = Double.MAX_VALUE;
        int iteration = 0;

        while (eps > ((1 - FrobeniusMatrixNorm(B)) / (FrobeniusMatrixNorm(B2))) * tolerance && iteration < maxIterations) {
            System.arraycopy(x, 0, x_new, 0, n);

            for (int i = 0; i < n; i++) {
                x_new[i] = c[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        x_new[i] += B.components[i][j] * (j < i ? x_new[j] : x[j]);
                    }
                }
            }

            eps = normVector(subtractVectors(x_new, x));
            System.arraycopy(x_new, 0, x, 0, n);
            iteration++;
        }

        if (iteration == maxIterations) {
            System.out.println("Метод не сошелся за " + maxIterations + " итераций.");
        }
        return x_new;
    }

    public static void getB1B2(int n) {
        B1 = new Matrix(new double[n][n]);
        B2 = new Matrix(new double[n][n]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    B1.components[i][j] = B.components[i][j];
                    B2.components[i][j] = 0;
                }
                else if (i == j) {
                    B1.components[i][j] = 0;
                    B2.components[i][j] = 0;
                }
                else {
                    B1.components[i][j] = 0;
                    B2.components[i][j] = B.components[i][j];
                }
            }
        }
    }

}
