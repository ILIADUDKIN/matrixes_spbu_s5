package task8_1;

import org.junit.Test;
import task7_0.Matrix;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.*;
import static task8_1.JustIteration.*;

public class TEST_JUST_ITERA {

    @Test
    public void testJI() throws Exception {
        int n = 5;
        Matrix A = generateData(n, 100).A;
        double[] b = generateData(n, 100).b;
        System.out.println("----------------------");
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("----------------------");
        System.out.println("Коэффициент b: \n" + Arrays.toString(b));
        System.out.println("Матрица B имеет вид: \n" + B);
        System.out.println("----------------------");
        System.out.println("Норма матрицы B: " + firstMatrixNorm(B));
        System.out.println("Вектор с имеет вид: " + Arrays.toString(c));
        double[] x = justIteration(B, c, 0.00000000000000000000000000000001, 1000000000);
        System.out.println("Ответ: \n" + Arrays.toString(x));
        System.out.println();
        System.out.println("Bx + C = " + Arrays.toString(sumVector(multiplicationMatrixVector(B, x) , c, true) ));
        System.out.println();
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("A * x = " + Arrays.toString(multiplicationMatrixVector(A, x)));
        System.out.println(answerChecker(A, b, x) ? "Ответ верен" : "Ответ неверен");
    }
}
