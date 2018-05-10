package com.baidu.teacherma.musicplayer.model;

/**
 * Created by teacherMa on 2018/5/9
 */
public interface MusicPlayer {

    void play(String musicPath);

    void pause();

    void resume();

    void seekPlay(String musicUri, long time);

}
