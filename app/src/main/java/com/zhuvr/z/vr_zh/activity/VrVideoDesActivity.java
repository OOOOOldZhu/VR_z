package com.zhuvr.z.vr_zh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.bean.VideoData;


import butterknife.ButterKnife;
import butterknife.InjectView;


public class VrVideoDesActivity extends AppCompatActivity {
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.control_bar)
    LinearLayout controlBar;
    @InjectView(R.id.detail_img_view)
    ImageView detailImgView;
    @InjectView(R.id.video_type)
    TextView videoType;
    @InjectView(R.id.play_link)
    ImageButton playLink;
    @InjectView(R.id.detail_text)
    TextView detailText;
    @InjectView(R.id.video_detail)
    FrameLayout videoDetail;
    private VideoData.VideoItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        final VideoData.VideoItem item =  (VideoData.VideoItem) intent.getSerializableExtra("item");

        titleText.setText(item.title);

        detailText.setText(item.textSimple);

        Glide.with(this).load(item.img).into(detailImgView);
        playLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),VrVideoPlayActivity.class);
                intent.putExtra("play",item.play);
                v.getContext().startActivity(intent);
            }
        });

    }
}
