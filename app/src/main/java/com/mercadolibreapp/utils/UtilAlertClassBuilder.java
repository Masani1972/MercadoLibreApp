package com.mercadolibreapp.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.WindowManager;

import com.mercadolibreapp.R;


public class UtilAlertClassBuilder {
    private  AlertDialog alert;

    public UtilAlertClassBuilder(final Activity activity) {
        this.alert = new AlertDialog.Builder(activity, R.style.MyDialogTheme).create();
    }

    public UtilAlertClassBuilder setTitle(String title){
        alert.setTitle(title);
        return this;
    }

    public UtilAlertClassBuilder setMessage(String message){
        alert.setMessage(message);
        return this;
    }

    public UtilAlertClassBuilder setType(TypeAlert typeAlert) {
        switch (typeAlert) {
            case TYPE_INFO:
                alert.setIcon(android.R.drawable.ic_dialog_info);
                break;
            case TYPE_ERROR:
                alert.setIcon(android.R.drawable.ic_dialog_alert);
                break;
        }
        return this;
    }

    public AlertDialog  build(){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alert.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alert.getWindow().setAttributes(lp);
        return alert;
    }

}
