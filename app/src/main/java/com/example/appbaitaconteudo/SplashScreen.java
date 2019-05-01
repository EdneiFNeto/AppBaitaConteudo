package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appbaitaconteudo.dao.UserDao;
import com.example.appbaitaconteudo.model.User;

import java.util.List;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //1 verify user == 1
        UserDao dao = new UserDao(this);
        try {
            List<User> userList = dao.listar();

            //exists user
            if(userList.size() > 0){
                //verify status == 1
                for (User users: userList){


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(it);
            }
        }, 3000);
    }
}
