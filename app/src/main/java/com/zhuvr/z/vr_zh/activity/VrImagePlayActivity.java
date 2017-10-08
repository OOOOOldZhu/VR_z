package com.zhuvr.z.vr_zh.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.zhuvr.z.vr_zh.bean.ImageData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VrImagePlayActivity extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;
    private ImageTask imageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        ImageData.ImageItem item= (ImageData.ImageItem) intent.getSerializableExtra("item");
        vrPanoramaView = new VrPanoramaView(this);
        setContentView(vrPanoramaView);

        imageTask = new ImageTask();
        imageTask.execute(item.url);
    }
    private class ImageTask extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {

                URL url=new URL(params[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                InputStream inputStream =connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null)
            {

                VrPanoramaView.Options options=new VrPanoramaView.Options() ;

                options.inputType= VrPanoramaView.Options.TYPE_MONO;

                VrPanoramaEventListener listener=new VrPanoramaEventListener(){

                    @Override
                    public void onLoadError(String errorMessage) {
                        super.onLoadError(errorMessage);
                        Toast.makeText(VrImagePlayActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoadSuccess() {
                        super.onLoadSuccess();
                        Toast.makeText(VrImagePlayActivity.this, "进入VR图片显示...", Toast.LENGTH_SHORT).show();
                    }
                };
                vrPanoramaView.setEventListener(listener);
                vrPanoramaView.loadImageFromBitmap(bitmap,options);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vrPanoramaView != null) {
            vrPanoramaView.pauseRendering();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vrPanoramaView != null) {
            vrPanoramaView.resumeRendering();
        }

        vrPanoramaView.setInfoButtonEnabled(false);

        vrPanoramaView.setFullscreenButtonEnabled(false);
        vrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vrPanoramaView != null) {
            vrPanoramaView.shutdown();
        }
        if (imageTask != null && !imageTask.isCancelled()) {

            imageTask.cancel(true);
            imageTask = null;
        }
    }
}
