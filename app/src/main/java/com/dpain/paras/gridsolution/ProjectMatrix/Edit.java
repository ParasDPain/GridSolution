package com.dpain.paras.gridsolution.ProjectMatrix;

import java.util.ArrayList;
import java.util.List;

public class Edit {

    private static Matrix error = new Matrix(0, 0);

    // Multiplies the matrix with a scalar value
    public static Matrix ScalarProduct(Matrix m, double s) {
        // Pass -1 to change the matrix's sign
        if (m.getValidity()) {

            int row = m.getRowSize();
            int column = m.getColumnSize();
            double[][] grid = m.getGrid();
            double[][] newGrid = new double[row][column];

            // Simple cyclic increment
            // Cycle through the row for each column
            for (int ro = 0; ro < row; ro++) {
                for (int col = 0; col < column; col++) {
                    newGrid[ro][col] = s * grid[ro][col];
                }// --Column loop end
            }// --Row loop end

            Matrix result = new Matrix(newGrid);
            return result;
        }
        return error;
    }

    // Swap rows with columns and update the element list
    public static Matrix Transpose(Matrix m) {
        if (m.getValidity()) {

            int row = m.getRowSize();
            int column = m.getColumnSize();
            double[][] grid = m.getGrid();
            double[][] newGrid = new double[column][row];

            // Simple cyclic increment
            // Cycle through the row for each column
            for (int ro = 0; ro < row; ro++) {
                for (int col = 0; col < column; col++) {
                    newGrid[col][ro] = grid[ro][col];
                }// --Column loop end
            }// --Row loop end

            Matrix result = new Matrix(newGrid);
            return result;
        }
        return error;
    }

    // Removes/Retains any selected number of rows and columns from the Matrix
    public static Matrix Submatrix(Matrix m, List<Integer> listOfRows, List<Integer> listOfColumns, boolean removeThese) {
        /*Pseudo
         * If data is to be removed
		 * 		for all values in the grid
		 * 			if(i is NOT EQUAL to any value in the listofRows && same for j)
		 * 				listOf<double> buffer.add(grid[i][j])
		 * 			else nothing;
		 * else data is to be preserved
		 * 		for all values in the grid
		 * 			if(i is EQUAL to any value in the listofRows || same for j)
		 * 				listOf<double> buffer.add(grid[i][j])
		 * 			else nothing;
		 */

        // Removed row/columns will be the ones above the sent values, arrays start from 0
        // OutOfBound values in the row/column list will return garbled Matrix due to reduced size and zero removal
        if (m.getValidity() && m.getRowSize() > listOfRows.size() && m.getColumnSize() > listOfColumns.size()) {
            List<Double> buffer = new ArrayList<Double>();

            int row = m.getRowSize();
            int column = m.getColumnSize();
            double[][] grid = m.getGrid();
            int newR, newC;

            if (removeThese) {
                newR = row - listOfRows.size();
                newC = column - listOfColumns.size();
            } else {
                newR = listOfRows.size();
                newC = listOfColumns.size();
            }
            double[][] newGrid = new double[newR][newC];

            // Data filter
            if (removeThese) {
                // Simple cyclic increment
                // Cycle through the row for each column
                for (int ro = 0; ro < row; ro++) {
                    for (int col = 0; col < column; col++) {
                        if (!listOfRows.contains(ro) && !listOfColumns.contains(col)) {
                            buffer.add(grid[ro][col]);
                        }
                    }// --Column loop end
                }// --Row loop end

            } else {
                // Simple cyclic increment
                // Cycle through the row for each column
                for (int ro = 0; ro < row; ro++) {
                    for (int col = 0; col < column; col++) {
                        if (listOfRows.contains(ro) || listOfColumns.contains(col)) {
                            buffer.add(grid[ro][col]);
                        }
                    }// --Column loop end
                }// --Row loop end
            }

            // Grid reconstruction
            int index = 0;

            // Simple cyclic increment
            // Cycle through the row for each column
            for (int ro = 0; ro < newR; ro++) {
                for (int col = 0; col < newC; col++) {
                    newGrid[ro][col] = buffer.get(index);
                    index++;
                }// --Column loop end
            }// --Row loop end

            Matrix s = new Matrix(newGrid);
            return s;
        }
        return error;
    }

}