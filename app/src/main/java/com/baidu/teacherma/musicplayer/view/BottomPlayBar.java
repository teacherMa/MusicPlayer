package com.baidu.teacherma.musicplayer.view;

import android.widget.ImageView;

/**
 * Created by teacherMa on 2018/5/8
 */
public class BottomPlayBar {

    private ImageView mPreMusic;
    private ImageView mNextMusic;
    private ImageView mChangeState;

    public BottomPlayBar(ImageView preMusic, ImageView nextMusic, ImageView changeState) {
        mPreMusic = preMusic;
        mNextMusic = nextMusic;
        mChangeState = changeState;
    }

    public ImageView getPreMusic() {
        return mPreMusic;
    }

    public void setPreMusic(ImageView preMusic) {
        mPreMusic = preMusic;
    }

    public ImageView getNextMusic() {
        return mNextMusic;
    }

    public void setNextMusic(ImageView nextMusic) {
        mNextMusic = nextMusic;
    }

    public ImageView getChangeState() {
        return mChangeState;
    }

    public void setChangeState(ImageView changeState) {
        mChangeState = changeState;
    }
}
