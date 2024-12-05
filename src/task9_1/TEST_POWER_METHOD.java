package task9_1;

import org.junit.Test;
import task7_0.DataGeneration;
import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.normVector;
import static task9_1.PowerMethod.eigenVector1;
import static task9_1.PowerMethod.eigenVector2;

public class TEST_POWER_METHOD {

    @Test
    public void testPowerMethod() {
        int n = 5;
        Matrix A = DataGeneration.generationMatrixWithRealEigenValues(n, 10);
        System.out.println("Матрица A имеет вид: \n" + A);
        double lambda = PowerMethod.powerMethod(A, 100000, 10e-6);
        System.out.println("Максимальное собственное число имеет вид: " + lambda);
        System.out.println("Собственный вектор имеет вид: \n" + Arrays.toString(eigenVector1));

        System.out.println("Умножение А на x: \n" + Arrays.toString(StandartMatrixOperations.multiplicationMatrixVector(A, eigenVector1)));
        System.out.println("Умножение Lambda на x: \n" + Arrays.toString(StandartMatrixOperations.multiplicationVectorDouble(lambda, eigenVector1)));
    }

    @Test
    public void testPowerMethod2() {
        int n = 5;
        Matrix A = DataGeneration.generationMatrixWithRealEigenValues(n, 10);
        System.out.println("Матрица А имеет вид: \n" + A);
        double lambda = PowerMethod.powerMethod2(A, 100000, 10e-6);
        System.out.println("Максимальное собственное число имеет вид: " + lambda);
        System.out.println("Собственный вектор имеет вид: \n" + Arrays.toString(eigenVector2));
        System.out.println("Умножение А на x: \n"
                + Arrays.toString(StandartMatrixOperations.multiplicationMatrixVector(A, eigenVector2)));
        System.out.println("Умножение Lambda на x: \n"
                + Arrays.toString(StandartMatrixOperations.multiplicationVectorDouble(lambda,
                eigenVector2)));
        System.out.println("Собственный вектор имеет вид: \n" + Arrays.toString(eigenVector2));
        System.out.println("Норма собственного вектора: \n"
                + normVector(eigenVector2));
    }

    @Test
    public void testTheory() {
        int n = 5;
        Matrix A = DataGeneration.generationMatrixWithBigEigenValue(n, 10);
        System.out.println("Матрица А имеет вид: \n" + A);
        System.out.println("Находим первым методом: \n");
        double lambda = PowerMethod.powerMethod(A, 10000000, 10e-6);
        System.out.println("Максимальное собственное число имеет вид: " + lambda);
        System.out.println("А собственный вектор: \n" + Arrays.toString(eigenVector1));
        System.out.println("Находим вторым методом: \n");
        lambda = PowerMethod.powerMethod2(A, 100000, 10e-6);
        System.out.println("Максимальное собственное число имеет вид: " + lambda);
        System.out.println("А собственный вектор: \n" + Arrays.toString(eigenVector2));
    }
}
