package task9_3;

import org.junit.Test;
import task7_0.DataGeneration;
import task7_0.HessenbergTransformation;
import task7_0.Matrix;
import task7_5.QRSolver;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.minor;
import static task9_3.QRMethod.qrMethod;

public class TEST_QR_METHOD {

    @Test
    public void test() throws Exception {
        int n = 5;
        double bound = 10;
        Matrix[] mar = DataGeneration.generationMatrixWithRealEigenValuesAndP(n, bound);
        Matrix A = mar[0];
        Matrix P = mar[1];
        System.out.println("Матрица A имеет вид: \n" + A);
        Matrix A_copy = qrMethod(A, 10000);
        System.out.println("Матрица A_copy: \n" + A_copy);
        double[] minors = new double[P.height];
        System.out.println("Миноры матрицы P для проверки Критерия Уилкинсона");
        for (int i = 0; i < P.height; i++) {
            minors[i] = minor(P, i+1);
            System.out.println("minor: " + minors[i]);
        }
        double[] eigenValues = new double[n];
        for (int i = 0; i < n; i++) {
            eigenValues[i] = A_copy.components[i][i];
        }
        double maxEigenValue = Arrays.stream(eigenValues).max().getAsDouble();
        System.out.println("maxEigenValue: " + maxEigenValue);
    }

    @Test
    public void testModification() throws Exception {
        int n = 5;
        double bound = 10;
        Matrix[] mar = DataGeneration.generationMatrixWithRealEigenValuesAndP(n, bound);
        Matrix A = mar[0];
        Matrix P = mar[1];
        System.out.println("Матрица A имеет вид: \n" + A);
        HessenbergTransformation transformer = new HessenbergTransformation(A);
        Matrix hessenbergMatrix = transformer.transform();
        System.out.println("Матрица Хессенберга имеет вид: ");
        System.out.println(hessenbergMatrix);
        Matrix A_copy = qrMethod(hessenbergMatrix, 10000);
        System.out.println("Матрица A_copy: \n" + A_copy);
        double[] minors = new double[P.height];
        System.out.println("Миноры матрицы P для проверки Критерия Уилкинсона");
        for (int i = 0; i < P.height; i++) {
            minors[i] = minor(P, i+1);
            System.out.println("minor: " + minors[i]);
        }
        double[] eigenValues = new double[n];
        for (int i = 0; i < n; i++) {
            eigenValues[i] = A_copy.components[i][i];
        }
        double maxEigenValue = Arrays.stream(eigenValues).max().getAsDouble();
        System.out.println("maxEigenValue: " + maxEigenValue);







    }
}
