package com.dpain.paras.gridsolution;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.dpain.paras.gridsolution.ProjectMatrix.Matrix;

import java.util.ArrayList;

public class ResultActivity extends Activity {
    Intent receiveResult;
    LinearLayout resultPanel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultPanel = (LinearLayout) findViewById(R.id.result_panel);
        receiveResult = getIntent();

        // TODO Problem transferring data
        ArrayList<Integer> list = receiveResult.getIntegerArrayListExtra(MainActivity.RESULT_MATRIX);
        // Matrix result = new Matrix();
        // MatrixHandler mHandler = new MatrixHandler(result, resultPanel);
    }
}
