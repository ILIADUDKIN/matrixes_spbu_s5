package task7_0;

import java.sql.SQLOutput;
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

    public static Matrix generationDiagonalMatrix(int n, double bound) {
        Random rand = new Random();
        double[][] matrix_comp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_comp[i][j] = (i == j) ? rand.nextDouble(bound) : 0;
            }
        }
        return new Matrix(matrix_comp);
    }

    public static Matrix generationDiagonalMatrixWithBigEigenValue(int n) {
        double[][] matrix_comp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix_comp[i][j] = 1000 - i;
                } else {
                    matrix_comp[i][j] = 0;
                }
            }
         }
        return new Matrix(matrix_comp);
    }

    public static Matrix generationDiagonalMatrixWithMaxValueBound(int n, double bound) {
        Random rand = new Random();
        double[][] matrix_comp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    if (i == 0)
                        matrix_comp[i][j] = bound;
                    else matrix_comp[i][j] = rand.nextDouble(bound);
                } else {
                    matrix_comp[i][j] = 0;
                }
            }
        }
        return new Matrix(matrix_comp);
    }

    public static Matrix generationMatrixWithMaxValueBound(int n, double bound) {
        Matrix m;
        do {
            m = generationDataMatrix(n, bound);
        } while (Determinant.detGauss(m) == 0);
        System.out.println(m);
        Matrix A = generationDiagonalMatrixWithMaxValueBound(n, bound);
        System.out.println("Матрица диагональная имеет вид: \n" + A);
        Matrix Am = StandartMatrixOperations.multiplicationMatrix(A, m);
        Matrix m_inverse  = StandartMatrixOperations.inverseMatrix(m);
        return StandartMatrixOperations.multiplicationMatrix(m_inverse, Am);
    }

    public static Matrix generationMatrixWithBigEigenValue(int n, double bound) {
        Matrix m;
        do {
            m = generationDataMatrix(n, bound);
        } while (Determinant.detGauss(m) == 0);
        System.out.println(m);
        Matrix A = generationDiagonalMatrixWithBigEigenValue(n);
        System.out.println("Матрица диагональная имеет вид: \n" + A);
        Matrix Am = StandartMatrixOperations.multiplicationMatrix(A, m);
        Matrix m_inverse  = StandartMatrixOperations.inverseMatrix(m);
        return StandartMatrixOperations.multiplicationMatrix(m_inverse, Am);
    }

    public static Matrix generationMatrixWithRealEigenValues(int n, double bound) {
        Matrix m;
        do {
            m = generationDataMatrix(n, bound);
        } while (Determinant.detGauss(m) == 0);
        System.out.println(m);
        Matrix A = generationDiagonalMatrix(n, bound);
        System.out.println("Матрица диагональная имеет вид: \n" + A);
        Matrix Am = StandartMatrixOperations.multiplicationMatrix(A, m);
        Matrix m_inverse  = StandartMatrixOperations.inverseMatrix(m);
        return StandartMatrixOperations.multiplicationMatrix(m_inverse, Am);
    }

    public static Matrix[] generationMatrixWithRealEigenValuesAndP(int n, double bound) {
        Matrix P;
        do {
            P = generationDataMatrix(n, bound);
        } while (Determinant.detGauss(P) == 0);
        Matrix A = generationDiagonalMatrix(n, bound);
        System.out.println("Матрица диагональная имеет вид: \n" + A);
        Matrix Am = StandartMatrixOperations.multiplicationMatrix(A, P);
        Matrix m_inverse  = StandartMatrixOperations.inverseMatrix(P);
        return new Matrix[] {StandartMatrixOperations.multiplicationMatrix(m_inverse, Am), P};
    }

    public static Matrix generationSymmetryMatrix(int n, double bound) {
        double[][] m_comp = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                m_comp[i][j] = m_comp[j][i] = random.nextDouble(bound);
            }
        }
        return new Matrix(m_comp);
    }
}
