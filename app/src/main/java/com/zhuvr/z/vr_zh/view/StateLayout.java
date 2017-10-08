package com.zhuvr.z.vr_zh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zhuvr.z.vr_zh.R;


public class StateLayout extends FrameLayout {
    private View mLoading = null;
    private View mError = null;
    private View mEmpty = null;
    private View mNormal = null;

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.state_layout, null);
        mLoading = view.findViewById(R.id.loading);
        mError = view.findViewById(R.id.error);
        mEmpty = view.findViewById(R.id.nodata);
        this.addView(view);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(Context context) {
        this(context, null, 0);
    }

    private void reset() {
        mLoading.setVisibility(View.GONE);
        mError.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        if (mNormal != null) {
            mNormal.setVisibility(View.GONE);
        }

    }

    public void showLoading() {
        reset();
        mLoading.setVisibility(View.VISIBLE);
    }

    public void showError() {
        reset();
        mError.setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        reset();
        mEmpty.setVisibility(View.VISIBLE);
    }

    public void addNormalView(Object normalViewLayout) {
        if (normalViewLayout instanceof Integer) {
            mNormal = LayoutInflater.from(getContext()).inflate((Integer) normalViewLayout, this, false);
        } else {
            mNormal = (View) normalViewLayout;
        }
        this.addView(mNormal);
    }

    public void showNormal() {
        reset();
        mNormal.setVisibility(View.VISIBLE);
    }

}
