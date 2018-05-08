package com.baidu.teacherma.musicplayer.model;

/**
 * Created by teacherMa on 2018/5/8
 */
public interface ModelCallback<T> {

    void onCallback(T data, int result);

}
