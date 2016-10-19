package com.sky.vr.view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.sky.vr.R;

/**
 * Created by starrysky on 16-5-6.
 */
public class LoadingDialog extends Dialog {

    private TextView tipText;
    private CancelCallback mCancelCallback;

    private boolean cancelable = true;

    public LoadingDialog(Context context) {
        this(context, null);
    }

    public LoadingDialog(Context context, CancelCallback cancelCallback) {
        super(context, R.style.CustomProgressDialog);
        mCancelCallback = cancelCallback;

        setContentView(R.layout.dialog_loading);
        getWindow().setGravity(Gravity.CENTER);

        tipText = (TextView) findViewById(R.id.tipTextView);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        cancelable = flag;
    }

    public void setTipText(int text) {
        tipText.setText(text);
    }

    public void setTipText(String text) {
        tipText.setText(text);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode && cancelable) {

            // 关闭
            dismiss();
            if (mCancelCallback != null) mCancelCallback.cancel();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public interface CancelCallback {

        void cancel();
    }
}
