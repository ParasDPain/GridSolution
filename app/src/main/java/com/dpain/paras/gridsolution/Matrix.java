package com.dpain.paras.gridsolution;

import java.util.ArrayList;
import java.util.List;

// Add error checks (use false returns in boolean functions)
public class Matrix {

	/* ------------------------
	   Class variables
	 * ------------------------ */

    // Private property Row
    private int rowSize;
    public int getRowSize() {
        return rowSize;
    }
    private void setRowSize(int value) {
        this.rowSize = value;
    }

    // Private property Column
    private int columnSize;
    public int getColumnSize() {
        return columnSize;
    }
    private void setColumnSize(int value) {
        this.columnSize = value;
    }

    // Private property Grid
    private double[][] grid;
    public double[][] getGrid() {
        return grid;
    }
    private void setGrid(double[][] value) {
        this.grid = value;

    }

    // Private property Validity
    // Validity of the object
    private boolean Validity = true;
    public boolean getValidity() {
        return Validity;
    }
    private void setValidity(boolean value) {
        this.Validity = value;
    }

	/* ------------------------
	   Constructors
	 * ------------------------ */

    // Construct a matrix of zeros with r rows and c columns
    public Matrix(int r, int c) {
        // If invalid input, a null object is created with Validity set to false
        if (IsSizeValid(r,c)) {

            setRowSize(r);
            setColumnSize(c);
            double[][] matrix = new double[rowSize][columnSize];
            setGrid(matrix);
        }
    }

    // Construct a matrix with a given 2-D array grid
    public Matrix(double[][] grid) {
        // If invalid input, a null object is created with Validity set to false
        if(IsSizeValid(grid.length, grid[0].length) && IsGridValid(grid)) {

            setRowSize(grid.length);
            setColumnSize(grid[0].length);
            setGrid(grid);
        }

    }

    // Construct a matrix r rows and c columns with a repeated value
    public Matrix(int r, int c, double elementValue) {
        // If invalid input, a null object is created with Validity set to false
        if(IsSizeValid(r,c)) {

            setRowSize(r);
            setColumnSize(c);
            double[][] newGrid = new double[rowSize][columnSize];

            // Simple cyclic increment
            // Cycle through the row for each column
            for(int ro = 0; ro < rowSize; ro++) {
                for (int col = 0; col < columnSize; col++) {
                    newGrid[ro][col] = elementValue;
                }// --Column loop end
            }// --Row loop end

            setGrid(newGrid);
        }

    }

    // Construct a square identity matrix with size r
    public Matrix(int r) {
        // If invalid input, a null object is created with Validity set to false
        if(IsSizeValid(r,r)) {

            setRowSize(r);
            setColumnSize(r);
            double[][] newGrid = new double[rowSize][columnSize];

            // Simple cyclic increment
            // Cycle through the row for each column
            for(int ro = 0; ro < rowSize; ro++) {
                for (int col = 0; col < columnSize; col++) {
                    newGrid[ro][col] = (ro == col ? 1 : 0);
                }// --Column loop end
            }// --Row loop end

            setGrid(newGrid);
        }

    }

    // Construct a square matrix with size r and rounded pseudo-random values
    public Matrix(int size, long range) {
        // If invalid input, a null object is created with Validity set to false
        if(IsSizeValid(size,size)) {

            setRowSize(size);
            setColumnSize(size);
            double[][] newGrid = new double[rowSize][columnSize];

            // Simple cyclic increment
            // Cycle through the row for each column
            for(int ro = 0; ro < rowSize; ro++) {
                for (int col = 0; col < columnSize; col++) {
                    newGrid[ro][col] = Math.round(Math.random() * range);
                }// --Column loop end
            }// --Row loop end

            setGrid(newGrid);
        }

    }

	/* ------------------------
	   Public Methods
	 * ------------------------ */

    // Add new element to the grid at (x,y)
    // Returns false if the grid coordinates (x,y) did not exist
    public boolean AddElement(int posX, int posY, double value) {
        if(this.getValidity()) {

            try {
                grid[posX][posY] = value;
            } catch (IndexOutOfBoundsException e) {;
                return false;
            }

            return true;
        }
        return false;
    }

    // Removes element at (x,y) in the grid
    // Returns false if the grid coordinates (x,y) did not exist
    public boolean RemoveElement(int posX, int posY) {
        if(this.getValidity()) {

            try {
                grid[posX][posY] = 0;
            } catch (IndexOutOfBoundsException e) {
                return false;
            }

            return true;
        }
        return false;
    }

    // Returns element value at (x,y) in the grid
    // Returns 0 if the grid coordinates (x,y) do not exist
    public double GetElement(int posX, int posY) {
        if(this.getValidity()) {

            try {
                return grid[posX][posY];
            } catch (IndexOutOfBoundsException e) {
                return 0;
            }
        }
        return 0;
    }

    // Change row, column and grid size
    public boolean ChangeSize(int newR, int newC) {
        if(this.getValidity()) {

            int oldCol = getColumnSize();
            int oldRow = getRowSize();
            double[][] oldGrid = getGrid();
            double[][] newGrid = new double[newR][newC];

            // Check for the smallest r,c pair and set that as the limit
            // Always cycle till the smallest limit otherwise the array will overflow
            if (newR >= oldRow && newC >= oldCol) {

                // Simple cyclic increment
                // Cycle through the row for each column
                for(int ro = 0; ro < oldRow; ro++) {
                    for (int col = 0; col < oldCol; col++) {
                        newGrid[ro][col] = oldGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

            } else if(newR >= oldRow && newC <= oldCol) {

                // Simple cyclic increment
                // Cycle through the row for each column
                for(int ro = 0; ro < oldRow; ro++) {
                    for (int col = 0; col < newC; col++) {
                        newGrid[ro][col] = oldGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

            } else if(newR <= oldRow && newC >= oldCol) {

                // Simple cyclic increment
                // Cycle through the row for each column
                for(int ro = 0; ro < newR; ro++) {
                    for (int col = 0; col < oldCol; col++) {
                        newGrid[ro][col] = oldGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

            } else {

                // Simple cyclic increment
                // Cycle through the row for each column
                for(int ro = 0; ro < newR; ro++) {
                    for (int col = 0; col < newC; col++) {
                        newGrid[ro][col] = oldGrid[ro][col];
                    }// --Column loop end
                }// --Row loop end

            }

            if(IsSizeValid(newR, newC)) {
                setRowSize(newR);
                setColumnSize(newC);
                setGrid(newGrid);
                return true;
            }
        }
        return false;
    }

    public double[] GetGridAsArray() {
        if(this.getValidity()) {
            int Row = getRowSize();
            int Column = getColumnSize();
            double[] newArray = new double[Row*Column];
            double[][] Grid = getGrid();

            int index = 0;

            for(int ro = 0; ro < Row; ro++) {
                for (int col = 0; col < Column; col++) {
                    newArray[index] = Grid[ro][col];
                    index++;
                }// --Column loop end
            }// --Row loop end
            return newArray;
        }
        return new double[0];
    }

    public List<Double> GetGridAsList() {
        List<Double> newList = new ArrayList<>();
        if(this.getValidity()) {
            int Row = getRowSize();
            int Column = getColumnSize();
            double[][] Grid = getGrid();

            for(int ro = 0; ro < Row; ro++) {
                for (int col = 0; col < Column; col++) {
                    newList.add(Grid[ro][col]);
                }// --Column loop end
            }// --Row loop end
            return newList;
        }
        return newList;
    }

	/* ------------------------
	   Private Methods
	 * ------------------------ */

    // Only accepts positive values for the row and column values
    private boolean IsSizeValid(int r, int c) {
        if(r > 0 || c > 0) {
            return true;
        }
        setValidity(false);
        return false;

    }

    // Only accepts array grids with equal sized rows
    private boolean IsGridValid(double[][] grid) {
        for (int ro=0; ro<rowSize; ro++) {
            if (grid[ro].length != columnSize) {
                setValidity(false);
                return false;
            }
        }
        return true;
    }
}
