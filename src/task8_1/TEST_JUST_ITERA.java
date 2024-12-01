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
        PairMatrixVector pairMatrixVector = generateData(n, 100);
        Matrix A = pairMatrixVector.A;
        double[] b = pairMatrixVector.b;
        System.out.println("----------------------");
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("----------------------");
        System.out.println("Коэффициент b: \n" + Arrays.toString(b));
        System.out.println("Матрица B имеет вид: \n" + B);
        System.out.println("----------------------");
        System.out.println("Норма матрицы B: " + firstMatrixNorm(B));
        System.out.println("Вектор с имеет вид: " + Arrays.toString(c));
        double[] x = justIteration(B, c, 0.00001, 100000000);
        System.out.println("Ответ: \n" + Arrays.toString(x));
        System.out.println();
        System.out.println("Bx + C = " + Arrays.toString(sumVector(multiplicationMatrixVector(B, x) , c, true) ));
        System.out.println();
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("A * x = " + Arrays.toString(multiplicationMatrixVector(A, x)));
        System.out.println(answerChecker(A, b, x) ? "Ответ верен" : "Ответ неверен");
    }

    @Test
    public void testGetBC() throws Exception {
        Matrix A = new Matrix(new double[][]{{10,1,-1}, {1, 10, -1}, {-1, 1, 10}});
        double[] b = new double[]{11, 10, 11};
        getBC(A, b);
        System.out.println("B: " + B);
        System.out.println("Norm of B: " + FrobeniusMatrixNorm(B));
        System.out.println("c: " + Arrays.toString(c));
        double[] x = justIteration(B, c, 0.00000001, 1000000000);
        System.out.println("Ответ: \n" + Arrays.toString(x));
        System.out.println();
        System.out.println("Bx + C = " + Arrays.toString(sumVector(multiplicationMatrixVector(B, x) , c, true) ));
        System.out.println();
        System.out.println("Матрица A имеет вид: \n" + A);
        System.out.println("A * x = " + Arrays.toString(multiplicationMatrixVector(A, x)));
        System.out.println(answerChecker(A, b, x) ? "Ответ верен" : "Ответ неверен");

    }
}
