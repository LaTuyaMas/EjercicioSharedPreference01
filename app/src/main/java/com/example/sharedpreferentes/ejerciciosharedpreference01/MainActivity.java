package com.example.sharedpreferentes.ejerciciosharedpreference01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText txtCorreo;
    private EditText txtPassword;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constantes.user, MODE_PRIVATE);

        inicializarVistas();

        inicializarLogin();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = txtCorreo.getText().toString();
                String password = txtPassword.getText().toString();

                if (correo.equals(Constantes.correoCorrecto) && password.equals(Constantes.passwordCorrecto)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constantes.correo, correo);
                    editor.putString(Constantes.password, Constantes.codificaPassword(password));
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, AppActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarVistas(){
        txtCorreo = findViewById(R.id.txtCorreoMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnEntrar = findViewById(R.id.btnEntrarMain);
    }

    private void inicializarLogin() {
        String correo = sharedPreferences.getString(Constantes.correo, null);
        String password = sharedPreferences.getString(Constantes.password, null);

        if (correo != null && password != null){
            password = Constantes.decodificaPassword(password);
            if (correo.equals(Constantes.correoCorrecto) && password.equals(Constantes.passwordCorrecto)){
                startActivity(new Intent(this, AppActivity.class));
                finish();
            }
        }
    }
}