package task8_2;

import org.junit.Test;
import task7_0.Matrix;
import task8_1.JacobiMethod;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.*;
import static task8_2.GaussSeidelMethod.gaussSeidel;
import static task8_2.GaussSeidelMethod.generateDataSeidel;

public class TEST_GAUSS_SEIDEL {
    @Test
    public void testGSM() throws Exception {
        int n = 5;
        JacobiMethod.PairMatrixVector pairMatrixVector = generateDataSeidel(n, 100);
        Matrix A = pairMatrixVector.A;
        double[] b = pairMatrixVector.b;
        System.out.println("----------------------");
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("----------------------");
        System.out.println("Коэффициент b: \n" + Arrays.toString(b));
        System.out.println("Матрица B имеет вид: \n" + GaussSeidelMethod.B);
        System.out.println("----------------------");
        System.out.println("Норма матрицы B: " + FrobeniusMatrixNorm(GaussSeidelMethod.B));
        System.out.println("Вектор с имеет вид: " + Arrays.toString(GaussSeidelMethod.c));
        double[] x = gaussSeidel(GaussSeidelMethod.B, GaussSeidelMethod.c, 0.00001, 100000000);
        System.out.println("Ответ: \n" + Arrays.toString(x));
        System.out.println();
        System.out.println("Bx + C = " + Arrays.toString(sumVector(multiplicationMatrixVector(GaussSeidelMethod.B, x),
                GaussSeidelMethod.c, true) ));
        System.out.println();
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("A * x = " + Arrays.toString(multiplicationMatrixVector(A, x)));
        System.out.println(answerChecker(A, b, x) ? "Ответ верен" : "Ответ неверен");
    }
}
