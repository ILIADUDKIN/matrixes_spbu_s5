package task7_2;

import task7_0.Matrix;
import task7_1.GaussSolver;

import java.util.Random;

public class LUSolver {
    public static Matrix L;
    public static Matrix U;
    public static void lu(Matrix A, Matrix C) {
        C = A;
        // Задание матриц U и L
        double[][] componentsU = C.components;
        double[][] componentsL = new double[componentsU.length][componentsU[0].length];

        for (int i = 0; i < componentsU.length; i++) {
            for (int j = i; j < componentsU[i].length; j++) {
                componentsL[j][i] = componentsU[j][i] / componentsU[i][i];
            }
        }

        // идем по строчками и столбцам
        for (int k = 1; k < A.components.length; k++) {

            for (int i = k - 1; i < A.components.length; i++) {
                for (int j = i; j < A.components.length; j++) {
                    componentsL[j][i] = componentsU[j][i] / componentsU[i][i];
                }
            }

            for (int i = k; i < A.components.length; i++) {
                for (int j = k - 1; j < A.components.length; j++) {
                    componentsU[i][j] = componentsU[i][j] - componentsL[i][k-1] * componentsU[k-1][j];
                }
            }
        }
        L = new Matrix(componentsL);
        U = new Matrix(componentsU);
    }

    public static double[] luSolve(Matrix A, Matrix C, double[] b) {
        lu(A, C);
        double[] y = GaussSolver.solveGauss(L, b);
        return GaussSolver.solveGauss(U, y);
    }

    public static double[] getRightSide(int n) {
        Random random = new Random();
        double[] rs = new double[n];
        int a = - 10;
        int b = 10;
        for (int i = 0; i < n; i++) {
            rs[i] = a + random.nextDouble() * (b - a);
        }
        return rs;
    }
}
