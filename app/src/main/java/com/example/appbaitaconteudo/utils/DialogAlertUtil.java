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
        builder.setPositiveButton("Positivo", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //cria o AlertDialog
        alerta = builder.create();

        //Exibe
        alerta.show();
    }

    public static void showDialogError(final Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);

        //define a mensagem
        builder.setMessage(message);

        //define um botão como positivo
        builder.setPositiveButton("Positivo", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(context, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                    }
                });

        //define um botão como negativo.
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });

        //cria o AlertDialog
        alerta = builder.create();

        //Exibe
        alerta.show();
    }
}
