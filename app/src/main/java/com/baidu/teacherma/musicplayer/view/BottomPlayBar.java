package com.baidu.teacherma.musicplayer.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.teacherma.musicplayer.App;
import com.baidu.teacherma.musicplayer.R;
import com.baidu.teacherma.musicplayer.model.MusicHolder;
import com.baidu.teacherma.musicplayer.model.MusicItem;
import com.baidu.teacherma.musicplayer.model.MusicPlayer;
import com.baidu.teacherma.musicplayer.model.PlayerCallback;
import com.baidu.teacherma.musicplayer.util.Util;

/**
 * Created by teacherMa on 2018/5/8
 */
public class BottomPlayBar implements PlayerCallback {

    private ImageView mPreMusic;
    private ImageView mNextMusic;
    private ImageView mChangeState;
    private TextView mMusicName;
    private TextView mPlayMode;


    private MusicPlayer mMusicPlayer;
    private MusicItem mCurrentMusic;
    private boolean mIsPlaying;

    public BottomPlayBar(ImageView preMusic, ImageView nextMusic, ImageView changeState, TextView musicName, TextView playMode) {
        mPreMusic = preMusic;
        mNextMusic = nextMusic;
        mChangeState = changeState;
        mMusicName = musicName;
        mPlayMode = playMode;
        setOnClickListener();
    }

    private void setOnClickListener() {
        mPreMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentMusic == null) {
                    return;
                }
                mCurrentMusic = MusicHolder.getInstance().getPreItem(mCurrentMusic);
                mMusicName.setText(mCurrentMusic.getName());
                mIsPlaying = true;
                requestPlay();
            }
        });
        mNextMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentMusic == null) {
                    return;
                }
                mCurrentMusic = MusicHolder.getInstance().getNextItem(mCurrentMusic);
                mIsPlaying = true;
                mMusicName.setText(mCurrentMusic.getName());
                requestPlay();
            }
        });
        mChangeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentMusic == null) {
                    return;
                }
                if (mIsPlaying) {
                    mMusicPlayer.pause();
                } else {
                    mMusicPlayer.resume();
                }
                mIsPlaying = !mIsPlaying;
                if (mIsPlaying) {
                    mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                            R.drawable.ic_pause_36dp
                    ));
                } else {
                    mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                            R.drawable.ic_play_arrow_36dp
                    ));
                }
            }

        });
        mPlayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicHolder.getInstance().getHolderMode() == MusicHolder.ORDER){
                    mPlayMode.setText(App.getAppContext().getText(R.string.random_play));
                    MusicHolder.getInstance().setHolderMode(MusicHolder.RANDOM);
                }else {
                    mPlayMode.setText(App.getAppContext().getText(R.string.order_play));
                    MusicHolder.getInstance().setHolderMode(MusicHolder.ORDER);
                }
            }
        });
    }

    public void requestPlay() {
        if (mCurrentMusic != null) {
            requestPlay(mCurrentMusic);
        }
    }

    public void requestPlay(MusicItem musicItem) {
        if (mMusicPlayer != null) {
            mCurrentMusic = musicItem;
            mIsPlaying = true;
            mMusicName.setText(mCurrentMusic.getName());
            mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                    R.drawable.ic_pause_36dp
            ));
            mMusicPlayer.play(Util.getRealFilePath(mCurrentMusic.getSongUri()));
        }
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

    public TextView getMusicName() {
        return mMusicName;
    }

    public void setMusicName(TextView musicName) {
        mMusicName = musicName;
    }

    public MusicPlayer getMusicPlayer() {
        return mMusicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        mMusicPlayer = musicPlayer;
    }

    public MusicItem getCurrentMusic() {
        return mCurrentMusic;
    }

    public void setCurrentMusic(MusicItem currentMusic) {
        mCurrentMusic = currentMusic;
    }

    public boolean isPlaying() {
        return mIsPlaying;
    }

    public void setPlaying(boolean playing) {
        mIsPlaying = playing;
    }

    @Override
    public void onPlayStart() {
        mIsPlaying = true;
        mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                R.drawable.ic_pause_36dp
        ));
    }

    @Override
    public void onPlayPause() {
        mIsPlaying = false;
        mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                R.drawable.ic_play_arrow_36dp
        ));
    }

    @Override
    public void onPlayError(int errorNumber) {
        mIsPlaying = false;
        mChangeState.setImageDrawable(App.getAppContext().getDrawable(
                R.drawable.ic_play_arrow_36dp
        ));
    }

    @Override
    public void onPlayFinish() {
        mNextMusic.performClick();
    }
}
