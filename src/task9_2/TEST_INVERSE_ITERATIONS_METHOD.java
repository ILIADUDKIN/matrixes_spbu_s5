package task9_2;

import org.junit.Test;
import task7_0.DataGeneration;
import task7_0.Matrix;
import task7_0.StandartMatrixOperations;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.normVector;
import static task9_2.InverseIterationsMethod.eigenVector;


public class TEST_INVERSE_ITERATIONS_METHOD {

    @Test
    public void testInverseIterations() {
        int n = 5;
        double bound = 10;
        Matrix A = DataGeneration.generationMatrixWithMaxValueBound(n, bound);
        System.out.println("Матрица A имеет вид: \n" + A);
        double[] x = InverseIterationsMethod.inverseIterationMethod(A, bound - 10e-3,10e-9, 100000);
        System.out.println("Собственный вектор матрицы: \n" + Arrays.toString(x));
        System.out.println("Lambda * x = \n" + Arrays.toString(StandartMatrixOperations.multiplicationVectorDouble(bound, x)));
        System.out.println("A * x = \n" + Arrays.toString(StandartMatrixOperations.multiplicationMatrixVector(A, x)));
    }

    @Test
    public void testModificationInverseIterations() {
        int n = 5;
        double bound = 10;
        System.out.println("Метод для проверке модифицированного метода с отношением Рэлея.");
        Matrix A = DataGeneration.generationSymmetryMatrix(n, bound);
        System.out.println("Матрица A имеет вид: \n" + A);
        double lambda = InverseIterationsMethod.modificationInverseMethod(A, 10e-9, 100000);
        System.out.println("Собственное число матрицы: \n" + lambda);
        System.out.println("Собственный вектор матрицы: \n" + Arrays.toString(eigenVector));
        System.out.println("Норма собственного вектора матрицы: " + normVector(eigenVector));
        System.out.println("Lambda * x = \n" + Arrays.toString(StandartMatrixOperations.multiplicationVectorDouble(lambda, eigenVector)));
        System.out.println("A * x = \n" + Arrays.toString(StandartMatrixOperations.multiplicationMatrixVector(A, eigenVector)));
    }
}
