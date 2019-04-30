package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbaitaconteudo.utils.DialogAlertUtil;

public class UsuarioActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView listView;
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private ImageView menu_toolbar;
    private EditText edit_text_nome;
    private CheckBox chebox;
    private boolean ischeckBoxChecked;
    private TextView nome_perfil;
    private Button btn_editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        listView = (ListView) findViewById(R.id.listView);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        edit_text_nome = (EditText) findViewById(R.id.text_edit_nome);
        chebox = (CheckBox) findViewById(R.id.checkBox);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_toolbar = (ImageView) findViewById(R.id.menu_toolbar);

        menu_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });

        nome_perfil = (TextView) findViewById(R.id.nome_perfil);
        btn_editar = (Button) findViewById(R.id.btn_editar);
        btn_editar.setOnClickListener(this);
        chebox.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new AdapterChannel(this));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_user_logado:
                Intent intent = new Intent(UsuarioActivity.this, UsuarioActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        drawer_layout.closeDrawer(Gravity.START, true);//close navigation view
        Intent intent = new Intent(UsuarioActivity.this, ChannelActivity.class);
        intent.putExtra("channel", "" + position);
        startActivity(intent);

        drawer_layout.closeDrawer(Gravity.START, true);
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.checkBox:
                ischeckBoxChecked = isSelectedCheckBox();
                break;
            case R.id.btn_editar:
                editarDados();
                break;
        }
    }

    private void editarDados() {

        //save data db
        if (!edit_text_nome.getText().toString().equals("") || edit_text_nome.getText().toString().length() > 0) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    nome_perfil.setText(edit_text_nome.getText().toString());
                    chebox.setChecked(ischeckBoxChecked);
                    DialogAlertUtil.showDialogSuccess(UsuarioActivity.this, "Aviso", "Dados alterados com sucesso !");
                }
            });
        } else {
            DialogAlertUtil.showDialogSuccess(UsuarioActivity.this, "Aviso", "Erro ao salvar nome do usuário.\nPreencha o campo nome vazio, ");
        }
    }


    private boolean isSelectedCheckBox() {
        if (chebox.isChecked()) {
            return true;
        }
        return false;
    }
}
