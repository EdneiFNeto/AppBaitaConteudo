package com.example.appbaitaconteudo.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "nbtelecom.db";
    public static final String TABELA = " user ";
    public static final String ID     = " id ";
    public static final String NAME   = " name ";
    public static final String SENHA  = " senha ";
    public static final String STATUS = " status ";
    public static final int VERSAO    = 1;


    public DatabaseHelper(Context context){
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement, "
                + NAME + " text, "
                + SENHA + " text, "
                + STATUS + " text "
                +")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
