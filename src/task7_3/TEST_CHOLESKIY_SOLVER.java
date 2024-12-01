package task7_3;

import org.junit.Test;
import task7_0.Matrix;
import task7_0.StandartMatrixOperations;
import task7_2.LUPSolver;
import task7_2.LUSolver;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.StandartMatrixOperations.answerChecker;
import static task7_0.StandartMatrixOperations.minor;
import static task7_3.CholeskySolver.*;

public class TEST_CHOLESKIY_SOLVER {

    @Test
    public void test_Choleskiy_decompo() throws Exception {
        int n = 5;
        Matrix A = choleskyGeneration(n);
        System.out.println(A);
        double[] b = generationDataArray(n, 100);
        choleskyDecomposition(A);
        System.out.println("Матрица А имеет вид:\n" + A);
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Его матрица L:\n" + CholeskySolver.L);
        System.out.println("________________________________");
        System.out.println();
        System.out.println("________________________________");
        double[] x = choleskySolver(A, b);
        System.out.println("Решение системы со случайным вектором b: \n" + Arrays.toString(x));
        System.out.println(answerChecker(A, b, x) ? "Ответ верен" : "Ответ неверен");
        System.out.println("________________________________");
        System.out.println("Метод Холецкого требует симметричности матрицы, а также ее положительной определенности.");
        System.out.println("Миноры матрицы: \n");
        for (int k = 1; k <= n; k++) {
            System.out.println("Минор "+ k +"-го порядка: " + minor(A, k) + "\n");
        }
    }

}
