package com.feng.corelibrary.utils;


import android.app.Dialog;
import android.content.Context;
import android.os.SystemClock;

import com.feng.corelibrary.R;


/**
 * APP对话框的样式统一控制
 */
public class DialogUtil {

    /**
     * 使用统一的风格创建对话框
     *
     * @param context
     * @param layoutResID 布局文件id
     * @return Dialog
     */
    public static Dialog createDialog(Context context, int layoutResID) {
        Dialog dialog = new MDialog(context, R.style.NobackDialog);
        dialog.setContentView(layoutResID);
        return dialog;
    }

    public static Dialog createDialogAndShow(Context context, int layoutResID) {
        Dialog dialog = createDialog(context, layoutResID);
        dialog.show();
        return dialog;
    }

    public static void show(Dialog dialog) {
        if (dialog != null && dialog.isShowing())
            return;
        dialog.show();
    }

    public static void dissmiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void setShowLong(Dialog dialog, long showLong) {
        if (dialog != null && dialog instanceof MDialog) {
            ((DialogUtil.MDialog)(dialog)).setShowLong(showLong);
        }
    }

    public static class MDialog extends Dialog {

        public long createTime;
        public long showTime;
        public long showLong;

        public MDialog(Context context) {
            this(context, 0);
        }

        public MDialog(Context context, int themeResId) {
            super(context, themeResId);
            createTime = SystemClock.uptimeMillis();
        }

        public void setShowLong(long showLong) {
            this.showLong = showLong;
        }

        @Override
        public void show() {
            super.show();
            showTime = SystemClock.uptimeMillis();
        }

        @Override
        public void dismiss() {
            if (showLong > 0) {
                long timeNow = SystemClock.uptimeMillis();
                if (timeNow - showTime > showLong)
                    super.dismiss();
            } else
                super.dismiss();
        }
    }

}
