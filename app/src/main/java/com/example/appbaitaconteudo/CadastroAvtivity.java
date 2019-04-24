package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbaitaconteudo.model.User;
import com.example.appbaitaconteudo.utils.DialogAlertUtil;

public class CadastroAvtivity extends AppCompatActivity implements View.OnClickListener {


    private EditText campo_nome, campo_empresa, campo_cargo, campo_email, campo_cel;
    private Button btn_cadastrar;
    private String TAG="cadastroLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_avtivity);


        campo_nome      = (EditText) findViewById(R.id.campo_nome);
        campo_empresa   = (EditText) findViewById(R.id.campo_empresa);
        campo_cargo     = (EditText) findViewById(R.id.campo_cargo);
        campo_email     = (EditText) findViewById(R.id.campo_email);
        campo_cel       = (EditText) findViewById(R.id.campo_cel);

        btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btn_cadastrar.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_cadastrar:
                cadastrarUser();
                break;
        }

    }

    private void cadastrarUser() {


        User user = new User();
        user.setNome(campo_nome.getText().toString());
        user.setEmpresa(campo_empresa.getText().toString());
        user.setCargo(campo_cargo.getText().toString());
        user.setTelefone(campo_cel.getText().toString());
        user.setEmail(campo_email.getText().toString());

        //verificar se os campos estao vazios

        if (!campo_nome.getText().toString().equals("") && !campo_empresa.getText().toString().equals("") && !campo_cargo.getText().toString().equals("") &&
                !campo_cel.getText().toString().equals("") && !campo_email.getText().toString().equals("")) {

            if(validaCampoTelefone(campo_cel.getText().toString()) && validateEmailFormat(campo_email.getText().toString())){

                //send resp post php
                final SendPOST sendPOst =  new SendPOST(this, user);
                sendPOst.sendPOST();


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if(sendPOst.resp.equals("200")){
                            DialogAlertUtil.showDialogSuccess(CadastroAvtivity.this, "Aviso", "Email enviado com sucesso ! \nAguarde que em breve seu acesso será enviado por email.");

                        }else if(sendPOst.resp.equals("401") || sendPOst.resp.equals("500")){
                            DialogAlertUtil.showDialogSuccess(CadastroAvtivity.this, "Aviso", "Não foi possível enviar email ! \nTente novamente.");
                        }
                    }
                }, 1000);

            }else{
                Toast.makeText(this, "Campos Email ou Telefone invalido !", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validateEmailFormat(final String email) {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    private boolean validaCampoTelefone(final String telefone){
        return telefone.length() > 0  && telefone.length()<= 9 ? true: false;
    }
}
