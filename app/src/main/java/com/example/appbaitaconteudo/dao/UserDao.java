package com.example.appbaitaconteudo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appbaitaconteudo.factory.DatabaseHelper;
import com.example.appbaitaconteudo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private SQLiteDatabase db;
    private DatabaseHelper dataBase;
    private Long result;
    private static final String TAG = "UserDao";
    public static boolean statusUser = false;


    public UserDao(Context ctx) {
        this.dataBase = new DatabaseHelper(ctx);
    }

    public void save(User user) throws Exception {

        List<User> list = new ArrayList<>();
        db = dataBase.getWritableDatabase();
        ContentValues values = getContentValues(user);
        result = db.insert(DatabaseHelper.TABELA, null, values);
        db.close();
        Log.e(TAG, "Save success !");
    }

    private ContentValues getContentValues(User user) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.NAME, user.getNome());
        values.put(DatabaseHelper.SENHA, user.getSenha());
        values.put(DatabaseHelper.STATUS, user.getStatus());

        return values;

    }

    public void delete(User user) {

        String where = DatabaseHelper.NAME + " = " + user.getNome();
        db = dataBase.getReadableDatabase();
        db.delete(DatabaseHelper.TABELA, where, null);
        db.close();
    }

    public void update(User user) {

        ContentValues values = getContentValues(user);
        db.update(DatabaseHelper.TABELA, values, "id = " + user.getId(), null);
        db.close();
        Log.e(TAG, "Update success !");
    }

    public List<User> listar() throws Exception {

        List<User> list = new ArrayList<>();
        Cursor cursor;
        String[] campos = {dataBase.ID, dataBase.NAME, dataBase.STATUS};
        db = dataBase.getReadableDatabase();
        cursor = db.query(dataBase.TABELA, campos, null, null, null, null, null, null);

        String status;
        String nome = "";
        String id = "";

        while (cursor.moveToNext()) {

            id = cursor.getString(0);
            nome = cursor.getString(1);
            status = cursor.getString(2);

            User user = new User();
            user.setNome(nome);
            user.setStatus(status);
            user.setId(id);

            list.add(user);
        }


        return list;
    }
}
