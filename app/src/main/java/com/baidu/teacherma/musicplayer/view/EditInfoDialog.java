package com.baidu.teacherma.musicplayer.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.teacherma.musicplayer.R;

/**
 * Created by teacherMa on 2018/5/11
 */
public class EditInfoDialog extends Dialog {
    private TextView mOk;
    private TextView mCancel;
    private EditText mEditInfo;
    private String mInputString;
    private OnClick mInfoDisplay;
    private Context mContext;

    public interface OnClick{
        void click(String info,int which);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mInputString = s.toString();
        }
    };

    public EditInfoDialog(@NonNull Context context) {
        super(context);
        mContext = context;

        View rootView = View.inflate(context, R.layout.edit_info_dialog, null);

        mOk = rootView.findViewById(R.id.tvOkButton);
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInfoDisplay != null) {
                    mInfoDisplay.click(mInputString,0);
                }
                dismiss();
            }
        });

        mCancel = rootView.findViewById(R.id.tvCancelButton);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mEditInfo = rootView.findViewById(R.id.input_info);
        mEditInfo.addTextChangedListener(mTextWatcher);
        mEditInfo.setFocusable(true);

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {

            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

            dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
            dialogWindow.setAttributes(layoutParams);
            dialogWindow.setGravity(Gravity.BOTTOM);
            setContentView(rootView, layoutParams);
        }

        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                hideSoftKeyboard(mEditInfo);
            }
        });

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dismiss();
                }
                return false;
            }
        });
    }

    public void setInfoDisplay(OnClick infoDisplay) {
        mInfoDisplay = infoDisplay;
    }

    public void hideSoftKeyboard(TextView textView) {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && textView != null && imm.isActive(textView)) {
            imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
        }
        ((Activity) mContext).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void show() {
        super.show();
        mEditInfo.requestFocus();
        InputMethodManager imm =
                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(mEditInfo, 0);
        }
    }
}
