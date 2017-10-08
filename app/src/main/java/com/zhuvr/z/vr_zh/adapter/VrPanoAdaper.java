package com.zhuvr.z.vr_zh.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.activity.VrImagePlayActivity;
import com.zhuvr.z.vr_zh.bean.ImageData;
import com.zhuvr.z.vr_zh.bean.ImageData.ImageItem;


import java.util.List;

public class VrPanoAdaper extends BaseQuickAdapter<ImageItem> {

    public VrPanoAdaper(int layoutResId, List<ImageItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ImageItem item) {

        helper.setText(R.id.text, item.title);

        Glide.with(mContext).load(item.url).into((ImageView) helper.getView(R.id.image));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), VrImagePlayActivity.class);
                intent.putExtra("item",item);
                v.getContext().startActivity(intent);
            }
        });
    }
}