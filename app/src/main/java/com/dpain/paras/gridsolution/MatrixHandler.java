package com.dpain.paras.gridsolution;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MatrixHandler {

    // View which is supposed to contain the generated matrix TableLayout
    private LinearLayout _parentView;
    // Context of the parentView
    private Context _parentContext;
    // Associated Matrix object and related attributes
    private Matrix _m;
    private int _roSize;
    private int _colSize;

    // UI related fields
    private TableLayout _mainTable;
    // To set Layout width and height to WRAP_CONTENT
    private TableLayout.LayoutParams _tblParams;
    private TableRow[] _rowObj;
    private EditText[][] _txtBoxVal;


    public MatrixHandler(Matrix M, LinearLayout ParentView) {
        _m = M;
        _roSize = _m.getRowSize();
        _colSize = _m.getColumnSize();
        _parentView = ParentView;
        _parentContext = _parentView.getContext();

        _mainTable = new TableLayout(_parentView.getContext());
        _rowObj = new TableRow[_roSize];
        _txtBoxVal = new EditText[_roSize][_colSize];
        _tblParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
    }

    public void DisplayMatrix() {
        // Init _mainTable with basic attributes
        _mainTable.setGravity(Gravity.CENTER_HORIZONTAL);
        //_mainTable.setBackgroundResource(R.drawable.rect_empty_border);

        // testing--
        int dispWidth = _parentContext.getResources().getDisplayMetrics().widthPixels;
        int dispHeight = _parentContext.getResources().getDisplayMetrics().heightPixels;
        // txtSize = 3 * dispArea/(matrixArea + 1/3)
        int txtSize = (3 * dispHeight * dispWidth) / ((3 * _colSize * _roSize) + 1);
        int pad = txtSize / 3;
        // --testing

        // Init txtBox with basic attributes and assign element value
        for (int i = 0; i < _roSize; i++) {
            _rowObj[i] = new TableRow(_mainTable.getContext());
            for (int j = 0; j < _colSize; j++) {
                _txtBoxVal[i][j] = new EditText(_mainTable.getContext());
                _txtBoxVal[i][j].setBackgroundResource(R.drawable.rect_empty_border);
                _txtBoxVal[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                _txtBoxVal[i][j].setGravity(Gravity.CENTER);
                _txtBoxVal[i][j].setTextColor(Color.GRAY);
                // _txtBoxVal[i][j].setTextSize(TypedValue.COMPLEX_UNIT_PX, (txtSize));
                // _txtBoxVal[i][j].setPadding(pad,pad,pad,pad);

                _txtBoxVal[i][j].setText(String.valueOf(_m.GetElement(i, j)));
            }
        }

        for (int i = 0; i < _roSize; i++) {
            for (int j = 0; j < _colSize; j++) {
                // Add txtBox to the Row
                _rowObj[i].addView(_txtBoxVal[i][j]);
            }
            // Add the row of txtBoxes to the table
            _mainTable.addView(_rowObj[i]);
        }
        // Add table to the parentView
        _parentView.addView(_mainTable, _tblParams);
    }
}
