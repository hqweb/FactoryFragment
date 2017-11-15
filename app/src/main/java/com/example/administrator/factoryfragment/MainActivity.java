package com.example.administrator.factoryfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.one)
    RadioButton one;
    @BindView(R.id.two)
    RadioButton two;
    @BindView(R.id.three)
    RadioButton three;

    private FragmentFactory mFactory;
    private String mCurrentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mFactory = FragmentFactory.getInstance();
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

        initFragment(FragmentFactory.ONE_TAG);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                initFragment(FragmentFactory.ONE_TAG);
                break;
            case R.id.two:
                initFragment(FragmentFactory.TWO_TAG);
                break;
            case R.id.three:
                initFragment(FragmentFactory.THREE_TAG);
                break;
        }

    }

    private void initFragment(String tag) {
        Fragment baseFragment = mFactory.getFragmentByTag(tag);
        if (baseFragment == null) {
            throw new IllegalAccessError("tag is error!");
        }
        if (tag.equals(mCurrentTag)) {
            return;
        } else {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (!TextUtils.isEmpty(mCurrentTag)) {
                transaction.hide(mFactory.getFragmentByTag(mCurrentTag));
            }
            if (!baseFragment.isAdded()) {
                transaction.add(R.id.fragment_tab, baseFragment);
            } else {
                transaction.show(baseFragment);
            }
            transaction.commitAllowingStateLoss();
        }
        mCurrentTag = tag;
    }
}
