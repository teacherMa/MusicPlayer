package com.baidu.teacherma.musicplayer.model;

/**
 * Created by teacherMa on 2018/5/10
 */
public interface PlayerCallback {

    void onPlayStart();

    void onPlayPause();

    void onPlayError(int errorNumber);

    void onPlayFinish();

}
