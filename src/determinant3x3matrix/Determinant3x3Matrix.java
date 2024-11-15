/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package determinant3x3matrix;

/**
 *
 * @author gavan
 */
public class Determinant3x3Matrix {

    private static int calc2x2Determinant(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private static void calc3x3Determinant(int[][] matrix) {
        int numElements = 9; // 9 elements in the matrix
        int sideLength = 3; // 3x3 matrix
        int determinant = 0; // store the determinant sum as we calculate it

        // for every all elements in first row of matrix
        for (int i = 0; i < sideLength; i++) {

            /*
            If we flatten the matrix into a 1D array, then we can calculate the
            row and column of the i-th element of the 1D array
             */
            int row = Math.floorDiv(i, sideLength);
            int col = i % sideLength;

            // we get the scalar in our original array using the row and col we
            // calculated
            int scalar = matrix[row][col];

            /* we use minorIndex for the following:
            i) To keep track of our position in the matrix of minors as we fill
            in the values
            ii) To terminate our inner for loop once minorMatrix is full
             */
            int minorIndex = 0;
            int minorMatrix[][] = new int[2][2];

            // this loop constructs the matrix of minors for each element a i
            for (int j = 0; j < numElements; j++) {
                // the matrix of minors only has 4 elements
                if (minorIndex > 3) {
                    break;
                }

                // the element itself can't be in the matrix of minors
                if (j == i) {
                    continue;
                }

                // convert our 1D index, j, to a 2D row and col
                int currRow = Math.floorDiv(j, sideLength);
                int currCol = j % sideLength;

                // ignore the row and column that contain our element at index i
                if (currRow == row || currCol == col) {
                    continue;
                }

                // we convert the 1D value of minorIndex to the
                // 2D row and column equivalent
                int minorMatrixRow = Math.floorDiv(minorIndex, minorMatrix.length);
                int minorMatrixCol = minorIndex % minorMatrix.length;

                // add the element to the matrix of minors
                minorMatrix[minorMatrixRow][minorMatrixCol] = matrix[currRow][currCol];

                // increment minorIndex, ready to add the next element
                minorIndex++;

            }

            // we use i to determine the sign of the minor
            if (i % 2 == 0) {
                determinant += calc2x2Determinant(minorMatrix) * scalar;
            } else {
                determinant += -(calc2x2Determinant(minorMatrix) * scalar);
            }
        }
        System.out.println("determinant of 3x3 matrix: " + determinant);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int matrix[][] = {{1, 2, 3}, {4, 1, 5}, {6, 0, 2}};
        int anotherMatrix[][] = {{2, -3, 1}, {5, -1, 2}, {3, 2, -1}};
        int yetAnotherMatrix[][] = {{5, -1, 3}, {7, 2, 4}, {6, 0, 1}};

        calc3x3Determinant(anotherMatrix);
        calc3x3Determinant(matrix);
        calc3x3Determinant(yetAnotherMatrix);

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
    }
}
