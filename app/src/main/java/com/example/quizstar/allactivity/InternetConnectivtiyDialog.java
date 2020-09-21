package com.example.quizstar.allactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class InternetConnectivtiyDialog extends AlertDialog {

    public InternetConnectivtiyDialog(Context context) {
        super(context);

        new AlertDialog.Builder(context)
                .setTitle("Connectivity Lost!!")
                // .setMessage(R.string.text_location_permission)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                })
                .create()
                .show();
    }
}
