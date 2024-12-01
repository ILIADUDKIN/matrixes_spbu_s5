package task7_0;

import org.junit.Test;

import static org.junit.Assert.*;
import static task7_0.StandartMatrixOperations.multiplicationMatrixVector;
import static task7_0.StandartMatrixOperations.multiplicationVecVec;

public class TEST_STANDART_MATRIX_OPERATION {

    @Test
    public void testSumMatrix_Addition() {
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix result = StandartMatrixOperations.sumMatrix(a, b, true);
        assertEquals(new double[][]{{6, 8}, {10, 12}}, result.components);
    }

    @Test
    public void testSumMatrix_Subtraction() {
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix result = StandartMatrixOperations.sumMatrix(a, b, false);
        assertEquals(new double[][]{{-4, -4}, {-4, -4}}, result.components);
    }

    @Test
    public void testMultiplicationMatrix() {
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix b = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix result = StandartMatrixOperations.multiplicationMatrix(a, b);
        assertEquals(new double[][]{{19, 22}, {43, 50}}, result.components);
    }

    @Test
    public void testTranspositionMatrix() {
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix result = StandartMatrixOperations.transpositionMatrix(a);
        assertEquals(new double[][]{{1, 3}, {2, 4}}, result.components);
    }

    @Test
    public void testInverseMatrix_SquareMatrix() {
        Matrix a = new Matrix(new double[][]{{-1, 0}, {2, 1}});
        Matrix inverse = StandartMatrixOperations.inverseMatrix(a);
        Matrix result = StandartMatrixOperations.multiplicationMatrix(a, inverse);
        assertEquals(new double[][]{{1,0},{0,1}}, result.components);
    }


    @Test
    public void testInverseMatrix_NonSquareMatrix() {
        Matrix a = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
        assertThrows(IllegalArgumentException.class, () -> StandartMatrixOperations.inverseMatrix(a));
    }

    @Test
    public void testFindPivotRow(){
        double [][] matrix = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        int result = StandartMatrixOperations.findPivotRow(matrix,0,0);
        assertEquals(2, result);
    }

    @Test
    public void testSwapRows(){
        double [][] matrix = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
        double [][] result = StandartMatrixOperations.swapRows(matrix,0,2);
        assertEquals(new double[][]{{7,8,9},{4,5,6},{1,2,3}}, result);
    }

    @Test
    public void testFirstMatrixNorm(){
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double result = StandartMatrixOperations.firstMatrixNorm(a);
        assertEquals(6, result, 0.01);
    }

    @Test
    public void testMMV() {
        Matrix a = new Matrix(new double[][]{{1, 2}, {3, 4}});
        double[] b = new double[]{1,1};
        double[] result = multiplicationMatrixVector(a,b);
        double[] expected_result = new double[]{3,7};
        assertArrayEquals(expected_result, result, 0.01);
    }

    @Test
    public void testMVV() {
        double[] vec_1 = new double[] {1,2};
        double[] vec_2 = new double[] {2,4};
        Matrix a = new Matrix(new double[][] {{2,4}, {4,8}});
        Matrix a_new = multiplicationVecVec(vec_1, vec_2);
        for (int i = 0; i < a.height; i++) {
            assertArrayEquals(a.components[i], a_new.components[i], 0.01);
        }
    }
}
