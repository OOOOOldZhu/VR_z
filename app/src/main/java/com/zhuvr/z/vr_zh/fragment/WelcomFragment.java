package com.zhuvr.z.vr_zh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.zhuvr.z.vr_zh.R;
import com.zhuvr.z.vr_zh.activity.VrVideoDesActivity;
import com.zhuvr.z.vr_zh.activity.ZhiboActivity;

public class WelcomFragment  extends Fragment {
    Button textView_welcom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.welcomfragment,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // View view=View.inflate(getContext(),R.layout.welcomfragment,null);
        textView_welcom= (Button) getActivity().findViewById(R.id.tv_welcomfragment);
        textView_welcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ZhiboActivity.class);
                //intent.putExtra("item",item);
                Toast.makeText(v.getContext(),"跳转直播界面。。。。",Toast.LENGTH_SHORT).show();;
                v.getContext().startActivity(intent);
            }
        });

    }
}
