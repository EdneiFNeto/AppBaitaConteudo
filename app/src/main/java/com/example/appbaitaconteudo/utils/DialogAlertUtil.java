package com.example.appbaitaconteudo.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class DialogAlertUtil {

    private static AlertDialog alerta;

    public static void showDialogSuccess(final Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);

        //define a mensagem
        builder.setMessage(message);

        //define um botão como positivo
        builder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //cria o AlertDialog
        alerta = builder.create();

        //Exibe
        alerta.show();
    }
}
