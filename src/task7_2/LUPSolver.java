package task7_2;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;
import task7_1.GaussSolver;

public class LUPSolver {
    public static Matrix L;
    public static Matrix U;
    public static Matrix P;
    public static void lup(Matrix A, Matrix C) {
        int n = A.height;
        C = A;
        P = StandartMatrixOperations.identityMatrix(n);

        for (int i = 0; i < n; i++) {
            double pivotValue = 0;
            int pivot = -1;
            for (int row = i; row < n; row++) {
                if (Math.abs(C.components[row][i]) > pivotValue) {
                    pivotValue = Math.abs(C.components[row][i]);
                    pivot = row;
                }
            }
            if (pivotValue != 0) {

                StandartMatrixOperations.swapRows(P.components, pivot, i);
                StandartMatrixOperations.swapRows(C.components, pivot, i);

                for (int j = i + 1; j < n; j++) {
                    C.components[j][i] /= C.components[i][i];
                    for (int k = i + 1; k < n; k++) {
                        C.components[j][k] -= C.components[j][i] * C.components[i][k];
                    }
                }
            }
        }

        C = StandartMatrixOperations.sumMatrix(C, StandartMatrixOperations.identityMatrix(n), true);
        L = StandartMatrixOperations.identityMatrix(n);
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                if (i > j) {
                    L.components[i][j] += C.components[i][j];
                }
            }
        }
        U = StandartMatrixOperations.sumMatrix(C,L,false);
    }

    public static double[] lupSolve(Matrix A, double[] b) {
        lup(A, null);
        double[] y = GaussSolver.solveGauss(StandartMatrixOperations.multiplicationMatrix(StandartMatrixOperations.inverseMatrix(P),L), b);
        return GaussSolver.solveGauss(U, y);
    }
}
