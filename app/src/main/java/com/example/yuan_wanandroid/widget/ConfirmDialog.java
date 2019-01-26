package com.example.yuan_wanandroid.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.yuan_wanandroid.R;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 确认框
 * </pre>
 */


public class ConfirmDialog extends Dialog implements View.OnClickListener {

    private OnClickListener mOnClickListener;
    private TextView mTitle;
    private TextView mText;

    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        initView();
    }

    public interface OnClickListener {
        void selectSure();    //选择确定
        String setTitle(); //设置标题
        String setText(); //设置内容
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private void initView(){
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, null);
        mTitle = view.findViewById(R.id.dialogTitle);        //标题
        mText = view.findViewById(R.id.dialogText);    //内容
        TextView cancel = view.findViewById(R.id.dialogCancel);    //取消
        TextView sure = view.findViewById(R.id.dialogSure);    //删除
        cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        super.setContentView(view);     //设置布局
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogCancel:
                cancel();
                break;
            case R.id.dialogSure:
                cancel();
                mOnClickListener.selectSure();
                break;
            default:
                break;
        }
    }/**
     * 重新该方法，使dialog适应屏幕的宽高
     */
    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        //设置高宽
        lp.width = (int) (screenWidth * 0.8); // 宽度
        lp.height = (int) (lp.width * 0.5);     // 高度
        dialogWindow.setAttributes(lp);

        mTitle.setText(mOnClickListener.setTitle());    //设置标题
        mText.setText(mOnClickListener.setText());
    }

}
