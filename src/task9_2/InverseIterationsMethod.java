package task9_2;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.*;

public class InverseIterationsMethod {

    public static double[] eigenVector;


    public static double[] inverseIterationMethod(Matrix A, double approximateLambda,
                                                  double tolerance, double maxIterations) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }

        Matrix lambdaE = StandartMatrixOperations.multiplicationMatrixDouble
                (approximateLambda, StandartMatrixOperations.identityMatrix(A.width));
        Matrix C = sumMatrix(A, lambdaE, false);
        Matrix A_LambdaE_inverse = inverseMatrix(C);

        double[] x = new double[A.width];
        Arrays.fill(x, 1);

        for (int i = 0; i < maxIterations; i++) {
            double[] y = StandartMatrixOperations.multiplicationMatrixVector(A_LambdaE_inverse, x);

            double norm_y = StandartMatrixOperations.normVector(y);
            double[] tmp = new double[x.length]; ;
            if (A.width >= 0) System.arraycopy(x, 0, tmp, 0, A.width);
            for (int k = 0; k < A.width; k++) {
                x[k] = y[k] / norm_y;
            }
            if (normVector(sumVector(tmp,x,false)) < tolerance) {
                System.out.println("Метод сошелся за " + (i + 1) + " итераций.");
                return x;
            }



        }
        System.out.println("Метод не сошелся за заданное число итераций.");
        return null;
    }

    public static double modificationInverseMethod(Matrix A, double tolerance, double maxIterations) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }

        double eigenvalue = 0;
        double previousEigenvalue = Double.MAX_VALUE;


        double[] x = new double[A.width];
        Arrays.fill(x, 1);

        for (int i = 0; i < maxIterations; i++) {
            double[] Ax = StandartMatrixOperations.multiplicationMatrixVector(A,x);
            eigenvalue = StandartMatrixOperations.scalarMultiplication(Ax, x);

            Matrix lambdaE = StandartMatrixOperations.multiplicationMatrixDouble
                    (eigenvalue, StandartMatrixOperations.identityMatrix(A.width));
            Matrix C = sumMatrix(A, lambdaE, false);
            Matrix A_LambdaE_inverse = inverseMatrix(C);
            double[] y = StandartMatrixOperations.multiplicationMatrixVector(A_LambdaE_inverse, x);

            double norm_y = StandartMatrixOperations.normVector(y);

            for (int k = 0; k < A.width; k++) {
                x[k] = y[k] / norm_y;
            }
            if (Math.abs(eigenvalue - previousEigenvalue) < tolerance) {
                System.out.println("Метод сошелся за " + (i + 1) + " итераций.");
                eigenVector = x;
                return eigenvalue;
            }
            previousEigenvalue = eigenvalue;



        }
        System.out.println("Метод не сошелся за заданное число итераций.");
        return 0;
    }
}
