package com.zhuvr.z.vr_zh.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.adapter.VrVideoAdapter;
import com.zhuvr.z.vr_zh.bean.VideoData;
import com.zhuvr.z.vr_zh.utils.ApiUrls;


public class VrVideoFragment extends BaseFragment {
    @Override
    protected String loadUrl() {
        return ApiUrls.VIDEO_LIST;
    }
    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayout(@Nullable ViewGroup container) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 2);
        return gridLayoutManager;
    }
    @Override
    protected Object processJson(String json) {
        return new Gson().fromJson(json, VideoData.class);
    }
    @Override
    protected void showData(Object data) {
        VideoData videoData = (VideoData) data;

        VrVideoAdapter vrVideoAdapter=new VrVideoAdapter(R.layout.item_video_card,videoData.content);
        recyclerView.setAdapter(vrVideoAdapter);
    }
}
