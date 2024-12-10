package task7_5;

import task7_0.Matrix;

import java.util.Arrays;

import static task7_0.StandartMatrixOperations.*;
import static task7_0.StandartMatrixOperations.multiplicationMatrix;


public class QRSolver {
    public static Matrix Q;
    public static Matrix R;

    public static void givensMethod(Matrix A, double[] b) {
        Matrix A_new = getMatrix(A);
        double[] b_new = Arrays.copyOf(b, b.length);
        Matrix T = identityMatrix(A.height);
        Matrix[][] T_ij = new Matrix[A_new.height][A_new.width];
        double[][] c = new double[A_new.height][A_new.width];
        double[][] s = new double[A_new.height][A_new.width];
        for (int i = 0; i < A_new.height; i++) {
            for (int j = i+1; j < A_new.width; j++) {
                // вычисление коэффицентов c и s
                double r = Math.sqrt(A_new.components[i][i] * A_new.components[i][i]
                        + A_new.components[j][i] * A_new.components[j][i]);
                c[i][j] = A_new.components[i][i] / r;
                s[i][j] = A_new.components[j][i] / r;

                // перерасчитывание коэффициентов матрицы А
                Matrix T_new = identityMatrix(A.components[0].length);
                T_new.components[i][i] = c[i][j];
                T_new.components[j][j] = c[i][j];
                T_new.components[i][j] = s[i][j];
                T_new.components[j][i] = -s[i][j];
                T_ij[i][j] = T_new;
                T = multiplicationMatrix(T_new, T);
                A_new = multiplicationMatrix(T_new, A_new);
                b_new = multiplicationMatrixVector(T_new, b_new);
            }
        }
        R = A_new;
        Q = transpositionMatrix(T);
    }


    public static void houseHolderMethod(Matrix A) {
        Matrix A_new = getMatrix(A);
        Matrix P_i = identityMatrix(A.height);
        for (int j = 0; j < A_new.width; j++) {

            double[] a = new double[A_new.width - j];
            for (int i = j; i < A_new.height; i++) {
                a[i - j] = A_new.components[i][j];
            }
            double[] v = new double[A.width - j];

            for (int k = 0; k < v.length; k++) {
                v[k] = a[k] + normVector(a) * (k == 0 ? 1 : 0) * Math.signum(a[0]);
            }

            double norm_v = normVector(v);
            double[] w = new double[A.width - j];
            for (int k = 0; k < w.length; k++) {
                w[k] = v[k] / norm_v;
            }
            Matrix V = sumMatrix(identityMatrix(A.width - j),
                    multiplicationMatrixDouble(2,multiplicationVecVec(w, w)), false);
            Matrix P = new Matrix(new double[A.width][A.height]);

            for (int i_1 = 0; i_1 < P.height; i_1++) {
                for (int j_1 = 0; j_1 < P.width; j_1++) {
                    if (i_1 < j || j_1 < j) {
                        P.components[i_1][j_1] = (i_1 == j_1) ? 1 : 0;
                    } else {
                        P.components[i_1][j_1] = V.components[i_1 - j][j_1 - j];
                    }
                }
            }

            P_i = multiplicationMatrix(P_i, P); // вот здесь была проблема
            A_new = multiplicationMatrix(P, A_new);

        }
        R = A_new;
        Q = P_i;


    }

    private static Matrix getMatrix(Matrix A) {
        if (A.height != A.width) {
            throw new IllegalArgumentException("Matrix does not have the same dimension");
        }
        if (A.height == 0) {
            throw new IllegalArgumentException("Matrix should have at least one dimension");
        }

        //Создание глубокой копии объекта
        double[][] A_copy_components;
        A_copy_components = Arrays.copyOf(A.components, A.components.length);

        return new Matrix(A_copy_components);
    }


}
