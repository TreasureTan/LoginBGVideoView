package com.example.administrator.loginbgvideoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {

    private CustomBgVideoView videoview;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoview = (CustomBgVideoView) findViewById(R.id.videoview);


        // TODO: 根据网络数据 或者 需求加载 广告或者视频或者图片
//        MediaController controller = new MediaController(this);
//        this.videoview.setMediaController(controller);
        //设置播放加载本地路径
        videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.loginmovie2));

//        videoView.setVideoURI(Uri.parse("http://imgfan.b2social.cn/6431f3b7-3f79-433f-a70b-bf5edbbca105.mp4"));
        //播放
        videoview.setSoundEffectsEnabled(true);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override


            public void onPrepared(MediaPlayer mp) {
                videoview.start();
//                splashViewBgWebviewContainer.setVisibility(View.GONE);
//                splashViewBgIconContainer.setVisibility(View.GONE);
//                splashViewBgVideoContainer.setVisibility(View.VISIBLE);
            }
        });
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
//                autoLogin();
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        videoview.pause();
        currentPosition = videoview.getCurrentPosition();
        Log.e("videoView","videoView stopPlayback");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoview!=null){
            Log.e("videoView","videoView start");
            videoview.seekTo(currentPosition);
            videoview.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoview!=null){
            videoview.stopPlayback();
            videoview = null;
        }
    }
}
