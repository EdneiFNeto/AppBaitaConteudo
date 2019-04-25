package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.appbaitaconteudo.model.User;
import com.example.appbaitaconteudo.utils.DialogAlertUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText campo_login, campo_senha;
    private Button btn_logar, btn_cadastrar;
    private ProgressBar spinner;
    private String TAG = "LoginLog";

    //atributo da classe.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campo_login = (EditText) findViewById(R.id.campo_login);
        campo_senha = (EditText) findViewById(R.id.campo_senha);
        btn_logar = (Button) findViewById(R.id.btn_logar);
        spinner = (ProgressBar) findViewById(R.id.spinner);

        btn_logar.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_logar:
                logar();
                break;
        }

    }

    private void logar() {

        try {

            final User user = new User();

            user.setLogin(campo_login.getText().toString());
            user.setSenha(campo_senha.getText().toString());
            final LoginPOST loginPOST = new LoginPOST(this, user);
            loginPOST.LoginPOST();

            spinner.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    spinner.setVisibility(View.GONE);

                    if (loginPOST.login != null && loginPOST.senha != null) {

                        if (loginPOST.login.equals(user.getLogin()) && loginPOST.senha.equals(user.getSenha())) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            DialogAlertUtil.showDialogSuccess(LoginActivity.this, "Erro ", "Erro de autenticão!\nTente novamente.");
                        }

                    }else{
                        DialogAlertUtil.showDialogSuccess(LoginActivity.this, "Erro ", "Erro de autenticão!\nTente novamente.");
                    }

                }
            }, 2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
