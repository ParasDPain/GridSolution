package com.dpain.paras.gridsolution;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

// Instances of this class handle number of fragments in a collection
// Adapter for the SwipeView
public class MatrixCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public MatrixCollectionPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // New Fragment object to return
        Fragment matrixFragment = new MatrixFragment();
        return matrixFragment;
    }

    @Override
    public int getCount() {
        // Number of matrices
        return 3;
    }
}
