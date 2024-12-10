package task9_3;

import task7_0.Matrix;
import task7_0.StandartMatrixOperations;
import task7_5.QRSolver;

import java.util.Arrays;

import static task7_5.QRSolver.houseHolderMethod;

public class QRMethod {
    public static Matrix qrMethod(Matrix A, double maxIterations) {

        double[][] A_copy_components;
        A_copy_components = Arrays.copyOf(A.components, A.components.length);

        Matrix A_copy = new Matrix(A_copy_components);

        for (int i = 0; i < maxIterations; i++) {
            houseHolderMethod(A_copy);
            Matrix Q_m = QRSolver.Q;
            Matrix R_m = QRSolver.R;
            A_copy = StandartMatrixOperations.multiplicationMatrix(R_m, Q_m);

        }

        return A_copy;
    }

}
