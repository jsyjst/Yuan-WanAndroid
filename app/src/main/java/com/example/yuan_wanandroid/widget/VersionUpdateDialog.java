package com.example.yuan_wanandroid.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.event.UpdateEvent;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/04/17
 *     desc   : 版本更新弹窗
 * </pre>
 */

public class VersionUpdateDialog extends DialogFragment {

    private String mContentText = "";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_version_update, null);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.setOnKeyListener((dialog1, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0);
        TextView updateLater = view.findViewById(R.id.updateLaterTv);
        TextView updateNow = view.findViewById(R.id.updateNowTv);
        TextView content = view.findViewById(R.id.versionContentTv);
        content.setText(mContentText);
        updateLater.setOnClickListener(v -> dialog.dismiss());
        updateNow.setOnClickListener(v -> {
            dialog.dismiss();
            RxBus.getInstance().post(new UpdateEvent());
        });
        return dialog;
    }

    public void setContentText(String content){
        mContentText = content;
    }
}
