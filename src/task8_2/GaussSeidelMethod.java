package task8_2;

import task7_0.Matrix;

public class GaussSeidelMethod {
    public static Matrix B;
    public static double[] c;

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
    }
}
