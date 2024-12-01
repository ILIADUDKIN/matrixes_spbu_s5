package task7_4;

import task7_0.Matrix;

import java.util.Arrays;
import java.util.Random;

public class TomasSolver {

    public static boolean isThreeDiagonal(Matrix matrix) {

        int rows = matrix.components.length;
        int cols = matrix.components[0].length;

        if (rows != cols) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Math.abs(i - j) > 1 && matrix.components[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Matrix getTomasSolverExample(int n) {
        Random rand = new Random();
        Matrix matrix = new Matrix(new double[n][n]);
        do {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Math.abs(i - j) <= 1) {
                        matrix.components[i][j] = rand.nextDouble(100);
                    } else {
                        matrix.components[i][j] = 0;
                    }
                }
            }
        } while (!isDiagonalPredominance(matrix));
        return matrix;
    }

    public static void extractDiagonals(Matrix A, double[] a, double[] b, double[] c) {
        int n = A.components.length;

        if (n != A.components[0].length) {
            throw new IllegalArgumentException("Матрица не квадратная");
        }


        for (int i = 0; i < n; i++) {

            b[i] = A.components[i][i];
            if (i < n - 1) {
                c[i] = A.components[i][i + 1];
            } else {
                c[i] = 0;
            }


            if (i > 0) {
                a[i] = A.components[i][i - 1];
            } else {
                a[i] = 0;
            }
        }
    }

    public static double[] solveTomas(Matrix A, double[] a, double[] b, double[] c, double[] f) {
        if (!isThreeDiagonal(A)) {
            throw new IllegalArgumentException("Матрица должна быть трехдиагональной");
        }
        if (!isDiagonalPredominance(A)) {
            throw new IllegalArgumentException("У матрицы должно бють диагональное преобладание.");
        }
        extractDiagonals(A, a, b, c);

        int n = a.length;
        double[] p = new double[n];
        double[] q = new double[n];
        double[] x = new double[n];


        for (double v : b) {
            if (v == 0) {
                throw new IllegalArgumentException("Главная диагональ матрицы не может содержать нулевые элементы");
            }
        }

        // Прямая прогонка
        p[0] = -c[0] / b[0];
        q[0] = f[0] / b[0];
        for (int i = 1; i < n; i++) {
            p[i] = -c[i] / (b[i] + a[i] * p[i - 1]);
            q[i] = (f[i] - a[i] * q[i - 1]) / (b[i] + a[i] * p[i - 1]);
        }


        // Обратная прогонка
        x[n - 1] = q[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = q[i] + p[i] * x[i + 1];
        }

        return x;
    }

    public static boolean isDiagonalPredominance(Matrix A) {
        int n = A.components.length;
        if (!isThreeDiagonal(A)) {
            throw new IllegalArgumentException("Матрица должна быть трёхдиагональной.");
        }
        double[] a = new double[n];
        double[] b = new double[n];
        double[] c = new double[n];
        extractDiagonals(A, a, b, c);
        for (int i = 0; i < n; i++) {
            if (Math.abs(b[i]) < Math.abs(a[i]) + Math.abs(c[i]) || Math.abs(b[i]) <= Math.abs(a[i])) {
                return false;
            }
        }
        return true;
    }




}
