package com.zhuvr.z.vr_zh.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.zhuvr.z.vr_zh.R;


import java.io.IOException;


public class VrVideoPlayActivity extends AppCompatActivity {

    private VrVideoView vrVideoView;
    private VideoTask videoTask;
    private ProgressBar progressBar;
    private TextView timeView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_videoplay);
        vrVideoView = (VrVideoView) findViewById(R.id.vr_video_view);

        videoTask = new VideoTask();

        String play=getIntent().getStringExtra("play");
        videoTask.execute(play);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        timeView = (TextView) findViewById(R.id.time);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        vrVideoView.setInfoButtonEnabled(false);
        vrVideoView.setFullscreenButtonEnabled(false);

        //双眼模式
        vrVideoView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);
        // 全屏显示模式
       // vrVideoView.setDisplayMode(VrWidgetView.DisplayMode.EMBEDDED);

    }

    private class VideoTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {

            final VrVideoView.Options options = new VrVideoView.Options();

            options.inputType = VrVideoView.Options.TYPE_MONO;
            //FORMAT_DEFAULT:资源 sd assets
            //FORMAT_HLS :流媒体  直播
            options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;

            final VrVideoEventListener listener = new VrVideoEventListener() {

                @Override
                public void onLoadSuccess() {
                    super.onLoadSuccess();
                    progressBar.setVisibility(View.GONE);
                }


                @Override
                public void onLoadError(String errorMessage) {
                    super.onLoadError(errorMessage);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(VrVideoPlayActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }


                @Override
                public void onNewFrame() {
                    super.onNewFrame();

                    seekBar.setMax((int) vrVideoView.getDuration());
                    seekBar.setProgress((int) vrVideoView.getCurrentPosition());

                    String curr = String.format("%.2f", vrVideoView.getCurrentPosition() / 1000f);
                    String total = String.format("%.2f", vrVideoView.getDuration() / 1000f);
                    timeView.setText(curr + "/" + total + "s");
                }

                @Override
                public void onCompletion() {
                    super.onCompletion();

                    vrVideoView.seekTo(0);

                    vrVideoView.pauseVideo();
                    isPasuse = true;
                    Toast.makeText(VrVideoPlayActivity.this, "播放完成，是否重播", Toast.LENGTH_SHORT).show();
                }

                private boolean isPasuse = false;


                @Override
                public void onClick() {
                    super.onClick();
                    if (isPasuse) {
                        vrVideoView.playVideo();
                        isPasuse = false;
                    } else {
                        vrVideoView.pauseVideo();
                        isPasuse = true;
                    }
                }
            };
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vrVideoView.setEventListener(listener);
                        try {
                            vrVideoView.loadVideo(Uri.parse(params[0]),options);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vrVideoView != null) {
            vrVideoView.pauseRendering();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (vrVideoView != null) {
            vrVideoView.resumeRendering();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vrVideoView != null) {
            vrVideoView.shutdown();
        }
        if (videoTask != null && !videoTask.isCancelled()) {
            videoTask.cancel(true);
            videoTask = null;
        }
    }
}
