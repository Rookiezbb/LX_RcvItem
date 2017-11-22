package com.bawei.lx_rcvitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bawei.lx_rcvitem.common.PlayerManager;
import com.bawei.lx_rcvitem.widget.media.IjkVideoView;

public class VideoActivity extends AppCompatActivity implements PlayerManager.PlayerStateListener{

    private IjkVideoView mVideoView;
    private PlayerManager player;
    private String url1 = "http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initPlayer();
    }

    private void initPlayer() {
        //初始化播放器
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(this);
        player.play(url1);
    }

    private void initView() {
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }
}
