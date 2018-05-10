package com.baidu.teacherma.musicplayer.model;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by teacherMa on 2018/5/9
 */
public class MusicPlayerImpl implements MusicPlayer {

    private static MediaPlayer mMediaPlayer = new MediaPlayer();
    private String mCurPlayAudioPath;
    private PlayerCallback mPlayerCallback;

    @Override
    public void play(String musicPath) {
        if (null == mMediaPlayer) {
            mMediaPlayer = new MediaPlayer();
        }
        if (mCurPlayAudioPath != null && mCurPlayAudioPath.equals(musicPath)) {
            return;
        }
        mMediaPlayer.stop();
        //after invoke reset,all listener would be removed
        mMediaPlayer.reset();
        //播放语音
        try {
            mCurPlayAudioPath = musicPath;

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mCurPlayAudioPath = null;
                    mp.reset();
                    if (mPlayerCallback != null) {
                        mPlayerCallback.onPlayFinish();
                    }
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    if (mPlayerCallback != null) {
                        mPlayerCallback.onPlayError(-1);
                    }
                    return false;
                }
            });
            mMediaPlayer.setDataSource(musicPath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            if (mPlayerCallback != null) {
                mPlayerCallback.onPlayError(-1);
            }
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
        }
    }

    @Override
    public void resume() {
        if (mMediaPlayer != null) {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }
    }

    @Override
    public void seekPlay(String musicUri, long time) {

    }

    public void setPlayerCallback(PlayerCallback playerCallback) {
        mPlayerCallback = playerCallback;
    }
}
