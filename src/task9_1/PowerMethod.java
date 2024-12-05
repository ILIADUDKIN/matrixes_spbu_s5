package task9_1;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

import java.util.Arrays;

import static task7_0.DataGeneration.generationDataArray;

/**
 * Степенной метод - используется для разреженных матриц.
 */
public class PowerMethod {

    public static double[] eigenVector1;
    public static double[] eigenVector2;
    public static double powerMethod(Matrix A, double maxIterations, double tolerance) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }
        double eigenvalue = 0;
        double previousEigenvalue = Double.MAX_VALUE;
        double[] x = new double[A.width];
        Arrays.fill(x, 1);

        for (int i = 0; i < maxIterations; i++) {
            double[] y = StandartMatrixOperations.multiplicationMatrixVector(A, x);
            eigenvalue = StandartMatrixOperations.scalarMultiplication(y, x) /
                    StandartMatrixOperations.scalarMultiplication(x, x);
            System.arraycopy(y, 0, x, 0, y.length);
            if (Math.abs(eigenvalue - previousEigenvalue) < tolerance) {
                System.out.println("Метод 1 сошелся за " + (i + 1) + " итераций.");
                eigenVector1 = x;
                return eigenvalue;
            }
            previousEigenvalue = eigenvalue;
        }
        System.out.println("Метод не сошелся за заданное число итераций.");
        return 0;
    }

    public static double powerMethod2(Matrix A, double maxIterations, double tolerance) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }
        double eigenvalue = 0;
        double previousEigenvalue = Double.MAX_VALUE;
        double[] x = new double[A.width];
        Arrays.fill(x, 1);

        for (int i = 0; i < maxIterations; i++) {
            double[] y = StandartMatrixOperations.multiplicationMatrixVector(A, x);
            eigenvalue = StandartMatrixOperations.scalarMultiplication(y, x);
            double norm_y = StandartMatrixOperations.normVector(y);
            for (int k = 0; k < A.width; k++) {
                x[k] = y[k] / norm_y;
            }
            if (Math.abs(eigenvalue - previousEigenvalue) < tolerance) {
                System.out.println("Метод 2 сошелся за " + (i + 1) + " итераций.");
                eigenVector2 = x;
                return eigenvalue;
            }
            previousEigenvalue = eigenvalue;
        }
        System.out.println("Метод не сошелся за заданное число итераций.");
        return 0;
    }



}
