package com.baidu.teacherma.musicplayer.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.baidu.teacherma.musicplayer.R;
import com.baidu.teacherma.musicplayer.model.ModelCallback;
import com.baidu.teacherma.musicplayer.model.ModelImpl;
import com.baidu.teacherma.musicplayer.model.MusicItem;

import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mIvAbout;
    private RecyclerView mMusicList;
    private BottomPlayBar mBottomPlayBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        mIvAbout = findViewById(R.id.about_app);

        mMusicList = findViewById(R.id.music_list);

        ImageView preMusic = findViewById(R.id.pre_music);
        ImageView nextMusic = findViewById(R.id.next_music);
        ImageView changeState = findViewById(R.id.change_state);
        mBottomPlayBar = new BottomPlayBar(preMusic, nextMusic, changeState);

        if (checkPermission()) {
            getData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                gotoShare();
                break;
            case R.id.search:
                gotoSearch();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void gotoSearch() {

    }

    private void gotoShare() {

    }

    private void getData() {
        ModelImpl.getInstance().getMusicList(new ModelCallback<List<MusicItem>>() {
            @Override
            public void onCallback(List<MusicItem> data, int result) {
                MusicViewAdapter adapter = new MusicViewAdapter(data, MainActivity.this);
                mMusicList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mMusicList.setAdapter(adapter);
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getData();
    }
}
