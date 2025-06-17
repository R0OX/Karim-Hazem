package com.example.loginnex;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading_dialog {

    private Activity activity;
    private AlertDialog dialog;

    Loading_dialog(Activity act){
        activity = act;
    }

    void StartLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    void CancelLoadingDialog(){
        dialog.dismiss();
    }

}
