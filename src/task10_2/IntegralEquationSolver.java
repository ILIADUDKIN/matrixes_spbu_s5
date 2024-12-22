package task10_2;

import task7_0.Matrix;

import java.util.Arrays;

import static java.lang.Math.*;
import static task10_1.ODESolver.inputN;
import static task7_0.StandartMatrixOperations.normVector;
import static task7_0.StandartMatrixOperations.sumVector;
import static task7_1.GaussSolver.solveGauss;

public class IntegralEquationSolver {
    /*
        Вариант : u(x) + 3 \int _{0}^{pi/2} {sin(x-2s)u(s)ds} =  2
        eps = 10^(-4)
        K(x,s) = sin(x-2s)
        a = 0; b = pi/2;
        f(x) = 2;
        lambda = -3;
     */
    public static double[] solveIntegralEquation() throws Exception {
        double[] u = null;
        double[] trueSolution = null;// решения системы
        double[] x; // вектор-сетка
        double epsilon = 10e-1;
        double a = 0;
        double b = PI/2;


        double epsCurrent = 100;
        int iteration = 1;
        while (abs(epsCurrent) > epsilon) {
            int n = (int) pow(2, iteration);
            iteration += 1;
            double h = (b - a) / n;
            double lambda = -3;
            u = new double[n+1];
            x = new double[n+1];
            for (int i = 0; i < n+1; i++) {
                x[i] = a + h * i;
            }

            // реальное решение
            trueSolution = new double[n+1];
            for (int i = 0; i < n+1; i++) {
                trueSolution[i] = 2 - 3 * sin(x[i]);
            }


            Matrix A = new Matrix(new double[n + 1][n + 1]);
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    A.components[i][j] = (i == j ? 1 : 0) - lambda * simpsonCoefficient(j, n, h) * k(x[i], x[j]);
                }
            }

            double[] rs = new double[n + 1];
            for (int i = 0; i < n + 1; i++) {
                rs[i] = 2;
            }
            u = solveGauss(A, rs);
            System.out.println("Решение u выглядит так: " + Arrays.toString(u));
            epsCurrent = normVector(sumVector(u,trueSolution,false));
        }

        System.out.println("Real solution: " + Arrays.toString(trueSolution));
        System.out.println("Computed solution: " + Arrays.toString(u));
        return u;
    }

    public static double simpsonCoefficient(int j, int n, double h) {
        if (j == 0 || j == n) {
            return h/3;
        }
        if (j % 2 == 0) {
            return 2 * h / 3;
        }
        else {
            return 4 * h / 3;
        }
    }

    public static double k(double x_i, double x_j) {
        return sin(x_i - 2 * x_j);
    }

    public static void main(String[] args) throws Exception {
        solveIntegralEquation();
        // решение интегрального уравнения функция вида u(x) = 2 - 3sin(x)

    }

}
