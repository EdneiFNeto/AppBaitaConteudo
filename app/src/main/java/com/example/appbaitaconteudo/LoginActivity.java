package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appbaitaconteudo.model.User;
import com.example.appbaitaconteudo.utils.DialogAlertUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText campo_login, campo_senha;
    private Button btn_logar, btn_cadastrar;

    //atributo da classe.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campo_login = (EditText) findViewById(R.id.campo_login);
        campo_senha = (EditText) findViewById(R.id.campo_senha);

        btn_logar = (Button) findViewById(R.id.btn_logar);
        btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);


        btn_logar.setOnClickListener(this);
        btn_cadastrar.setOnClickListener(this);

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
            case R.id.btn_cadastrar:
                Intent intent = new Intent(LoginActivity.this, CadastroAvtivity.class);
                startActivity(intent);

        }

    }

    private void logar() {


        final User user = new User();

        user.setLogin(campo_login.getText().toString());
        user.setSenha(campo_senha.getText().toString());
        final LoginPOST loginPOST = new LoginPOST(this, user);
        loginPOST.LoginPOST();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(loginPOST.login.equals(user.getLogin()) && loginPOST.senha.equals(user.getSenha())){
                    DialogAlertUtil.showDialogError(LoginActivity.this, "Aviso", "Usuário autenticado com sucesso !");
                }else{
                    DialogAlertUtil.showDialogError(LoginActivity.this, "Erro ", "Erro de autenticão!\nTente novamente.");
                }
            }
        }, 2000);


    }
}
