package com.example.administrator.factoryfragment;

import android.support.v4.app.Fragment;

import com.example.administrator.factoryfragment.fragment.OneFragment;
import com.example.administrator.factoryfragment.fragment.ThreeFragment;
import com.example.administrator.factoryfragment.fragment.TwoFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/16.
 */

public class FragmentFactory {
    public static final String ONE_TAG = "one";
    public static final String TWO_TAG = "two";
    public static final String THREE_TAG = "three";

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;


    private static FragmentFactory mFactory;
    private HashMap<String, Fragment> mHashMap = new HashMap<>();

    private FragmentFactory() {

    }

    public static FragmentFactory getInstance() {
        if (mFactory == null) {
            synchronized (FragmentFactory.class) {
                if (mFactory == null) {
                    mFactory = new FragmentFactory();
                }
            }
        }
        return mFactory;
    }

    public Fragment getFragmentByTag(String tag) {
        if (tag.equals(ONE_TAG) && oneFragment == null) {
            oneFragment = new OneFragment();
            mHashMap.put(ONE_TAG, oneFragment);
        }
        if (tag.equals(TWO_TAG) && twoFragment == null) {
            twoFragment = new TwoFragment();
            mHashMap.put(TWO_TAG, twoFragment);
        }

        if (tag.equals(THREE_TAG) && threeFragment == null) {
            threeFragment = new ThreeFragment();
            mHashMap.put(THREE_TAG, threeFragment);
        }
        return mHashMap.get(tag);
    }

}
