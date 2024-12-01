package task7_4;

import org.junit.Test;
import task7_0.Matrix;
import task7_0.StandartMatrixOperations;
import task7_3.CholeskySolver;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.StandartMatrixOperations.answerChecker;
import static task7_3.CholeskySolver.choleskyDecomposition;
import static task7_4.TomasSolver.getTomasSolverExample;
import static task7_4.TomasSolver.solveTomas;

public class TEST_TOMAS_SOLVER {
    @Test
    public void test_Tomas() throws Exception {
        int n = 5;
        Matrix matrix  = getTomasSolverExample(n);
        double[] f = generationDataArray(n, 100);
        System.out.println("Пример матрицы Томаса со всеми возможными условиями \n" + matrix);
        System.out.println();
        double[] x = solveTomas(matrix, new double[n], new double[n], new double[n], f);
        System.out.println("Результат работы алгоритма прогонки со случайным коэффициентом: \n"
                + Arrays.toString(x));
        System.out.println(answerChecker(matrix, f, x) ? "Ответ верен" : "Ответ неверен");
    }
}
