package task10_1;

import task7_0.Matrix;

import java.util.Arrays;
import java.util.Scanner;

import static task7_4.TomasSolver.solveTomas;

/**
 * Вариант 7
 * - (4 + x) / (5 + 2x) u'' + (x/2 - 1)u' + (1 + e^(x/2))u = 2 + x, u'(-1) = u'(1) + 2u(1) = 0
 */
public class ODESolver {

    public static double[] solveOdeH() throws Exception {
        // задание начальных параметров
        int a = -1;
        int b = 1;
        int n = inputN();
        double h = (double)(b - a) / (double) n;

        // задание сетки узлов
        double[] x_i = new double[n+1];
        for (int i = 0; i < n+1; i++) {
            x_i[i] = a + i * h;
        }

        // задание коэффициентов p, q, r
        double[] p_i = new double[n+1];
        double[] q_i = new double[n+1];
        double[] r_i = new double[n+1];
        double[] f_i = new double[n+1];

        for (int i = 0; i < n + 1; i++) {
            p_i[i] = (4 + x_i[i]) / (5 + 2 * x_i[i]);
            q_i[i] = x_i[i] / 2 - 1;
            r_i[i] = (1 + Math.exp(x_i[i] / 2));
            f_i[i] = x_i[i] + 2;
        }

        // задание коэффициентов A,B,C,G (внутренних)
        double[] A = new double[n+1];
        double[] B = new double[n+1];
        double[] C = new double[n+1];
        double[] G = new double[n+1];

        for (int i = 1; i < n ; i++) {
            A[i] = (-p_i[i]) / (h * h) - (q_i[i]) / (2*h);
            B[i] = (2 * p_i[i]) / (h * h) + r_i[i];
            C[i] = (-p_i[i]) / (h * h) + (q_i[i]) / (2*h);
            G[i] = f_i[i];
        }

        // задание граничных коэффициентов порядка o(h)
        double alpha_1 = 0;
        double alpha_2 = -1;
        double beta_1 = 2;
        double beta_2 = 1;
        double alpha = 0;
        double beta = 0;
        A[0] = 0;
        B[0] = alpha_1 + alpha_2 / h;
        C[0] = -alpha_2 / h;
        G[0] = alpha;

        A[n] = -beta_2 / h;
        B[n] = beta_1 + beta_2 / h;
        C[n] = 0;
        G[n] = beta;

        // генерация матрицы размером n+1 на n+1
        Matrix matrix = new Matrix(new double[n+1][n+1]);
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (Math.abs(i - j) <= 1) {
                    if (i == j) {
                       matrix.components[i][j] = B[i];
                    }
                    if (j == i + 1) {
                        matrix.components[i][j] = C[i];
                    }
                    if (i == j + 1) {
                        matrix.components[i][j] = A[i];
                    }
                } else {
                    matrix.components[i][j] = 0;
                }

            }
        }
        double[] rightSide = Arrays.copyOf(G, n + 1);
        System.out.println("Matrix: \n" + matrix);
        double[] y = solveTomas(matrix, A, B, C, rightSide);
        for (int i = 0; i < n+1; i++) {
            System.out.println("i: " + i + ", x: " + x_i[i] + ", y: " + y[i]);
        }
        return y;
    }

    public static double[] solveOdeH2() throws Exception {
        // задание начальных параметров
        int a = -1;
        int b = 1;
        int n = inputN();
        double h = (double) (b - a) / (double) n;

        // задание сетки узлов
        double[] x_i = new double[n + 2];
        for (int i = 0; i < n + 2; i++) {
            x_i[i] = a - h / 2 + i * h;
        }

        // задание коэффициентов p, q, r
        double[] p_i = new double[n + 2];
        double[] q_i = new double[n + 2];
        double[] r_i = new double[n + 2];
        double[] f_i = new double[n + 2];

        for (int i = 0; i < n + 2; i++) {
            p_i[i] = (4 + x_i[i]) / (5 + 2 * x_i[i]);
            q_i[i] = x_i[i] / 2 - 1;
            r_i[i] = (1 + Math.exp(x_i[i] / 2));
            f_i[i] = x_i[i] + 2;
        }

        // задание коэффициентов A,B,C,G (внутренних)
        double[] A = new double[n + 2];
        double[] B = new double[n + 2];
        double[] C = new double[n + 2];
        double[] G = new double[n + 2];

        for (int i = 1; i < n + 1; i++) {
            A[i] = (-p_i[i]) / (h * h) - (q_i[i]) / (2 * h);
            B[i] = (2 * p_i[i]) / (h * h) + r_i[i];
            C[i] = (-p_i[i]) / (h * h) + (q_i[i]) / (2 * h);
            G[i] = f_i[i];
        }

        double alpha_1 = 0;
        double alpha_2 = -1;
        double beta_1 = 2;
        double beta_2 = 1;
        double alpha = 0;
        double beta = 0;
        A[0] = 0;
        B[0] = alpha_1 / 2 + alpha_2 / h;
        C[0] = alpha_1 / 2 - alpha_2 / h;
        G[0] = alpha;

        A[n + 1] = beta_1 / 2 - beta_2 / h;
        B[n + 1] = beta_1 / 2 + beta_2 / h;
        C[n + 1] = 0;
        G[n + 1] = beta;

        // генерация матрицы размером n+2 на n+2
        Matrix matrix = new Matrix(new double[n + 2][n + 2]);
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (Math.abs(i - j) <= 1) {
                    if (i == j) {
                        matrix.components[i][j] = B[i];
                    }
                    if (j == i + 1) {
                        matrix.components[i][j] = C[i];
                    }
                    if (i == j + 1) {
                        matrix.components[i][j] = A[i];
                    }
                } else {
                    matrix.components[i][j] = 0;
                }

            }
        }
        double[] rightSide = Arrays.copyOf(G, n + 2);
        System.out.println("Matrix: \n" + matrix);
        double[] y = solveTomas(matrix, A, B, C, rightSide);
        for (int i = 0; i < n+2; i++) {
            System.out.println("i: " + i + ", x: " + x_i[i] + ", y: " + y[i]);
        }
        return y;
    }

    public static int inputN() throws Exception {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter N: ");
            int n = sc.nextInt();
            sc.nextLine();
            if (n < 0)
                throw new IllegalArgumentException("N must be a positive integer");
            return n;

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Решение на основной сетке: ");
        solveOdeH();
        System.out.println("Решение на вторичной сетке: ");
        solveOdeH2();
    }
}
