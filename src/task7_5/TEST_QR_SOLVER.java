package task7_5;

import org.junit.Test;
import task7_0.Matrix;
import task7_2.LUPSolver;

import java.util.Arrays;

import static java.lang.Math.abs;
import static org.junit.Assert.assertArrayEquals;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.StandartMatrixOperations.*;
import static task7_5.QRSolver.*;


public class TEST_QR_SOLVER {

    @Test
    public void test_QR_GIVENS() throws Exception {
        int n = 5;
        Matrix A = generationDataMatrix(n, 100);
        double[] b = generationDataArray(n, 100);
        Matrix A_copy = new Matrix(new double[n][n]);
        Matrix A_2copy = new Matrix(new double[n][n]);
        for (int i = 0; i < n; i++) {
            A_copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
            A_2copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
        }

        QRSolver.givensMethod(A,b);
        System.out.println("Матрица А имеет вид:\n" + A_copy);
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица Q:\n" + QRSolver.Q);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица R:\n" + QRSolver.R);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        Matrix qr = multiplicationMatrix(Q, R);
        System.out.println("Произведение матрицы Q на матрицу R:\n" + qr);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("Разница между нормами матриц: \n" + abs(FrobeniusMatrixNorm(sumMatrix(qr,A_copy,false))));

    }

    @Test
    public void test_QR_HH() throws Exception {
        int n = 5;
        Matrix A = generationDataMatrix(n, 100);
        double[] b = generationDataArray(n, 100);
        Matrix A_copy = new Matrix(new double[n][n]);
        Matrix A_2copy = new Matrix(new double[n][n]);
        for (int i = 0; i < n; i++) {
            A_copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
            A_2copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
        }

        QRSolver.houseHolderMethod(A);
        System.out.println("Матрица А имеет вид:\n" + A_copy);
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица Q:\n" + QRSolver.Q);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица R:\n" + QRSolver.R);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        Matrix qr = multiplicationMatrix(Q, R);
        System.out.println("Произведение матрицы Q на матрицу R:\n" + qr);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("Разница между нормами матриц: \n" + abs(FrobeniusMatrixNorm(sumMatrix(qr,A_copy,false))));

    }

    @Test
    public void test_HessenbergMatrix() throws Exception {
        int n = 5;

    }
}
