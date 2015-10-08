package com.dpain.paras.gridsolution;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.dpain.paras.gridsolution.ProjectMatrix.Matrix;

// Instances of this class are fragments representing a single
// object in our collection.
public class MatrixFragment extends Fragment {
    // TODO Text size isn't auto

    // Associated Matrix object and related attributes
    Matrix m;
    private int roSize;
    private int colSize;

    // GUI related fields
    LinearLayout matrixPanel;
    private TableLayout mainTable; // Table has a list of TableRows
    private TableLayout.LayoutParams tblParams; // To set Layout width and height to WRAP_CONTENT
    private TableRow[] rowObj; // each row has a list of EditTexts
    private EditText[][] txtBoxVal; // Holds the cell value

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate parent LinearLayout and assign associated object
        View rootView = inflater.inflate(R.layout.matrix_input, container, false);
        matrixPanel = (LinearLayout) rootView.findViewById(R.id.matrix_main_panel);

        // Init Matrix and DisplayMatrix
        MatrixInit();
        DisplayMatrix();
        return rootView;
    }

    private void MatrixInit() {
        // Random matrix
        m = new Matrix(5, (long) 100);
        roSize = m.getRowSize();
        colSize = m.getColumnSize();

        mainTable = new TableLayout(matrixPanel.getContext());
        rowObj = new TableRow[roSize];
        txtBoxVal = new EditText[roSize][colSize];
        tblParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
    }

    public void DisplayMatrix() {
        // Init mainTable with auto-scale columns
        mainTable.setGravity(Gravity.CENTER_HORIZONTAL);
        mainTable.setShrinkAllColumns(true);
        mainTable.setStretchAllColumns(true);

        // Inflater for auto-scale rows
        LayoutInflater inflater = getLayoutInflater(getArguments());

        // testing--FAIL
        // int dispWidth = matrixPanel.getResources().getDisplayMetrics().widthPixels;
        // int dispHeight = matrixPanel.getResources().getDisplayMetrics().heightPixels;
        // txtSize = 3 * dispArea/(matrixArea + 1/3)
        // int txtSize = (3 * dispHeight * dispWidth) / ((3 * colSize * roSize) + 1);
        // int pad = txtSize / 3;
        // --testing

        for (int i = 0; i < roSize; i++) {
            // Inflate row and assign associated object
            View rowView = inflater.inflate(R.layout.row_autosize, mainTable, false);
            rowObj[i] = (TableRow) rowView.findViewById(R.id.row);

            for (int j = 0; j < colSize; j++) {
                // Init txtBox and assign element value
                txtBoxVal[i][j] = new EditText(rowObj[i].getContext());
                txtBoxVal[i][j].setBackgroundResource(R.drawable.rect_empty_border);
                txtBoxVal[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                txtBoxVal[i][j].setGravity(Gravity.CENTER);
                txtBoxVal[i][j].setTextColor(Color.GRAY);
                // txtBoxVal[i][j].setTextSize(TypedValue.COMPLEX_UNIT_PX, (txtSize));
                // txtBoxVal[i][j].setPadding(pad, pad, pad, pad);

                txtBoxVal[i][j].setText(String.valueOf(m.GetElement(i, j)));

                // Add txtBox to the Row
                rowObj[i].addView(txtBoxVal[i][j]);
            }
            // Add the row of txtBoxes to the table
            mainTable.addView(rowObj[i]);
        }
        // Add table to the parentView
        matrixPanel.addView(mainTable, tblParams);
    }
}