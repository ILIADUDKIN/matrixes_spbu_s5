package task7_0;

import java.util.Random;

public class DataGeneration {

    public static Matrix generationDataMatrix(int n, double bound) {
        Random rand = new Random();
        double[][] matrix_comp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_comp[i][j] = rand.nextDouble(bound);
            }
        }
        return new Matrix(matrix_comp);
    }

    public static double[] generationDataArray(int n, double bound) {
        Random rand = new Random();
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = rand.nextDouble(bound);
        }
        return b;
    }
}
