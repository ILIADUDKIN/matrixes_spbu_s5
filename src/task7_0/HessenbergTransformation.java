package task7_0;

import task7_0.Matrix;

/**
 * Заимствовал класс, так как у самого не удалось отписать
 */
public class HessenbergTransformation {

    private Matrix matrix;

    public HessenbergTransformation(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix transform() {
        double[][] components = matrix.components;
        int n = matrix.height;

        for (int i = 0; i < n - 2; i++) {
            int maxIndex = findMaxIndex(components, i, i);
            swapRows(components, i, maxIndex);
            swapColumns(components, i, maxIndex);

            double[] householderVector = householderVector(components, i);
            double[][] householderMatrix = householderMatrix(householderVector, n);

            components = multiply(householderMatrix, components);
            components = multiply(components, householderMatrix);
        }

        return new Matrix(components);
    }

    private int findMaxIndex(double[][] components, int row, int col) {
        int maxIndex = row;
        double max = Math.abs(components[row][col]);

        for (int i = row + 1; i < components.length; i++) {
            if (Math.abs(components[i][col]) > max) {
                max = Math.abs(components[i][col]);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private void swapRows(double[][] components, int row1, int row2) {
        double[] temp = components[row1];
        components[row1] = components[row2];
        components[row2] = temp;
    }

    private void swapColumns(double[][] components, int col1, int col2) {
        for (int i = 0; i < components.length; i++) {
            double temp = components[i][col1];
            components[i][col1] = components[i][col2];
            components[i][col2] = temp;
        }
    }

    private double[] householderVector(double[][] components, int index) {
        double[] vector = new double[components.length];
        double alpha = -Math.signum(components[index + 1][index]) * norm(components, index);

        for (int i = 0; i < index + 1; i++) {
            vector[i] = 0;
        }

        vector[index + 1] = components[index + 1][index] - alpha;

        for (int i = index + 2; i < components.length; i++) {
            vector[i] = components[i][index];
        }

        return vector;
    }

    private double norm(double[][] components, int index) {
        double sum = 0;

        for (int i = index + 1; i < components.length; i++) {
            sum += Math.pow(components[i][index], 2);
        }

        return Math.sqrt(sum);
    }

    private double[][] householderMatrix(double[] vector, int n) {
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        double norm = norm(vector);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] -= 2 * vector[i] * vector[j] / Math.pow(norm, 2);
            }
        }

        return matrix;
    }

    private double norm(double[] vector) {
        double sum = 0;

        for (int i = 0; i < vector.length; i++) {
            sum += Math.pow(vector[i], 2);
        }

        return Math.sqrt(sum);
    }

    private double[][] multiply(double[][] matrix1, double[][] matrix2) {
        int n = matrix1.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }
}