package com.whdev.garagem.alerts;

/**
 * Created by Lucas Altmann on 30/05/2017.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertsGaragem extends Dialog {

    public AlertsGaragem(Context context) {
        super(context);
    }

    public void createAlert (int msg, int msgButton) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                getContext().getText(msgButton),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
