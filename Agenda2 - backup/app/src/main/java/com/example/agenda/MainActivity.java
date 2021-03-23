package com.example.agenda; //pacote do nosso projeto

import androidx.appcompat.app.AppCompatActivity; // retrocompatibilidade do Android - Versão anteriores

import android.content.Intent; // importanto a classe da Intent da biblioteca do Android
import android.os.Bundle; // import responsável para execução do projeto no Android
import android.view.View; // import para mostrar os resultados
import android.widget.Button; // import da classe Button

public class MainActivity extends AppCompatActivity {

    Button btInicio; //variável do tipo botão

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInicio = findViewById(R.id.btcadastro); //atribuir na variável o identificador do botão

        btInicio.setOnClickListener(new View.OnClickListener(){ //método da classe Button responsável por verificar se houve click no botão

            public void onClick(View v){ // houve click ?
                chamaSegunda(); // executar este método
            }
            });
    }

    private void chamaSegunda() { // método privado chamaSegunda

            Intent it = new Intent(this, Cadastro.class); //instanciando a varivel it do tipo Intent
                                                                        //Atribuindo para essa variável a Intent Cadastro.class
            startActivityForResult(it, 1); //executando o método para abrir a Intent Cadastro

            }
}