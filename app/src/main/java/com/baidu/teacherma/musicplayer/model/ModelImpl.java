package com.baidu.teacherma.musicplayer.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.baidu.teacherma.musicplayer.App;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacherMa on 2018/5/8
 */
public class ModelImpl implements Model {

    private ModelImpl(){

    }

    private static final ModelImpl sInstance = new ModelImpl();

    public static ModelImpl getInstance(){
        return sInstance;
    }

    @Override
    public void getMusicList(ModelCallback<List<MusicItem>> callback) {
        new MusicUpdateTask(callback).execute();
    }

    private static class MusicUpdateTask extends AsyncTask<Object, MusicItem, Void> {

        SoftReference<ModelCallback<List<MusicItem>>> mListOnResultCallback;

        List<MusicItem> mDataList = new ArrayList<>();

        MusicUpdateTask(ModelCallback<List<MusicItem>> listOnResultCallback) {
            mListOnResultCallback = new SoftReference<>(listOnResultCallback);
        }

        @Override
        protected Void doInBackground(Object... params) {

            //这里是工作线程，处理耗时的查询音乐的操作
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] searchKey = new String[]{
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Albums.ALBUM_ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DURATION
            };

//			String where = MediaStore.Audio.Media.DATA + " like \"%" + "/music" + "%\"";
            String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

            ContentResolver resolver = App.getAppContext().getContentResolver();
            Cursor cursor = resolver.query(
                    uri,
                    searchKey,
                    null,
                    null,
                    sortOrder);

            if (cursor != null) {
                while (cursor.moveToNext() && !isCancelled()) {
//					String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    Uri musicUri = Uri.withAppendedPath(uri, id);

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                    int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ID));
                    Uri albumUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
                    MusicItem data = new MusicItem(musicUri, albumUri, name, duration);

                    publishProgress(data);

                }

                cursor.close();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(MusicItem... values) {
            mDataList.add(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (null != mListOnResultCallback) {
                mListOnResultCallback.get().onCallback(mDataList,100);
            }
        }
    }
}
