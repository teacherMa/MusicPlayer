package com.baidu.teacherma.musicplayer.view;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.teacherma.musicplayer.R;
import com.baidu.teacherma.musicplayer.model.MusicItem;

/**
 * Created by teacherMa on 2018/5/8
 */
public class MusicViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;

    public MusicViewHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.music_name);
    }

    public void bindData(MusicItem musicItem){
        mName.setText(musicItem.getName());
    }
}
