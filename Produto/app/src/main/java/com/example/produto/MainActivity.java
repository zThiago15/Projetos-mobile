package com.example.produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txEmail;
    TextView txSenha;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txEmail = (TextView) findViewById(R.id.txEmail);
        txSenha = (TextView) findViewById(R.id.txSenha);
        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = txEmail.getText().toString();
                String senha = txSenha.getText().toString();

                if(login.isEmpty()){
                    txEmail.setError("Este campo é obrigatório");
                } else if(senha.isEmpty()){
                    txSenha.setError("Este campo é obrigatório");
                } else {
                    Logar();
                    alert("Login realizado com sucesso!");
                }

            }
        });
    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private void Logar(){
        Intent it = new Intent(this, Inicio.class);
        startActivityForResult(it,1);
    }

}