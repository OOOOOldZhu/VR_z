package com.zhuvr.z.vr_zh.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;
import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.adapter.VrPanoAdaper;
import com.zhuvr.z.vr_zh.bean.ImageData;
import com.zhuvr.z.vr_zh.utils.ApiUrls;
import com.zhuvr.z.vr_zh.utils.HttpUtils;
import com.zhuvr.z.vr_zh.view.StateLayout;


public class BaseFragment extends Fragment {

    private StateLayout stateLayout;
    protected RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        stateLayout = new StateLayout(container.getContext());

        recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(getLayout(container));
        stateLayout.addNormalView(recyclerView);
        stateLayout.showLoading();
        getPageData();
        return stateLayout;
    }

    public void getPageData() {
       final OnResponseListener<String> callBack = new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                stateLayout.showLoading();
            }
            @Override
            public void onFinish(int what) {
                stateLayout.showNormal();
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                stateLayout.showError();
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json=response.get();
                Object data = processJson(json);
                showData(data);
            }
        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HttpUtils.get(loadUrl(), callBack);
            }
        }, 1000);
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayout(@Nullable ViewGroup container) {
        return new LinearLayoutManager(container.getContext());
    }
    @NonNull
    protected String loadUrl() {
        return ApiUrls.IMAGE_LIST;
    }

    protected Object processJson(String json) {
        return new Gson().fromJson(json,ImageData.class);
    }

    protected void showData(Object data) {
        ImageData imageData= (ImageData) data;

        VrPanoAdaper adapter=new VrPanoAdaper(R.layout.item_image_card,imageData.list);
        recyclerView.setAdapter(adapter);
    }
}
