package com.baidu.teacherma.musicplayer.model;

import java.util.List;

/**
 * Created by teacherMa on 2018/5/8
 */
public interface Model {

    void getMusicList(ModelCallback<List<MusicItem>> callback);

}
