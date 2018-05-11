package com.baidu.teacherma.musicplayer.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.baidu.teacherma.musicplayer.R;

import java.util.Date;

public class AboutAppActivity extends AppCompatActivity implements EditInfoDialog.OnClick {

    private static final String KEY_APP_INFO = "APP_INFO";
    public static final String DATE_FORMAT = "yyyy-MM-dd  HH:mm";

    private Toolbar mToolbar;
    private TextView mTimeInfo;
    private TextView mAppInfo;

    public static Intent getNewIntent(Context context) {
        return new Intent(context, AboutAppActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        mToolbar = findViewById(R.id.about_app_tool_bar);
        setSupportActionBar(mToolbar);

        mTimeInfo = findViewById(R.id.time_info);
        mTimeInfo.setText(DateFormat.format(DATE_FORMAT, new Date()));

        UpdateTimeThread updateTimeThread = new UpdateTimeThread();
        updateTimeThread.setTextView(mTimeInfo);
        updateTimeThread.start();

        mAppInfo = findViewById(R.id.app_info);
        String info = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(KEY_APP_INFO, "");
        if (!TextUtils.isEmpty(info)) {
            mAppInfo.setText(info);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.edit_app_info:
                gotoEditAppInfo();
                break;
            default:
                break;
        }
        return true;
    }

    private void gotoEditAppInfo() {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this);
        editInfoDialog.setInfoDisplay(this);
        editInfoDialog.show();
    }

    @Override
    public void click(String info, int which) {
        mAppInfo.setText(info);
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putString(KEY_APP_INFO, info)
                .apply();
    }

    private class UpdateTimeThread extends Thread {
        private TextView mTextView;

        @Override
        public void run() {
            try {
                while (true) {
                    sleep(60 * 1000);
                    final Message msg = Message.obtain();
                    final Date date = new Date();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(DateFormat.format(DATE_FORMAT, date).toString());
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void setTextView(TextView textView) {
            mTextView = textView;
        }
    }

}
