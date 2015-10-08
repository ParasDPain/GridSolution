package com.dpain.paras.gridsolution.ProjectMatrix;

public class Operator {

	/* ------------------------
       Class variables
	 * ------------------------ */

    private static Matrix error = new Matrix(0, 0);

	/* ------------------------
       Public Methods
	 * ------------------------ */

    // Returns an invalid Matrix if the caller is sending invalid data
    public static Matrix Plus(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getValidity() && rightMatrix.getValidity()) {

            // local data for easier use
            int lRow = leftMatrix.getRowSize();
            int lColumn = leftMatrix.getColumnSize();
            int rRow = rightMatrix.getRowSize();
            int rColumn = rightMatrix.getColumnSize();

            // Addition/Subtraction can only be performed on similar matrices
            if (lRow == rRow && lColumn == rColumn) {

                // Matrix grid extraction for the operation
                double[][] newGrid = new double[lRow][lColumn];
                double[][] leftGrid = leftMatrix.getGrid();
                double[][] rightGrid = rightMatrix.getGrid();

                // Simple cyclic increment
                for (int ro = 0; ro < lRow; ro++) {
                    for (int col = 0; col < lColumn; col++) {
                        newGrid[ro][col] = leftGrid[ro][col] + rightGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

                Matrix result = new Matrix(newGrid);
                return result;
            }
        }
        return error;
    }

    // Returns an invalid Matrix if the caller is sending invalid data
    public static Matrix Minus(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getValidity() && rightMatrix.getValidity()) {

            // local data for easier use
            int lRow = leftMatrix.getRowSize();
            int lColumn = leftMatrix.getColumnSize();
            int rRow = rightMatrix.getRowSize();
            int rColumn = rightMatrix.getColumnSize();

            // Addition/Subtraction can only be performed on similar matrices
            if (lRow == rRow && lColumn == rColumn) {

                // Matrix grid extraction for the operation
                double[][] newGrid = new double[lRow][lColumn];
                double[][] leftGrid = leftMatrix.getGrid();
                double[][] rightGrid = rightMatrix.getGrid();

                // Simple cyclic increment
                for (int ro = 0; ro < lRow; ro++) {
                    for (int col = 0; col < lColumn; col++) {
                        newGrid[ro][col] = leftGrid[ro][col] - rightGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

                Matrix result = new Matrix(newGrid);
                return result;
            }
        }
        return error;
    }

    // Returns an invalid Matrix if the caller is sending invalid data
    public static Matrix Multiply(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getValidity() && rightMatrix.getValidity()) {
            int lRow = leftMatrix.getRowSize();
            int lColumn = leftMatrix.getColumnSize();
            int rRow = rightMatrix.getRowSize();
            int rColumn = rightMatrix.getColumnSize();

            if (lColumn == rRow) {

                // Matrix grid extraction for the operation
                double[][] newGrid = new double[lRow][rColumn];
                double[][] leftGrid = leftMatrix.getGrid();
                double[][] rightGrid = rightMatrix.getGrid();

                // Extended cyclic increment
                for (int ro = 0; ro < lRow; ro++) {
                    for (int col = 0; col < rColumn; col++) {
                        // X increments till the common r/c value of the matrices
                        // 4x2,2x3 will have product of 4x3 and 2 will be the common value
                        for (int x = 0; x < lColumn; x++) {
                            newGrid[ro][col] += leftGrid[ro][x] * rightGrid[x][col];
                        }// --X loop end
                    }// --Column loop end
                }// --Row loop end

                Matrix result = new Matrix(newGrid);
                return result;
            }
        }
        return error;
    }

    // Returns an invalid Matrix if the caller is sending invalid data
    public static Matrix Divide(Matrix leftMatrix, Matrix rightMatrix) {
        if (leftMatrix.getValidity() && rightMatrix.getValidity()) {

            // local data for easier use
            int lColumn = leftMatrix.getColumnSize();
            int rRow = rightMatrix.getRowSize();
            int rColumn = rightMatrix.getColumnSize();

            if (lColumn == rRow) {
                // Matrix grid extraction for the operation
                double[][] newGrid = new double[rRow][rColumn];
                double[][] rightGrid = rightMatrix.getGrid();

                // Simple cyclic increment
                for (int ro = 0; ro < rRow; ro++) {
                    for (int col = 0; col < rColumn; col++) {
                        newGrid[ro][col] = 1 / rightGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

                // Forwards operation after preparing the rightMatrix
                Matrix result = new Matrix(newGrid);
                return Multiply(leftMatrix, result);
            }
        }
        return error;
    }
}
