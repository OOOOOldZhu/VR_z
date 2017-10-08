package com.zhuvr.z.vr_zh.adapter;



import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.activity.VrVideoDesActivity;
import com.zhuvr.z.vr_zh.bean.VideoData;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VrVideoAdapter extends BaseQuickAdapter<VideoData.VideoItem> {

    public VrVideoAdapter(int layoutResId, List<VideoData.VideoItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final  VideoData.VideoItem item) {

        SimpleDateFormat formate=new SimpleDateFormat("yyyy/MM/dd");
        helper.setText(R.id.text,item.title)
                .setText(R.id.type,item.type)
                .setText(R.id.date,formate.format(new Date(item.date)));

        Glide.with(mContext).load(item.img).into((ImageView)helper.getView(R.id.image));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),VrVideoDesActivity.class);
                intent.putExtra("item",item);
                v.getContext().startActivity(intent);
            }
        });

    }
}
