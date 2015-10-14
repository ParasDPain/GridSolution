package com.dpain.paras.gridsolution;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.dpain.paras.gridsolution.ProjectMatrix.Edit;
import com.dpain.paras.gridsolution.ProjectMatrix.Matrix;
import com.dpain.paras.gridsolution.ProjectMatrix.Operator;

// Instances of this class are fragments representing a single
// object in our collection.
public class MatrixFragment extends Fragment implements View.OnClickListener {
    // TODO Text size isn't auto
    // TODO Add a Solve method to parse mathematical expressions
    // TODO Complete the main GUI

    // Associated Matrix object and related attributes
    Matrix m;
    private Boolean mSign = true; // true = +; false = -
    private Operand op = Operand.Plus;
    private int roSize;
    private int colSize;

    // GUI related fields
    View rootView;
    LinearLayout matrixPanel;
    private TableLayout mainTable; // Table has a list of TableRows
    private TableLayout.LayoutParams tblParams; // To set Layout width and height to WRAP_CONTENT
    private TableRow[] rowObj; // each row has a list of EditTexts
    private EditText[][] txtBoxVal; // Holds the cell value

    Button sign, operand;
    EditText scalarMul;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate parent LinearLayout and assign associated object
        rootView = inflater.inflate(R.layout.matrix_input, container, false);
        matrixPanel = (LinearLayout) rootView.findViewById(R.id.matrix_main_panel);

        ClickInit();

        // Init Matrix and DisplayMatrix
        MatrixInit(new Matrix(5, (long) 100));
        DisplayMatrix();
        return rootView;
    }

    private void MatrixInit(Matrix M) {
        // Random matrix
        m = M;
        roSize = m.getRowSize();
        colSize = m.getColumnSize();

        mainTable = new TableLayout(matrixPanel.getContext());
        rowObj = new TableRow[roSize];
        txtBoxVal = new EditText[roSize][colSize];

        // testing
        scalarMul = (EditText) rootView.findViewById(R.id.edit_scalar_mul);
        scalarMul.setText("0.0");

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
                txtBoxVal[i][j].setTextColor(Color.BLACK);
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

    // Init Button Click Listeners
    private void ClickInit(){
        Button addRow = (Button)rootView.findViewById(R.id.btn_add_row);
        addRow.setOnClickListener(this);
        Button subRow = (Button)rootView.findViewById(R.id.btn_sub_row);
        subRow.setOnClickListener(this);
        Button addCol = (Button)rootView.findViewById(R.id.btn_add_col);
        addCol.setOnClickListener(this);
        Button subCol = (Button)rootView.findViewById(R.id.btn_sub_col);
        subCol.setOnClickListener(this);

        Button trans = (Button) rootView.findViewById(R.id.btn_transpose_test);
        trans.setOnClickListener(this);
        sign = (Button) rootView.findViewById(R.id.btn_matrix_sign);
        sign.setOnClickListener(this);
        operand = (Button) rootView.findViewById(R.id.btn_matrix_operator);
        operand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_row:
                m.ChangeSize(roSize + 1, colSize);
                break;
            case R.id.btn_sub_row:
                m.ChangeSize(roSize - 1, colSize);
                break;
            case R.id.btn_add_col:
                m.ChangeSize(roSize, colSize + 1);
                break;
            case R.id.btn_sub_col:
                m.ChangeSize(roSize, colSize - 1);
                break;
            case R.id.btn_transpose_test:
                m = Edit.Transpose(m);
                break;
            case R.id.btn_matrix_sign:
                ToggleSign();
                break;
            case R.id.btn_matrix_operator:
                ToggleOperand();
                break;
        }
        matrixPanel.removeView(mainTable);
        MatrixInit(m);
        DisplayMatrix();
    }

    private void ToggleSign(){
        // Change button text then update button state
        if(mSign){
            sign.setText("-");
        } else{
            sign.setText("+");
        }
        mSign = !mSign;
    }

    private void ToggleOperand(){
        switch(op){
            case Plus:
                op = Operand.Minus;
                operand.setText("-");
                break;
            case Minus:
                op = Operand.Multiply;
                operand.setText("*");
                break;
            case Multiply:
                op = Operand.Divide;
                operand.setText("/");
                break;
            case Divide:
                op = Operand.Plus;
                operand.setText("+");
                break;
        }
    }

    public void Redraw() {
        // Read updated values from the Matrix
        for (int i = 0; i < roSize; i++) {
            for (int j = 0; j < colSize; j++) {
                txtBoxVal[i][j].setText(String.valueOf(m.GetElement(i, j)));
            }
        }
    }
}

enum Operand {
    Plus, Minus, Multiply, Divide
}