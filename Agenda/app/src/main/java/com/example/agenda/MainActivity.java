package com.example.agenda; //Pacote do projeto

        import androidx.appcompat.app.AppCompatActivity; //Dá compatibilidade com outras versões do Android

        import android.content.Intent; //Importando a classe Intent da biblioteca do Android
        import android.os.Bundle; //Import responsável para execução do projeto no Android
        import android.view.View; //Import para mostrar os resultados
        import android.widget.Button; //Import da classe Button
//Importação das classes

public class MainActivity extends AppCompatActivity {

    Button btInicio; //Variável chamada btInicio do tipo Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInicio = findViewById(R.id.btcadastro); //Atribuir na variável o id do botão

        btInicio.setOnClickListener(new View.OnClickListener(){ //Método da classe Button responsável por saber se houve clique no botão

            public void onClick(View v){ //Em caso de haver clique
                chamaSegunda(); //Irá executar este método
            }
        });
    }

    private void chamaSegunda() { //Método privado chamaSegunda

        Intent it = new Intent(this, Cadastro.class); //Instanciando a variável it do tipo Intent
        //Atribuindo para essa variável a Intent Cadastro.class
        startActivityForResult(it, 1); //Executando o método startActivityForResult para abrir a Intent Cadastro

    }
}