package task7_1;

import org.junit.Test;
import task7_0.Matrix;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;
import static task7_0.DataGeneration.generationDataArray;
import static task7_0.DataGeneration.generationDataMatrix;
import static task7_0.StandartMatrixOperations.*;
import static task7_1.GaussSolver.gilbertMatrix;

public class TEST_GAUSS_SOLVER {

    @Test
    public void test()  {
        Matrix A = new Matrix(new double[][] {{97.826801,	41.998414,	50.609343,	12.433798,	-34.924921},
                {-73.302177,	-64.404949,	19.807895,	29.211111,	26.857349},
                {-23.018252,	-99.670959,	90.556130,	-93.071541,	50.149433},
                {33.468590,	83.659928,	-78.359246,	66.164907,	99.257844},
                {91.046960,	72.786687,	-99.490168,	92.090363,	54.995253},
        });
        double[] b = new double[] {15269.604896,
                -9448.144385,
                -4763.535748,
                178.108181,
                6152.954402};
        double[] x = GaussSolver.solveGauss(A,b);


        System.out.println(answerChecker(A,b,x));
        assertArrayEquals(new double[] {97.826801,
                41.998414,
                50.609343,
                12.433798,
                        -34.924921}, x, 0.01);
    }

    @Test
    public void test_nxn() {

        int n = 50;
        Matrix A = generationDataMatrix(n, 100);
        double[] b = generationDataArray(n, 100);

        System.out.println("Ответ: ");
        double[] x = GaussSolver.solveGauss(A,b);
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println(answerChecker(A,b,x) ? "Ответ верен" : "Есть ошибка");
    }

    @Test
    public void test_GilbertMatrix() {

        for (int n = 5; n <= 50; n += 5) {
            Matrix A = gilbertMatrix(n);
            double[] x = new double[n];
            Arrays.fill(x, 1);
            double[] b = multiplicationMatrixVector(A, x);
            double[] x_new = GaussSolver.solveGauss(A, b);
            System.out.println();
            System.out.println("Ответ при n = " + n + ": " + Arrays.toString(x_new));
            System.out.println();
            System.out.println("При этом число обсусловленности: " + numberOfConditionality(A));
            System.out.println("----------------------------------------------");
            System.out.println();
            System.out.println("Матрица Гильберта является плохо обусловленной матрицей и уже при n = 10 наблюдаются" +
                    "проблемы");
        }

    }

}
