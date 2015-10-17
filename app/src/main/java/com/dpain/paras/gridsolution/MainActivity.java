package com.dpain.paras.gridsolution;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.dpain.paras.gridsolution.ProjectMatrix.framework.Matrix;
import com.dpain.paras.gridsolution.ProjectMatrix.framework.Operator;


public class MainActivity extends FragmentActivity {
    // SwipeView and it's list adapter
    // adapter feeds a list of fragments for the SwipeView to display
    ViewPager swipeView;
    MatrixCollectionPagerAdapter adapter;

    // Testing
    Intent sendResult;
    public final static String RESULT_MATRIX = "com.dpain.paras.gridsolution.MATRIX";

    Button btnCalculate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init and set the adapter
        adapter = new MatrixCollectionPagerAdapter(getSupportFragmentManager());
        swipeView = (ViewPager) findViewById(R.id.swipe_view);
        swipeView.setAdapter(adapter);

        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        sendResult = new Intent(this, ResultActivity.class);
    }

    public void Calculate(View view) {
        // Construct an empty matrix
        Matrix result = new Matrix(5, 5);
        for (int i = 0; i < adapter.getCount(); i++) {
            MatrixFragment frag = (MatrixFragment) adapter.getItem(i);
            result = Operator.Plus(result, frag.m);
        }
        sendResult.putExtra(RESULT_MATRIX, result.getGrid());
        startActivity(sendResult);
    }

    public void NewMatrix(View view) {
        // Add one item after the current item in the adapter
        // set the current item to the new item
    }
}
