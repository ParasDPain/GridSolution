package com.dpain.paras.gridsolution;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Space;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // limit of the randomly generated numbers (zero based)
        long randSeed = 100;
        Matrix m = new Matrix(5, randSeed);
        Matrix n = new Matrix(4, (long)50);

        // View which is supposed to contain the generated matrix TableLayout
        LinearLayout parentView = (LinearLayout) findViewById(R.id.test_container);

        MatrixHandler first = new MatrixHandler(m, parentView);
        first.DisplayMatrix();
        Space spacer = new Space(parentView.getContext());
        spacer.setMinimumHeight(20);
        parentView.addView(spacer);
        MatrixHandler second = new MatrixHandler(n, parentView);
        second.DisplayMatrix();
    }
}
