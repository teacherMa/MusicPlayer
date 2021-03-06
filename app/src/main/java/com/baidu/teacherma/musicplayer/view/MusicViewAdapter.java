package com.baidu.teacherma.musicplayer.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.teacherma.musicplayer.R;
import com.baidu.teacherma.musicplayer.model.MusicItem;

import java.util.List;

/**
 * Created by teacherMa on 2018/5/8
 */
public class MusicViewAdapter extends RecyclerView.Adapter<MusicViewHolder> {

    private List<MusicItem> mMusicItems;
    private Context mContext;

    public MusicViewAdapter(List<MusicItem> musicItems, Context context) {
        mMusicItems = musicItems;
        mContext = context;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_item, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.bindData(mMusicItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mMusicItems.size();
    }
}
