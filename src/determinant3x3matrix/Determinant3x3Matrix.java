/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package determinant3x3matrix;

import java.util.Arrays;

/**
 *
 * @author gavan
 */
public class Determinant3x3Matrix {

    private static int calc2x2Determinant(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int matrix[][] = {{1, 2, 3}, {4, 1, 5}, {6, 0, 2}};
        int numElements = 9;
        int sideLength = 3;

        /* steps to find determinant of 3x3
        // flattening the matrix would make this easier to iterate?
            OR
        - the first for loop iterates for i = 0 and i<9
        - we convert that value to a row and col value
        - row index is: i/3 = 2
        - col index is: i % 3 = 0
        if i = 0:
        - row index is 0/3 = 0 - need to round this down, eg i = 1, 1/3 = 0.333
        - col index is: 0 % 3 = 0
        i)      take index of current element
        ii)     determine the row and col of the current element
        iii)
         */
        // for every element in the matrix - correction, only need to do first row
        for (int i = 0; i < sideLength; i++) {

            int row = Math.floorDiv(i, sideLength);
            int col = i % sideLength;
            int scalar = matrix[row][col];
            int minorIndex = 0;
            int minorMatrix[][] = new int[2][2];
            for (int j = 0; j < numElements; j++) {
                if (minorIndex > 3) {
                    break;
                }

                // for every element in the matrix, construct the 2x2 matrix
                // the element itself can't be in the matrix of minors
                if (j == i) {
                    continue;
                }

                int currRow = Math.floorDiv(j, sideLength);

                int currCol = j % sideLength;
                if (currRow == row || currCol == col) {
                    continue;
                }
                // add the element to the matrix of minors

                int minorMatrixRow = Math.floorDiv(minorIndex, minorMatrix.length);
                int minorMatrixCol = minorIndex % minorMatrix.length;
                minorMatrix[minorMatrixRow][minorMatrixCol] = matrix[currRow][currCol];
                minorIndex++;

            }
            System.out.println("scalar: " + scalar);
            System.out.println(Arrays.deepToString(minorMatrix));
            System.out.println("product of scalar by det(minorMatrix): " + calc2x2Determinant(minorMatrix) * scalar);
        }
    }
}
