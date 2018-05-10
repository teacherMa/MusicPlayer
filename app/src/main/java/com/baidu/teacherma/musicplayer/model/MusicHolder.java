package com.baidu.teacherma.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacherMa on 2018/5/10
 */
public class MusicHolder {

    public static final int ORDER = 0;
    public static final int RANDOM = 1;
    public static final int LOOPER = 2;

    private List<MusicItem> mMusicItems;
    private int mHolderMode = ORDER;

    private MusicHolder() {
    }

    private static final MusicHolder sInstance = new MusicHolder();

    public static MusicHolder getInstance() {
        return sInstance;
    }

    public List<MusicItem> getMusicItems() {
        return mMusicItems;
    }

    public void setMusicItems(List<MusicItem> musicItems) {
        if (musicItems != null) {
            mMusicItems = musicItems;
        }else {
            mMusicItems = new ArrayList<>();
        }
    }

    public MusicItem getNextItem(MusicItem item) {
        int currentIndex = mMusicItems.indexOf(item);
        int nextIndex;
        switch (mHolderMode) {
            case ORDER:
                nextIndex = (currentIndex + 1) % mMusicItems.size();
                break;
            case RANDOM:
                nextIndex = (int) ((currentIndex + mMusicItems.size() * Math.random()) % mMusicItems.size());
                break;
            default:
                nextIndex = (currentIndex + 1) % mMusicItems.size();
                break;
        }
        return mMusicItems.get(nextIndex);
    }

    public MusicItem getPreItem(MusicItem item) {
        int currentIndex = mMusicItems.indexOf(item);
        int preIndex;
        switch (mHolderMode) {
            case ORDER:
                preIndex = (currentIndex + mMusicItems.size() - 1) % mMusicItems.size();
                break;
            case RANDOM:
                preIndex = (int) ((currentIndex + mMusicItems.size() - mMusicItems.size() * Math.random()) % mMusicItems.size());
                break;
            default:
                preIndex = (currentIndex + 1) % mMusicItems.size();
                break;
        }
        return mMusicItems.get(preIndex);
    }

    public int getHolderMode() {
        return mHolderMode;
    }

    public void setHolderMode(int holderMode) {
        mHolderMode = holderMode;
    }
}
