package com.baidu.teacherma.musicplayer.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baidu.teacherma.musicplayer.R;
import com.baidu.teacherma.musicplayer.model.MusicItem;

/**
 * Created by teacherMa on 2018/5/8
 */
public class MusicViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mDuration;
    private TextView mArtist;

    public MusicViewHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.music_name);
        mDuration = itemView.findViewById(R.id.music_duration);
        mArtist = itemView.findViewById(R.id.music_artist);
    }

    public void bindData(final MusicItem musicItem, final int position, final ItemClickListener itemClickListener) {
        mName.setText(musicItem.getName());
        int second = (int) (musicItem.getDuration() / 1000);
        String s = second / 60 + ":";
        if (second % 60 < 10) {
            s += "0" + second % 60;
        } else {
            s += second % 60;
        }
        mDuration.setText(s);
        mArtist.setText(musicItem.getArtist());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(musicItem, position);
            }
        });
    }
}
