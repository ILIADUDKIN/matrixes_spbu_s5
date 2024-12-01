package task7_2;

import org.junit.Test;
import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.Determinant.detLU;
import static task7_0.StandartMatrixOperations.*;
import static task7_2.LUPSolver.lupSolve;
import static task7_2.LUSolver.getRightSide;
import static task7_2.LUSolver.luSolve;

public class TEST_LU_SOLVER {

    @Test
    public void test_LU() {
        int n = 5;
        Matrix A = generationDataMatrix(n, 100);
        double[] b = generationDataArray(n, 100);
        Matrix A_copy = new Matrix(new double[n][n]);
        Matrix A_2copy = new Matrix(new double[n][n]);
        for (int i = 0; i < n; i++) {
            A_copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
            A_2copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
        }

        Matrix C = new Matrix(new double[n][n]);
        LUSolver.lu(A, C);
        System.out.println("Матрица А имеет вид:\n" + A_copy);
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица L:\n" + LUSolver.L);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица U:\n" + LUSolver.U);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Произведение матрицы L на матрицу U:\n" + multiplicationMatrix(LUSolver.L, LUSolver.U));
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Определитель такой матрицы: " + detLU(A));
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println();
        double[] x = luSolve(A_copy, C, b);
        System.out.println("Решение системы методом LU:\n" + Arrays.toString(x));
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println(answerChecker(A_2copy, b, x) ? "Ответ верен" : "Ответ неверен");
    }

    @Test
    public void test_LUP() {
        int n = 5;
        Matrix A = generationDataMatrix(n, 100);
        double[] b = generationDataArray(n, 100);
        Matrix A_copy = new Matrix(new double[n][n]);
        Matrix A_2copy = new Matrix(new double[n][n]);
        for (int i = 0; i < n; i++) {
            A_copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
            A_2copy.components[i] = Arrays.copyOf(A.components[i], A.components[i].length);
        }

        Matrix C = new Matrix(new double[n][n]);
        LUPSolver.lup(A, C);
        System.out.println("Матрица А имеет вид:\n" + A_copy);
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица L:\n" + LUPSolver.L);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица U:\n" + LUPSolver.U);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица P:\n" + LUPSolver.P);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Произведение матрицы L на матрицу U и на P :\n"
                + multiplicationMatrix(inverseMatrix(LUPSolver.P), multiplicationMatrix(LUPSolver.L, LUPSolver.U)));
        System.out.println("________________________________");
        System.out.println();
        double[] x = lupSolve(A_copy, b);
        System.out.println("________________________________");
        System.out.println("Решение системы методом LUP:\n" + Arrays.toString(x));
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        System.out.println(answerChecker(A_2copy, b, x) ? "Ответ верен" : "Ответ неверен");

    }

    @Test
    public void test_RO() {
        int countOfIterations = 10;
        int n = 5;
        Matrix A = generationDataMatrix(n, 100);
        System.out.println("Матрица А имеет следующий вид:\n" + A);
        System.out.println("________________________________");
        System.out.println();

        for (int i = 0; i < countOfIterations; i++) {
            Matrix A_copy = new Matrix(new double[n][n]);
            for (int j = 0; j < n; j++) {
                A_copy.components[j] = Arrays.copyOf(A.components[j], A.components[j].length);
            }
            double[] b = getRightSide(n);
            double[] x = luSolve(A_copy, new Matrix(new double[n][n]), b);
            System.out.println((i + 1) + "-ый набор коэффициентов: \n" + Arrays.toString(b));
            System.out.println();
            System.out.println("________________________________");
            System.out.println("Ответ: " + Arrays.toString(x));
            System.out.println();

        }
    }

}
