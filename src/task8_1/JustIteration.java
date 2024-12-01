package task8_1;

import task7_0.Matrix;

import static java.lang.Math.abs;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.StandartMatrixOperations.*;

public class JustIteration {

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

    public static double[] justIteration(Matrix B, double[] c, double tolerance, int maxIterations) {
        int n = B.width;
        double[] x = new double[n];
        double[] x_new = new double[n];
        double eps = Double.MAX_VALUE;
        int iteration = 0;

        while (eps > tolerance && iteration < maxIterations) {
            System.arraycopy(x,0,x_new,0,n);
            for (int i = 0; i < n; i++) {
                x_new[i] = c[i];
                for (int j = 0; j < n; j++) {
                    x_new[i] += B.components[i][j] * x[j];
                }
            }
            eps = normVector(subtractVectors(x_new, x));
            System.arraycopy(x_new,0,x,0,n);
            iteration++;
        }

        if (iteration == maxIterations) {
            System.out.println("Метод не сошелся за " + maxIterations + " итераций.");
        }
        return x_new;
    }

    public static double[] subtractVectors(double[] a, double[] b){
        if(a.length != b.length){
            throw new IllegalArgumentException("Векторы должны быть одинаковой длины.");
        }
        double[] result = new double[a.length];
        for(int i = 0; i < a.length; i++){
            result[i] = a[i] - b[i];
        }
        return result;
    }



    public static PairMatrixVector generateData(int n, double bound) {
        Matrix A = generationDataMatrix(n, bound);
        double[] b = generationDataArray(n, bound);
        getBC(A, b);
        while (firstMatrixNorm(B) > 1) {
            A = generationDataMatrix(n, bound);
            A = sumMatrix(A, multiplicationMatrixDouble(100, identityMatrix(n)),    true);
            b = generationDataArray(n, bound);
            getBC(A, b);
        }
        return new PairMatrixVector(A, b);
    }

    static class PairMatrixVector {

        public PairMatrixVector(Matrix a, double[] b) {
            A = a;
            this.b = b;
        }

        Matrix A;
        double[] b;

        public Matrix getA() {
            return A;
        }

        public void setA(Matrix a) {
            A = a;
        }

        public double[] getB() {
            return b;
        }

        public void setB(double[] b) {
            this.b = b;
        }
    }
}
